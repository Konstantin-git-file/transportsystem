package com.tutorial.transportsystem.mapper;

import com.tutorial.transportsystem.dto.generalDto.PassportDto;
import com.tutorial.transportsystem.entity.Passport;
import org.mapstruct.Mapper;

@Mapper
public interface PassportMapper {
    Passport passportDtoToPassport(PassportDto passportDto);

    PassportDto passportToPassportDto(Passport passport);
}
