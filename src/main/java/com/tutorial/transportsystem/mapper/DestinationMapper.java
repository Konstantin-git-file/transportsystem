package com.tutorial.transportsystem.mapper;

import com.tutorial.transportsystem.dto.DestinationDto;
import com.tutorial.transportsystem.entity.Destination;
import org.mapstruct.Mapper;

@Mapper
public interface DestinationMapper {
    Destination destinationDtoToDestination(DestinationDto destinationDto);

    DestinationDto destinationToDestinationDto(Destination destination);


}
