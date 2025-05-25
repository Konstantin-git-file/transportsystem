package com.tutorial.transportsystem.controllers;

import com.tutorial.transportsystem.dto.TicketDTO;
import com.tutorial.transportsystem.entity.*;
import com.tutorial.transportsystem.mapper.TicketMapper;
import com.tutorial.transportsystem.mapper.UserMapper;
import com.tutorial.transportsystem.repository.CurrentLocationRepository;
import com.tutorial.transportsystem.repository.DestinationRepository;
import com.tutorial.transportsystem.repository.ETicketRepository;
import com.tutorial.transportsystem.repository.UserRepository;
import com.tutorial.transportsystem.service.TicketService;
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

    private final TicketService ticketService;

    @PostMapping
    public ResponseEntity<TicketDTO> create(@RequestBody @Valid TicketDTO dto) {
        log.info("Создание билета");
        return ResponseEntity.ok(ticketService.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TicketDTO> update(@PathVariable Long id, @RequestBody @Valid TicketDTO dto) {
        return ResponseEntity.ok(ticketService.update(id, dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TicketDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(ticketService.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<TicketDTO>> getAll(@RequestParam(defaultValue = "0") int page,
                                                  @RequestParam(defaultValue = "10") int size,
                                                  @RequestParam(required = false) String cityAndStation) {
        return ResponseEntity.ok(ticketService.getAll(page, size, cityAndStation));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        ticketService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
