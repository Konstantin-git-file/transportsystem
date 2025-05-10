package com.tutorial.transportsystem.controllers;

import com.tutorial.transportsystem.dto.UserDTO;
import com.tutorial.transportsystem.entity.User;
import com.tutorial.transportsystem.mapper.UserMapper;
import com.tutorial.transportsystem.repository.UserRepository;
import lombok.RequiredArgsConstructor;
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
public class UserController {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        if (page < 0 || size <= 0) {
            return ResponseEntity.badRequest().build();
        }

        Page<User> userPage = userRepository.findAll(PageRequest.of(page, size, Sort.by("lastname")));
        List<UserDTO> userDTOs = userPage.getContent().stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList());

        return ResponseEntity.ok(userDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
        return userRepository.findById(id)
                .map(userMapper::toDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {
        User saved = userRepository.save(userMapper.toEntity(userDTO));
        return ResponseEntity.ok(userMapper.toDto(saved));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Long id, @RequestBody UserDTO userDTO) {
        return userRepository.findById(id)
                .map(existing -> {
                    User updated = userMapper.toEntity(userDTO);
                    updated.setId(id);
                    return ResponseEntity.ok(userMapper.toDto(userRepository.save(updated)));
                })
                .orElse(ResponseEntity.notFound().build());
    }
}