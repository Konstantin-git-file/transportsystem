package com.tutorial.transportsystem.service.mapper;

import com.tutorial.transportsystem.entity.User;
import com.tutorial.transportsystem.service.dto.UserDto;
import org.mapstruct.*;

import java.util.List;

@Mapper(
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = "cdi"
)
public interface UserMapper {

    User userDtoToUser(UserDto userDto);

    List<User> userDTOsToUser(List<UserDto> userDTOs);

    UserDto userToUserDto(User user);

    List<UserDto> usersToUserDTO(List<User> users);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    User updateUserFromUserDto(UserDto userDto, @MappingTarget User user);
}
