package com.tutorial.transportsystem.service.general.impl;

import com.tutorial.transportsystem.service.general.UpdateUserDto;
import com.tutorial.transportsystem.dto.generalDto.UserDto;
import com.tutorial.transportsystem.entity.User;
import com.tutorial.transportsystem.mapper.impl.UserMapperImpl;
import com.tutorial.transportsystem.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateUserDtoImpl implements UpdateUserDto {

    private final UserRepository userRepository;
    private final UserMapperImpl userMapper;
    private final PasswordEncoder encoder;

    @Override
    public UserDto updateUserDto(long id, UserDto userDto) {
        UserDto userDtoNew = null;
        if (userRepository.findById(id).isPresent()) {
//TODO проверка на ноль
            User existingUser = userRepository.findById(id).get();
            existingUser.setPassword(encoder.encode((existingUser.getPassword())));
            existingUser.setLogin(userDto.getLogin());
            existingUser.setFirstname(userDto.getFirstname());
            existingUser.setLastname(userDto.getLastname());
            existingUser.setEmail(userDto.getEmail());
//      existingUser.setPassport(userDto.getPassport());
            userRepository.save(existingUser);
            userDtoNew = userMapper.userToUserDto(existingUser);
            return userDtoNew;
        }
        return userDtoNew;
    }
}
