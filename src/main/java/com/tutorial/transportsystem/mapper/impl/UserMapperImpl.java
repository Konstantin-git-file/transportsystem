package com.tutorial.transportsystem.mapper.impl;

import com.tutorial.transportsystem.dto.generalDto.UserDto;
import com.tutorial.transportsystem.entity.User;
import com.tutorial.transportsystem.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class UserMapperImpl implements UserMapper {

  @Override
  public UserDto userToUserDto(User user) {
    UserDto userDto = new UserDto();
    userDto.setCreatedAt(user.getCreatedAt());
    userDto.setUpdatedAt(user.getCreatedAt());
    //        userDto.setId(user.getId());
    userDto.setLogin((user.getLogin()));
    userDto.setEmail(user.getEmail());
    userDto.setFirstname(user.getFirstname());
    userDto.setLastname(user.getLastname());
    return userDto;
  }

  @Override
  public List<UserDto> usersToUserDTO(List<User> users) {
    return users.stream()
        .filter(Objects::nonNull)
        .map(this::userToUserDto)
        .collect(Collectors.toList());
  }
}
