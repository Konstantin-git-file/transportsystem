package com.tutorial.transportsystem.controllers;

import com.tutorial.transportsystem.dto.LoginDTO;
import com.tutorial.transportsystem.dto.UserDTO;
import com.tutorial.transportsystem.entity.User;
import com.tutorial.transportsystem.mapper.UserMapper;
import com.tutorial.transportsystem.repository.UserRepository;
import com.tutorial.transportsystem.service.UserService;
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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserDTO> register(@RequestBody @Valid UserDTO userDTO) {
        log.info("Регистрация пользователя: {}", userDTO.getLogin());
        return ResponseEntity.ok(userService.register(userDTO));
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, Long>> login(@RequestBody @Valid LoginDTO loginDTO) {
        log.info("Попытка входа: {}", loginDTO.getLogin());
        Long id = userService.login(loginDTO);
        log.info("Вход выполнен. ID={}", id);
        return ResponseEntity.ok(Map.of("userId", id));
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAll(@RequestParam(defaultValue = "0") int page,
                                                @RequestParam(defaultValue = "10") int size,
                                                @RequestParam(required = false) String lastname) {
        return ResponseEntity.ok(userService.getAll(page, size, lastname));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> update(@PathVariable Long id, @RequestBody @Valid UserDTO userDTO) {
        return ResponseEntity.ok(userService.update(id, userDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}