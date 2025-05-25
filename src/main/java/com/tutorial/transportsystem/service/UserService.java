package com.tutorial.transportsystem.service;

import com.tutorial.transportsystem.dto.LoginDTO;
import com.tutorial.transportsystem.dto.UserDTO;
import com.tutorial.transportsystem.entity.User;
import com.tutorial.transportsystem.mapper.UserMapper;
import com.tutorial.transportsystem.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UserService {
    UserDTO register(UserDTO userDTO);
    Long login(LoginDTO loginDTO);
    UserDTO update(Long id, UserDTO userDTO);
    void delete(Long id);
    UserDTO getById(Long id);
    List<UserDTO> getAll(int page, int size, String lastname);
}