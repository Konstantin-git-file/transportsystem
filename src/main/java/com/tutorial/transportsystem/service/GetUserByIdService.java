package com.tutorial.transportsystem.service;

import com.tutorial.transportsystem.dto.UserDto;

public interface GetUserByIdService {
    UserDto getUserById(Long id);
}
