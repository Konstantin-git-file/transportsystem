package com.tutorial.transportsystem.service.general;

import com.tutorial.transportsystem.dto.generalDto.UserDto;

public interface GetUserByIdService {
  UserDto getUserById(Long id);
}
