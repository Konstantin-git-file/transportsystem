package com.tutorial.transportsystem.service.general;

import com.tutorial.transportsystem.dto.generalDto.UserDto;

public interface UpdateUserDto {
  UserDto updateUserDto(long id, UserDto userDto);
}
