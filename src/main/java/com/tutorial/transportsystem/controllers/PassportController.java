package com.tutorial.transportsystem.controllers;

import com.tutorial.transportsystem.dto.PassportDTO;
import com.tutorial.transportsystem.entity.Passport;
import com.tutorial.transportsystem.mapper.UserMapper;
import com.tutorial.transportsystem.repository.PassportRepository;
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
@RequestMapping("/api/passports")
@RequiredArgsConstructor
@Slf4j
public class PassportController {

    private final PassportRepository passportRepository;
    private final UserMapper userMapper;
    private final KafkaTemplate<String, PassportDTO> kafkaTemplate;

    @Operation(summary = "Получить список паспортов", description = "Возвращает постраничный список паспортов с возможностью фильтрации по serial")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Список паспортов успешно возвращён"),
            @ApiResponse(responseCode = "400", description = "Некорректные параметры запроса")
    })
    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllPassports(
            @RequestParam(required = false) String serial,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        log.info("Получен запрос: GET /api/passports?page={}&size={} — получить список паспортов", page, size);
        if (page < 0 || size <= 0) {
            log.warn("Некорректные параметры запроса: page={}, size={}", page, size);
            return ResponseEntity.badRequest().build();
        }

        PageRequest pageRequest = PageRequest.of(page, size, Sort.by("id"));
        Page<Passport> passportPage = serial != null && !serial.isEmpty()
                ? passportRepository.findBySerialContainingIgnoreCase(serial, pageRequest)
                : passportRepository.findAll(pageRequest);

        List<PassportDTO> passportDTOs = passportPage.getContent().stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList());

        Map<String, Object> response = new HashMap<>();
        response.put("passports", passportDTOs);
        response.put("currentPage", passportPage.getNumber());
        response.put("totalItems", passportPage.getTotalElements());
        response.put("totalPages", passportPage.getTotalPages());

        log.info("Найдено {} паспорт(ов), страница {}/{}", passportDTOs.size(), page, passportPage.getTotalPages());
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Получить паспорт по ID", description = "Возвращает паспорт по указанному ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Паспорт найден"),
            @ApiResponse(responseCode = "404", description = "Паспорт не найден")
    })
    @GetMapping("/{id}")
    public ResponseEntity<PassportDTO> getPassportById(@PathVariable Long id) {
        log.info("Получен запрос: GET /api/passports/{} — получить паспорт по ID", id);

        return passportRepository.findById(id)
                .map(userMapper::toDto)
                .map(dto -> {
                    log.info("Паспорт с ID={} найден: {}", id, dto);
                    return ResponseEntity.ok(dto);
                })
                .orElseGet(() -> {
                    log.warn("Паспорт с ID={} не найден", id);
                    return ResponseEntity.notFound().build();
                });
    }

    @Operation(summary = "Создать новый паспорт", description = "Создаёт новый паспорт на основе переданных данных")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Паспорт успешно создан"),
            @ApiResponse(responseCode = "400", description = "Некорректные входные данные")
    })
    @PostMapping
    public ResponseEntity<PassportDTO> createPassport(@Valid @RequestBody PassportDTO passportDTO) {
        log.info("Получен запрос: POST /api/passports — создать паспорт");
        log.debug("Входящий DTO: {}", passportDTO);

        Passport saved = passportRepository.save(userMapper.toEntity(passportDTO));
        PassportDTO savedDTO = userMapper.toDto(saved);

        kafkaTemplate.send("passport-updates", String.valueOf(saved.getId()), savedDTO);
        log.info("Паспорт успешно создан с ID={}", saved.getId());
        return ResponseEntity.ok(savedDTO);
    }

    @Operation(summary = "Обновить паспорт", description = "Обновляет существующий паспорт по указанному ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Паспорт успешно обновлён"),
            @ApiResponse(responseCode = "404", description = "Паспорт не найден"),
            @ApiResponse(responseCode = "400", description = "Некорректные входные данные")
    })
    @PutMapping("/{id}")
    public ResponseEntity<PassportDTO> updatePassport(@PathVariable Long id, @Valid @RequestBody PassportDTO passportDTO) {
        log.info("Получен запрос: PUT /api/passports/{} — обновить паспорт", id);
        log.debug("Входящий DTO: {}", passportDTO);

        return passportRepository.findById(id)
                .map(existing -> {
                    Passport updated = userMapper.toEntity(passportDTO);
                    updated.setId(id);
                    Passport saved = passportRepository.save(updated);
                    PassportDTO savedDTO = userMapper.toDto(saved);

                    kafkaTemplate.send("passport-updates", String.valueOf(id), savedDTO);
                    log.info("Паспорт с ID={} успешно обновлён", id);
                    return ResponseEntity.ok(savedDTO);
                })
                .orElseGet(() -> {
                    log.warn("Обновление невозможно: паспорт с ID={} не найден", id);
                    return ResponseEntity.notFound().build();
                });
    }
}