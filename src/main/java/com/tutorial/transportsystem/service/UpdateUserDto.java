package com.tutorial.transportsystem.service;

import com.tutorial.transportsystem.dto.UserDto;

public interface UpdateUserDto {
    UserDto updateUserDto(Long id, UserDto userDto);
}
