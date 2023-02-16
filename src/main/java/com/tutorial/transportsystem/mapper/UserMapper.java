package com.tutorial.transportsystem.mapper;

import com.tutorial.transportsystem.dto.UserDto;
import com.tutorial.transportsystem.entity.User;

import java.util.List;

public interface UserMapper {

    UserDto userToUserDto(User user);

    List<UserDto> usersToUserDTO(List<User> users);
}

