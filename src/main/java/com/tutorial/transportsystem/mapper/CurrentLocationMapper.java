package com.tutorial.transportsystem.mapper;

import com.tutorial.transportsystem.dto.CurrentLocationDTO;
import com.tutorial.transportsystem.entity.CurrentLocation;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CurrentLocationMapper {
    CurrentLocationDTO toDto(CurrentLocation entity);
    CurrentLocation toEntity(CurrentLocationDTO dto);
}
