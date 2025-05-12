package com.tutorial.transportsystem.controllers;

import com.tutorial.transportsystem.dto.PassportDTO;
import com.tutorial.transportsystem.entity.Passport;
import com.tutorial.transportsystem.mapper.UserMapper;
import com.tutorial.transportsystem.repository.PassportRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/passports")
@RequiredArgsConstructor
@Slf4j
public class PassportController {

    private final PassportRepository passportRepository;
    private final UserMapper userMapper;

    @GetMapping
    public ResponseEntity<List<PassportDTO>> getAllPassports() {
        log.info("Получен запрос: GET /api/passports — получить все паспорта");

        List<PassportDTO> list = passportRepository.findAll().stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList());

        log.info("Найдено {} паспорт(ов)", list.size());
        return ResponseEntity.ok(list);
    }

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

    @PostMapping
    public ResponseEntity<PassportDTO> createPassport(@RequestBody PassportDTO passportDTO) {
        log.info("Получен запрос: POST /api/passports — создать паспорт: {}", passportDTO);

        Passport saved = passportRepository.save(userMapper.toEntity(passportDTO));

        log.info("Паспорт успешно создан с ID={}", saved.getId());
        return ResponseEntity.ok(userMapper.toDto(saved));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PassportDTO> updatePassport(@PathVariable Long id, @Valid @RequestBody PassportDTO passportDTO) {
        log.info("Получен запрос: PUT /api/passports/{} — обновить паспорт: {}", id, passportDTO);

        return passportRepository.findById(id)
                .map(existing -> {
                    Passport updated = userMapper.toEntity(passportDTO);
                    updated.setId(id);
                    Passport saved = passportRepository.save(updated);
                    log.info("Паспорт с ID={} успешно обновлён", id);
                    return ResponseEntity.ok(userMapper.toDto(saved));
                })
                .orElseGet(() -> {
                    log.warn("Обновление невозможно: паспорт с ID={} не найден", id);
                    return ResponseEntity.notFound().build();
                });
    }
}
