package com.tutorial.transportsystem.mapper;

import com.tutorial.transportsystem.dto.PassportDTO;
import com.tutorial.transportsystem.entity.Passport;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PassportMapper {
    PassportDTO toDto(Passport entity);
    Passport toEntity(PassportDTO dto);
}
