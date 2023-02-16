package com.tutorial.transportsystem.service.impl;

import com.tutorial.transportsystem.dto.UserDto;
import com.tutorial.transportsystem.mapper.impl.UserMapperImpl;
import com.tutorial.transportsystem.repository.UserRepository;
import com.tutorial.transportsystem.service.UpdateUserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UpdateUserDtoImpl implements UpdateUserDto {

    private final UserRepository userRepository;
    private final UserMapperImpl userMapper;

    @Override
    public UserDto updateUserDto(Long id, UserDto userDto) {
        return null;
//        UserDto userDtoUpdated = userRepository.findById(id).get();
//        return userRepository.saveAndFlush(userDtoUpdated);
        }
}
