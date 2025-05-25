package com.tutorial.transportsystem.controllers;

import com.tutorial.transportsystem.entity.CurrentLocation;
import com.tutorial.transportsystem.repository.CurrentLocationRepository;
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
@RequestMapping("/api/current-locations")
@RequiredArgsConstructor
@Slf4j
public class CurrentLocationController {

    private final CurrentLocationRepository currentLocationRepository;

    @Operation(summary = "Получить список станций отправления", description = "Возвращает список всех станций отправления с их ID, городом и станцией")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Список станций успешно возвращён")
    })
    @GetMapping
    public ResponseEntity<List<Map<String, Object>>> getAllCurrentLocations() {
        log.info("Получен запрос: GET /api/current-locations — получить список станций отправления");

        List<Map<String, Object>> locations = currentLocationRepository.findAll().stream()
                .map(location -> {
                    Map<String, Object> map = new HashMap<>();
                    map.put("id", location.getId());
                    map.put("city", location.getCity());
                    map.put("station", location.getStation());
                    return map;
                })
                .collect(Collectors.toList());

        log.info("Найдено {} станций отправления", locations.size());
        return ResponseEntity.ok(locations);
    }
}