package com.tutorial.transportsystem.controllers;
import com.tutorial.transportsystem.entity.Destination;
import com.tutorial.transportsystem.repository.DestinationRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/destinations")
@RequiredArgsConstructor
@Slf4j
public class DestinationController {

    private final DestinationRepository destinationRepository;

    @Operation(summary = "Получить список пунктов назначения", description = "Возвращает список всех пунктов назначения с их ID, городами и станциями")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Список пунктов назначения успешно возвращён")
    })
    @GetMapping
    public ResponseEntity<List<Map<String, Object>>> getAllDestinations() {
        log.info("Получен запрос: GET /api/destinations — получить список пунктов назначения");

        List<Map<String, Object>> destinations = destinationRepository.findAll().stream()
                .map(destination -> {
                    Map<String, Object> map = new HashMap<>();
                    map.put("id", destination.getId());
                    map.put("city", destination.getCity());
                    map.put("station", destination.getStation().name());
                    return map;
                })
                .collect(Collectors.toList());

        log.info("Найдено {} пунктов назначения", destinations.size());
        return ResponseEntity.ok(destinations);
    }
}