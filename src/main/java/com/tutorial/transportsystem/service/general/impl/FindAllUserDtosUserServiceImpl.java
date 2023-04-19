package com.tutorial.transportsystem.service.general.impl;

import com.tutorial.transportsystem.dto.generalDto.UserDto;
import com.tutorial.transportsystem.mapper.impl.UserMapperImpl;
import com.tutorial.transportsystem.repository.UserRepository;
import com.tutorial.transportsystem.service.general.FindAllUserDtosUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FindAllUserDtosUserServiceImpl implements FindAllUserDtosUserService {

  private final UserRepository userRepository;
  private final UserMapperImpl userMapper;

  @Override
  public List<UserDto> findAllUserDtos(Integer limit, Integer offset) {
    return userMapper.usersToUserDTO(
        userRepository.findAll(PageRequest.of(offset, limit)).toList());
  }
}
