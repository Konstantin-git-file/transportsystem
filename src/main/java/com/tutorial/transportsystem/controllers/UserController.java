package com.tutorial.transportsystem.controllers;

import com.tutorial.transportsystem.dto.UserDTO;
import com.tutorial.transportsystem.entity.User;
import com.tutorial.transportsystem.mapper.UserMapper;
import com.tutorial.transportsystem.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        log.info("Получен запрос: GET /api/users?page={}&size={} — получить всех пользователей", page, size);

        if (page < 0 || size <= 0) {
            log.warn("Некорректные параметры запроса: page={}, size={}", page, size);
            return ResponseEntity.badRequest().build();
        }

        Page<User> userPage = userRepository.findAll(PageRequest.of(page, size, Sort.by("lastname")));
        List<UserDTO> userDTOs = userPage.getContent().stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList());

        log.info("Найдено {} пользователь(ей)", userDTOs.size());
        return ResponseEntity.ok(userDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
        log.info("Получен запрос: GET /api/users/{} — получить пользователя по ID", id);

        return userRepository.findById(id)
                .map(userMapper::toDto)
                .map(dto -> {
                    log.info("Пользователь с ID={} найден: {}", id, dto);
                    return ResponseEntity.ok(dto);
                })
                .orElseGet(() -> {
                    log.warn("Пользователь с ID={} не найден", id);
                    return ResponseEntity.notFound().build();
                });
    }

    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {
        log.info("Получен запрос: POST /api/users — создать пользователя: {}", userDTO);

        User saved = userRepository.save(userMapper.toEntity(userDTO));

        log.info("Пользователь успешно создан с ID={}", saved.getId());
        return ResponseEntity.ok(userMapper.toDto(saved));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Long id, @RequestBody UserDTO userDTO) {
        log.info("Получен запрос: PUT /api/users/{} — обновить пользователя: {}", id, userDTO);

        return userRepository.findById(id)
                .map(existing -> {
                    User updated = userMapper.toEntity(userDTO);
                    updated.setId(id);
                    User saved = userRepository.save(updated);
                    log.info("Пользователь с ID={} успешно обновлён", id);
                    return ResponseEntity.ok(userMapper.toDto(saved));
                })
                .orElseGet(() -> {
                    log.warn("Обновление невозможно: пользователь с ID={} не найден", id);
                    return ResponseEntity.notFound().build();
                });
    }
}