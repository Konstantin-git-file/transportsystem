package com.tutorial.transportsystem.controllers;

import com.tutorial.transportsystem.dto.TicketDTO;
import com.tutorial.transportsystem.entity.Ticket;
import com.tutorial.transportsystem.mapper.UserMapper;
import com.tutorial.transportsystem.repository.ETicketRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.var;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/tickets")
@RequiredArgsConstructor
@Slf4j
public class TicketController {

    private final ETicketRepository ticketRepository;
    private final UserMapper userMapper;

    @GetMapping
    public ResponseEntity<List<TicketDTO>> getAllTickets(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        log.info("Получен запрос: GET /api/tickets?page={}&size={} — получить список билетов", page, size);

        var tickets = ticketRepository.findAll(PageRequest.of(page, size, Sort.by("id"))).getContent();
        var ticketDTOs = tickets.stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList());

        log.info("Найдено {} билет(ов)", ticketDTOs.size());
        return ResponseEntity.ok(ticketDTOs);
    }

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

    @PostMapping
    public ResponseEntity<TicketDTO> createTicket(@RequestBody TicketDTO ticketDTO) {
        log.info("Получен запрос: POST /api/tickets — создать билет: {}", ticketDTO);

        Ticket saved = ticketRepository.save(userMapper.toEntity(ticketDTO));

        log.info("Билет успешно создан с ID={}", saved.getId());
        return ResponseEntity.ok(userMapper.toDto(saved));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TicketDTO> updateTicket(@PathVariable Long id, @RequestBody TicketDTO ticketDTO) {
        log.info("Получен запрос: PUT /api/tickets/{} — обновить билет: {}", id, ticketDTO);

        return ticketRepository.findById(id)
                .map(existing -> {
                    Ticket updated = userMapper.toEntity(ticketDTO);
                    updated.setId(id);
                    Ticket saved = ticketRepository.save(updated);
                    log.info("Билет с ID={} успешно обновлён", id);
                    return ResponseEntity.ok(userMapper.toDto(saved));
                })
                .orElseGet(() -> {
                    log.warn("Обновление невозможно: билет с ID={} не найден", id);
                    return ResponseEntity.notFound().build();
                });
    }
}