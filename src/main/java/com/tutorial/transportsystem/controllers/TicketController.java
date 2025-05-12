package com.tutorial.transportsystem.controllers;

import com.tutorial.transportsystem.dto.TicketDTO;
import com.tutorial.transportsystem.entity.CityAndStation;
import com.tutorial.transportsystem.entity.Ticket;
import com.tutorial.transportsystem.mapper.UserMapper;
import com.tutorial.transportsystem.repository.ETicketRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/tickets")
@RequiredArgsConstructor
@Slf4j
public class TicketController {

    private final ETicketRepository ticketRepository;
    private final UserMapper userMapper;
    private final KafkaTemplate<String, TicketDTO> kafkaTemplate;

    @Operation(summary = "Получить список билетов", description = "Возвращает постраничный список билетов с возможностью фильтрации по cityAndStation")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Список билетов успешно возвращён"),
            @ApiResponse(responseCode = "400", description = "Некорректные параметры запроса")
    })
    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllTickets(
            @RequestParam(required = false) String cityAndStation,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        log.info("Получен запрос: GET /api/tickets?page={}&size={} — получить список билетов", page, size);
        if (page < 0 || size <= 0) {
            log.warn("Некорректные параметры запроса: page={}, size={}", page, size);
            return ResponseEntity.badRequest().build();
        }

        PageRequest pageRequest = PageRequest.of(page, size, Sort.by("id"));
        Page<Ticket> ticketPage;

        if (cityAndStation != null && !cityAndStation.isEmpty()) {
            try {
                CityAndStation cityAndStationEnum = CityAndStation.valueOf(cityAndStation.toUpperCase());
                ticketPage = ticketRepository.findByCityAndStation(cityAndStationEnum, pageRequest);
            } catch (IllegalArgumentException e) {
                log.warn("Некорректное значение cityAndStation: {}", cityAndStation);
                return ResponseEntity.badRequest().body(Map.of("error", "Invalid cityAndStation value: " + cityAndStation));
            }
        } else {
            ticketPage = ticketRepository.findAll(pageRequest);
        }

        List<TicketDTO> ticketDTOs = ticketPage.getContent().stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList());

        Map<String, Object> response = new HashMap<>();
        response.put("tickets", ticketDTOs);
        response.put("currentPage", ticketPage.getNumber());
        response.put("totalItems", ticketPage.getTotalElements());
        response.put("totalPages", ticketPage.getTotalPages());

        log.info("Найдено {} билет(ов), страница {}/{}", ticketDTOs.size(), page, ticketPage.getTotalPages());
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Получить билет по ID", description = "Возвращает билет по указанному ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Билет найден"),
            @ApiResponse(responseCode = "404", description = "Билет не найден")
    })
    @GetMapping("/{id}")
    public ResponseEntity<TicketDTO> getTicketById(@PathVariable Long id) {
        log.info("Получен запрос: GET /api/tickets/{} — получить билет по ID", id);

        return ticketRepository.findById(id)
                .map(userMapper::toDto)
                .map(dto -> {
                    log.info("Билет с ID={} найден: {}", id, dto);
                    return ResponseEntity.ok(dto);
                })
                .orElseGet(() -> {
                    log.warn("Билет с ID={} не найден", id);
                    return ResponseEntity.notFound().build();
                });
    }

    @Operation(summary = "Создать новый билет", description = "Создаёт новый билет на основе переданных данных")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Билет успешно создан"),
            @ApiResponse(responseCode = "400", description = "Некорректные входные данные")
    })
    @PostMapping
    public ResponseEntity<TicketDTO> createTicket(@Valid @RequestBody TicketDTO ticketDTO) {
        log.info("Получен запрос: POST /api/tickets — создать билет");
        log.debug("Входящий DTO: {}", ticketDTO);

        Ticket saved = ticketRepository.save(userMapper.toEntity(ticketDTO));
        TicketDTO savedDTO = userMapper.toDto(saved);

        kafkaTemplate.send("ticket-bookings", String.valueOf(saved.getId()), savedDTO);
        log.info("Билет успешно создан с ID={}", saved.getId());
        return ResponseEntity.ok(savedDTO);
    }

    @Operation(summary = "Обновить билет", description = "Обновляет существующий билет по указанному ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Билет успешно обновлён"),
            @ApiResponse(responseCode = "404", description = "Билет не найден"),
            @ApiResponse(responseCode = "400", description = "Некорректные входные данные")
    })
    @PutMapping("/{id}")
    public ResponseEntity<TicketDTO> updateTicket(@PathVariable Long id, @Valid @RequestBody TicketDTO ticketDTO) {
        log.info("Получен запрос: PUT /api/tickets/{} — обновить билет", id);
        log.debug("Входящий DTO: {}", ticketDTO);

        return ticketRepository.findById(id)
                .map(existing -> {
                    Ticket updated = userMapper.toEntity(ticketDTO);
                    updated.setId(id);
                    Ticket saved = ticketRepository.save(updated);
                    TicketDTO savedDTO = userMapper.toDto(saved);

                    kafkaTemplate.send("ticket-bookings", String.valueOf(id), savedDTO);
                    log.info("Билет с ID={} успешно обновлён", id);
                    return ResponseEntity.ok(savedDTO);
                })
                .orElseGet(() -> {
                    log.warn("Обновление невозможно: билет с ID={} не найден", id);
                    return ResponseEntity.notFound().build();
                });
    }
}