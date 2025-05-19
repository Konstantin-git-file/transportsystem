package com.tutorial.transportsystem.controllers;

import com.tutorial.transportsystem.dto.TicketDTO;
import com.tutorial.transportsystem.entity.*;
import com.tutorial.transportsystem.mapper.TicketMapper;
import com.tutorial.transportsystem.mapper.UserMapper;
import com.tutorial.transportsystem.repository.CurrentLocationRepository;
import com.tutorial.transportsystem.repository.DestinationRepository;
import com.tutorial.transportsystem.repository.ETicketRepository;
import com.tutorial.transportsystem.repository.UserRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.validation.BindingResult;
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
    private final TicketMapper ticketMapper; // Замените UserMapper на TicketMapper
    private final KafkaTemplate<String, TicketDTO> kafkaTemplate;
    private final UserRepository userRepository;
    private final CurrentLocationRepository locationRepository;
    private final DestinationRepository destinationRepository;

    @PostMapping
    public ResponseEntity<?> createTicket(@Valid @RequestBody TicketDTO ticketDTO, BindingResult result) {
        log.info("Получен запрос: POST /api/tickets — создать билет");
        log.debug("Входящий DTO: {}", ticketDTO);

        if (result.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            result.getFieldErrors().forEach(error ->
                    errors.put(error.getField(), error.getDefaultMessage()));
            log.warn("Ошибки валидации при создании билета: {}", errors);
            return ResponseEntity.badRequest().body(errors);
        }

        try {
            User user = userRepository.findById(ticketDTO.getUserId())
                    .orElseThrow(() -> new RuntimeException("Пользователь с ID " + ticketDTO.getUserId() + " не найден"));
            CurrentLocation location = locationRepository.findById(ticketDTO.getLocationId())
                    .orElseThrow(() -> new RuntimeException("Локация с ID " + ticketDTO.getLocationId() + " не найдена"));
            Destination destination = destinationRepository.findById(ticketDTO.getDestinationId())
                    .orElseThrow(() -> new RuntimeException("Пункт назначения с ID " + ticketDTO.getDestinationId() + " не найден"));

            Ticket ticket = ticketMapper.toEntity(ticketDTO);
            ticket.setUser(user);
            ticket.setCurrentLocation(location);
            ticket.setDestination(destination);

            Ticket saved = ticketRepository.save(ticket);
            TicketDTO savedDTO = ticketMapper.toDto(saved);

//            kafkaTemplate.send("ticket-bookings", String.valueOf(saved.getId()), savedDTO);
            log.info("Билет успешно создан с ID={}", saved.getId());
            return ResponseEntity.ok(savedDTO);
        } catch (Exception e) {
            log.error("Ошибка при сохранении билета", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Ошибка сервера: " + e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllTickets(
            @RequestParam(required = false) String cityAndStation,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
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
                .map(ticketMapper::toDto) // Используем ticketMapper
                .collect(Collectors.toList());

        Map<String, Object> response = new HashMap<>();
        response.put("tickets", ticketDTOs);
        response.put("currentPage", ticketPage.getNumber());
        response.put("totalItems", ticketPage.getTotalElements());
        response.put("totalPages", ticketPage.getTotalPages());

        log.info("Найдено {} билет(ов), страница {}/{}", ticketDTOs.size(), page, ticketPage.getTotalPages());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TicketDTO> getTicketById(@PathVariable Long id) {
        log.info("Получен запрос: GET /api/tickets/{} — получить билет по ID", id);

        return ticketRepository.findById(id)
                .map(ticketMapper::toDto) // Используем ticketMapper
                .map(dto -> {
                    log.info("Билет с ID={} найден: {}", id, dto);
                    return ResponseEntity.ok(dto);
                })
                .orElseGet(() -> {
                    log.warn("Билет с ID={} не найден", id);
                    return ResponseEntity.notFound().build();
                });
    }

    @PutMapping("/{id}")
    public ResponseEntity<TicketDTO> updateTicket(@PathVariable Long id, @Valid @RequestBody TicketDTO ticketDTO) {
        log.info("Получен запрос: PUT /api/tickets/{} — обновить билет", id);
        log.debug("Входящий DTO: {}", ticketDTO);

        return ticketRepository.findById(id)
                .map(existing -> {
                    Ticket updated = ticketMapper.toEntity(ticketDTO); // Используем ticketMapper
                    updated.setId(id);
                    updated.setUser(userRepository.findById(ticketDTO.getUserId()).orElse(null));
                    updated.setCurrentLocation(locationRepository.findById(ticketDTO.getLocationId()).orElse(null));
                    updated.setDestination(destinationRepository.findById(ticketDTO.getDestinationId()).orElse(null));

                    Ticket saved = ticketRepository.save(updated);
                    TicketDTO savedDTO = ticketMapper.toDto(saved);

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