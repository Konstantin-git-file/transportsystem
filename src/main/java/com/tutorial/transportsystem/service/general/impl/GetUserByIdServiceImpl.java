package com.tutorial.transportsystem.service.general.impl;

import com.tutorial.transportsystem.dto.generalDto.UserDto;
import com.tutorial.transportsystem.entity.User;
import com.tutorial.transportsystem.exception.ErrorEnum;
import com.tutorial.transportsystem.exception.TransportSystemException;
import com.tutorial.transportsystem.mapper.impl.UserMapperImpl;
import com.tutorial.transportsystem.repository.UserRepository;
import com.tutorial.transportsystem.service.general.GetUserByIdService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class GetUserByIdServiceImpl implements GetUserByIdService {

  private final UserRepository userRepository;
  private final UserMapperImpl userMapper;

  @Transactional
  @Override
  public UserDto getUserById(Long id) {
    User user =
        userRepository
            .findById(id)
            .orElseThrow(() -> new TransportSystemException(ErrorEnum.USER_DOESNT_EXIST));
    return userMapper.userToUserDto(user);
  }
}
