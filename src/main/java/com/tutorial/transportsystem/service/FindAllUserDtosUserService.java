package com.tutorial.transportsystem.service;

import com.tutorial.transportsystem.dto.UserDto;

import java.util.List;

public interface FindAllUserDtosUserService {
    List<UserDto> findAllUserDtos(Integer limit, Integer offset);
}
