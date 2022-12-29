package com.tutorial.transportsystem.service.mapper;

import com.tutorial.transportsystem.entity.CurrentLocation;
import com.tutorial.transportsystem.service.dto.CurrentLocationDto;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "cdi")
public interface CurrentLocationMapper {
    CurrentLocation currentLocationDtoToCurrentLocation(CurrentLocationDto currentLocationDto);

    CurrentLocationDto currentLocationToCurrentLocationDto(CurrentLocation currentLocation);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    CurrentLocation updateCurrentLocationFromCurrentLocationDto(CurrentLocationDto currentLocationDto, @MappingTarget CurrentLocation currentLocation);
}
