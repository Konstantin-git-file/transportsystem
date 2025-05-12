package com.tutorial.transportsystem.controllers;

import com.tutorial.transportsystem.dto.UserDTO;
import com.tutorial.transportsystem.entity.User;
import com.tutorial.transportsystem.mapper.UserMapper;
import com.tutorial.transportsystem.repository.UserRepository;
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
@RequestMapping("/api/users")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final KafkaTemplate<String, UserDTO> kafkaTemplate;

    @Operation(summary = "Получить список пользователей", description = "Возвращает постраничный список пользователей с возможностью фильтрации по lastname")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Список пользователей успешно возвращён"),
            @ApiResponse(responseCode = "400", description = "Некорректные параметры запроса")
    })
    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllUsers(
            @RequestParam(required = false) String lastname,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        log.info("Получен запрос: GET /api/users?page={}&size={} — получить всех пользователей", page, size);
        if (page < 0 || size <= 0) {
            log.warn("Некорректные параметры запроса: page={}, size={}", page, size);
            return ResponseEntity.badRequest().build();
        }

        PageRequest pageRequest = PageRequest.of(page, size, Sort.by("lastname"));
        Page<User> userPage = lastname != null && !lastname.isEmpty()
                ? userRepository.findByLastnameContainingIgnoreCase(lastname, pageRequest)
                : userRepository.findAll(pageRequest);

        List<UserDTO> userDTOs = userPage.getContent().stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList());

        Map<String, Object> response = new HashMap<>();
        response.put("users", userDTOs);
        response.put("currentPage", userPage.getNumber());
        response.put("totalItems", userPage.getTotalElements());
        response.put("totalPages", userPage.getTotalPages());

        log.info("Найдено {} пользователь(ей), страница {}/{}", userDTOs.size(), page, userPage.getTotalPages());
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Получить пользователя по ID", description = "Возвращает пользователя по указанному ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Пользователь найден"),
            @ApiResponse(responseCode = "404", description = "Пользователь не найден")
    })
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

    @Operation(summary = "Создать нового пользователя", description = "Создаёт нового пользователя на основе переданных данных")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Пользователь успешно создан"),
            @ApiResponse(responseCode = "400", description = "Некорректные входные данные")
    })
    @PostMapping
    public ResponseEntity<UserDTO> createUser(@Valid @RequestBody UserDTO userDTO) {
        log.info("Получен запрос: POST /api/users — создать пользователя");
        log.debug("Входящий DTO: {}", userDTO);

        User saved = userRepository.save(userMapper.toEntity(userDTO));
        UserDTO savedDTO = userMapper.toDto(saved);

        kafkaTemplate.send("user-updates", String.valueOf(saved.getId()), savedDTO);
        log.info("Пользователь успешно создан с ID={}", saved.getId());
        return ResponseEntity.ok(savedDTO);
    }

    @Operation(summary = "Обновить пользователя", description = "Обновляет существующего пользователя по указанному ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Пользователь успешно обновлён"),
            @ApiResponse(responseCode = "404", description = "Пользователь не найден"),
            @ApiResponse(responseCode = "400", description = "Некорректные входные данные")
    })
    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Long id, @Valid @RequestBody UserDTO userDTO) {
        log.info("Получен запрос: PUT /api/users/{} — обновить пользователя", id);
        log.debug("Входящий DTO: {}", userDTO);

        return userRepository.findById(id)
                .map(existing -> {
                    User updated = userMapper.toEntity(userDTO);
                    updated.setId(id);
                    User saved = userRepository.save(updated);
                    UserDTO savedDTO = userMapper.toDto(saved);

                    kafkaTemplate.send("user-updates", String.valueOf(id), savedDTO);
                    log.info("Пользователь с ID={} успешно обновлён", id);
                    return ResponseEntity.ok(savedDTO);
                })
                .orElseGet(() -> {
                    log.warn("Обновление невозможно: пользователь с ID={} не найден", id);
                    return ResponseEntity.notFound().build();
                });
    }
}