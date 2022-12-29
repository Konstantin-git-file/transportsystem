package com.tutorial.transportsystem.service.mapper;

import com.tutorial.transportsystem.entity.User;
import com.tutorial.transportsystem.service.dto.UserDto;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
public class UserMapperImpl implements UserMapper {

    @Override
    public User userDtoToUser(UserDto userDto) {
        if (userDto == null) {
            return null;
        } else {
            User user = new User();
            user.setId(user.getId());
            user.setFirstname(user.getFirstname());
            user.setLastname(user.getLastname());
            user.setPassport(user.getPassport());
            user.setTicket(user.getTicket());
            return user;
        }
    }

    @Override
    public List<User> userDTOsToUser(List<UserDto> userDto) {
        return null;
    }

    @Override
    public UserDto userToUserDto(User user) {
        return UserDto.builder()
                .createdAt(user.getCreatedAt())
                .id(user.getId())
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
//                .passport(user.getPassport())
//                .ticket(user.getTicket())
//                .user(Optional.ofNullable(user.()).map(this::userToUserDto).orElse(null))
                .build();
    }

    @Override
    public List<UserDto> usersToUserDTO(List<User> users) {
        return null;
    }

    @Override
    public User updateUserFromUserDto(UserDto userDto, User user) {
        return null;
    }
}
