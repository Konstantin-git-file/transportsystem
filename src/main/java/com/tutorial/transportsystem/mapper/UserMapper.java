package com.tutorial.transportsystem.mapper;

import com.tutorial.transportsystem.dto.UserDto;
import com.tutorial.transportsystem.entity.User;

import java.util.List;

public interface UserMapper {

    UserDto userToUserDto(User user);

    List<UserDto> usersToUserDTO(List<User> users);
}

//    List<UserDto> usersToUserDTO(List<User> users);
//
//    List<User> userDTOsToUser(List<UserDto> userDTOs);
//
//    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
//    User updateUserFromUserDto(UserDto userDto, @MappingTarget User user);

