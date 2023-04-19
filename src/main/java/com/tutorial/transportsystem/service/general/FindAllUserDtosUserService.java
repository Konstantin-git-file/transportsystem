package com.tutorial.transportsystem.service.general;

import com.tutorial.transportsystem.dto.generalDto.UserDto;

import java.util.List;

public interface FindAllUserDtosUserService {
  List<UserDto> findAllUserDtos(Integer limit, Integer offset);
}
