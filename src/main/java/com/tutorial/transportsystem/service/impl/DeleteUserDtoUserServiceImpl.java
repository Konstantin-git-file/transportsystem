package com.tutorial.transportsystem.service.impl;

import com.tutorial.transportsystem.entity.User;
import com.tutorial.transportsystem.exception.ErrorEnum;
import com.tutorial.transportsystem.exception.TransportSystemException;
import com.tutorial.transportsystem.repository.UserRepository;
import com.tutorial.transportsystem.service.DeleteUserDtoUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DeleteUserDtoUserServiceImpl implements DeleteUserDtoUserService {

    private final UserRepository userRepository;

    @Override
    public void deleteUserDto(Long id) {
        Optional<User> user = Optional.of(userRepository.getReferenceById(id));
        if (user.isPresent()) {
            userRepository.deleteById(id);
        } else {
            throw new TransportSystemException(ErrorEnum.USER_DOESNT_EXIST);
        }
    }
}
