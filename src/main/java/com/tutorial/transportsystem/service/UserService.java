package com.tutorial.transportsystem.service;

import com.tutorial.transportsystem.dto.UserDto;
import com.tutorial.transportsystem.entity.User;
import org.springframework.stereotype.Service;

// unittest
@Service
public interface UserService {

    void createUserDto(UserDto userDto);

//    UserDto updateUserDto(Long id, UserDto userDto);

//    UserDto getUserById(Long id);

//    List<UserDto> findAllUserDtos(Integer limit, Integer offset);

//    void deleteUserDto(Long id);

    User findUserByEmail(String email);
}
