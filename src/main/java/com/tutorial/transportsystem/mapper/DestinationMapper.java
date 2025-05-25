package com.tutorial.transportsystem.mapper;

import com.tutorial.transportsystem.dto.DestinationDTO;
import com.tutorial.transportsystem.entity.Destination;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DestinationMapper {
    DestinationDTO toDto(Destination entity);
    Destination toEntity(DestinationDTO dto);
}