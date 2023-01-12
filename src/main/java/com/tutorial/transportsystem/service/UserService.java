package com.tutorial.transportsystem.service;

import com.tutorial.transportsystem.dto.UserDto;
import com.tutorial.transportsystem.entity.User;
import com.tutorial.transportsystem.exception.RecordNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

//unittest
@Service
public interface UserService extends UserDetailsService {
    //pageable

    void createUserDto(UserDto userDto);

    UserDto updateUserDto(Long id, UserDto userDto);

    UserDto getUserById(Long id) throws RecordNotFoundException;

    Page<UserDto> findAllUserDtos(Pageable pageable);

    void deleteUserDto(Long id) throws RecordNotFoundException;

    User findUserByEmail(String email);
}
