package com.tutorial.transportsystem.mapper;

import com.tutorial.transportsystem.dto.PassportDto;
import com.tutorial.transportsystem.entity.Passport;

//@Mapper
public interface PassportMapper {

  Passport passportDtoToPassport(PassportDto passportDto);

  PassportDto passportToPassportDto(Passport passport);
}
