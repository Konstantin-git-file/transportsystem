package com.tutorial.transportsystem.controllers;
import com.tutorial.transportsystem.dto.PassportDTO;
import com.tutorial.transportsystem.service.PassportService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/passports")
@RequiredArgsConstructor
@Slf4j
public class PassportController {

    private final PassportService passportService;

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

        List<PassportDTO> passportDTOs = passportService.getAll(page, size, serial);

        Map<String, Object> response = new HashMap<>();
        response.put("passports", passportDTOs);
        response.put("currentPage", page);
        response.put("totalItems", passportDTOs.size());
        response.put("totalPages", (int) Math.ceil((double) passportDTOs.size() / size));

        log.info("Найдено {} паспорт(ов), страница {}", passportDTOs.size(), page);
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

        try {
            PassportDTO dto = passportService.getById(id);
            log.info("Паспорт с ID={} найден", id);
            return ResponseEntity.ok(dto);
        } catch (Exception e) {
            log.warn("Паспорт с ID={} не найден", id);
            return ResponseEntity.notFound().build();
        }
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

        PassportDTO savedDTO = passportService.create(passportDTO);
        log.info("Паспорт успешно создан с ID={}", savedDTO.getId());
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

        try {
            PassportDTO updatedDTO = passportService.update(id, passportDTO);
            log.info("Паспорт с ID={} успешно обновлён", id);
            return ResponseEntity.ok(updatedDTO);
        } catch (Exception e) {
            log.warn("Обновление невозможно: паспорт с ID={} не найден", id);
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Удалить паспорт", description = "Удаляет паспорт по указанному ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Паспорт успешно удалён"),
            @ApiResponse(responseCode = "404", description = "Паспорт не найден")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePassport(@PathVariable Long id) {
        log.info("Получен запрос: DELETE /api/passports/{} — удалить паспорт", id);

        try {
            passportService.delete(id);
            log.info("Паспорт с ID={} успешно удалён", id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            log.warn("Удаление невозможно: паспорт с ID={} не найден", id);
            return ResponseEntity.notFound().build();
        }
    }
}
