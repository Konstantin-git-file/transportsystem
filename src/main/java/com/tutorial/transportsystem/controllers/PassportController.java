package com.tutorial.transportsystem.controllers;

import com.tutorial.transportsystem.dto.PassportDTO;
import com.tutorial.transportsystem.entity.Passport;
import com.tutorial.transportsystem.mapper.UserMapper;
import com.tutorial.transportsystem.repository.PassportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/passports")
@RequiredArgsConstructor
public class PassportController {

    private final PassportRepository passportRepository;
    private final UserMapper userMapper;

    @GetMapping
    public ResponseEntity<List<PassportDTO>> getAllPassports() {
        List<PassportDTO> list = passportRepository.findAll().stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PassportDTO> getPassportById(@PathVariable Long id) {
        return passportRepository.findById(id)
                .map(userMapper::toDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<PassportDTO> createPassport(@RequestBody PassportDTO passportDTO) {
        Passport saved = passportRepository.save(userMapper.toEntity(passportDTO));
        return ResponseEntity.ok(userMapper.toDto(saved));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PassportDTO> updatePassport(@PathVariable Long id, @RequestBody PassportDTO passportDTO) {
        return passportRepository.findById(id)
                .map(existing -> {
                    Passport updated = userMapper.toEntity(passportDTO);
                    updated.setId(id);
                    return ResponseEntity.ok(userMapper.toDto(passportRepository.save(updated)));
                })
                .orElse(ResponseEntity.notFound().build());
    }
}

