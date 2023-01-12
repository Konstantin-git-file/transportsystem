package com.tutorial.transportsystem.mapper;

import com.tutorial.transportsystem.entity.CurrentLocation;
import com.tutorial.transportsystem.dto.CurrentLocationDto;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "cdi")
public interface CurrentLocationMapper {
    CurrentLocation currentLocationDtoToCurrentLocation(CurrentLocationDto currentLocationDto);

    CurrentLocationDto currentLocationToCurrentLocationDto(CurrentLocation currentLocation);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    CurrentLocation updateCurrentLocationFromCurrentLocationDto(CurrentLocationDto currentLocationDto, @MappingTarget CurrentLocation currentLocation);
}
