package com.tutorial.transportsystem.mapper;

import com.tutorial.transportsystem.dto.UserDto;
import com.tutorial.transportsystem.entity.User;
import org.mapstruct.*;

import java.util.List;

@Mapper(
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = "spring",
        uses = {PassportMapper.class, TicketMapper.class}
)
public interface UserMapper {

    UserDto userToUserDto(User user);

    User userDtoToUser(UserDto userDto);

    List<UserDto> usersToUserDTO(List<User> users);

    List<User> userDTOsToUser(List<UserDto> userDTOs);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    User updateUserFromUserDto(UserDto userDto, @MappingTarget User user);
}
