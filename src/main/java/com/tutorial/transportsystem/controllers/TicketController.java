package com.tutorial.transportsystem.controllers;

import com.tutorial.transportsystem.dto.TicketDTO;
import com.tutorial.transportsystem.entity.Ticket;
import com.tutorial.transportsystem.mapper.UserMapper;
import com.tutorial.transportsystem.repository.ETicketRepository;
import lombok.RequiredArgsConstructor;
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
public class TicketController {

    private final ETicketRepository ticketRepository;
    private final UserMapper userMapper;

    @GetMapping
    public ResponseEntity<List<TicketDTO>> getAllTickets(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        var tickets = ticketRepository.findAll(PageRequest.of(page, size, Sort.by("id"))).getContent();
        var ticketDTOs = tickets.stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(ticketDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TicketDTO> getTicketById(@PathVariable Long id) {
        return ticketRepository.findById(id)
                .map(userMapper::toDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<TicketDTO> createTicket(@RequestBody TicketDTO ticketDTO) {
        Ticket saved = ticketRepository.save(userMapper.toEntity(ticketDTO));
        return ResponseEntity.ok(userMapper.toDto(saved));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TicketDTO> updateTicket(@PathVariable Long id, @RequestBody TicketDTO ticketDTO) {
        return ticketRepository.findById(id)
                .map(existing -> {
                    Ticket updated = userMapper.toEntity(ticketDTO);
                    updated.setId(id);
                    return ResponseEntity.ok(userMapper.toDto(ticketRepository.save(updated)));
                })
                .orElse(ResponseEntity.notFound().build());
    }
}