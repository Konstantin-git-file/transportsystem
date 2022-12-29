package com.tutorial.transportsystem.service.mapper;

import com.tutorial.transportsystem.entity.Destination;
import com.tutorial.transportsystem.service.dto.DestinationDto;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "cdi")
public interface DestinationMapper {
    Destination destinationDtoToDestination(DestinationDto destinationDto);

    DestinationDto destinationToDestinationDto(Destination destination);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Destination updateDestinationFromDestinationDto(DestinationDto destinationDto, @MappingTarget Destination destination);
}
