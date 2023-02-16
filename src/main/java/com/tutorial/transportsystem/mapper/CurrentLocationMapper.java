package com.tutorial.transportsystem.mapper;

import com.tutorial.transportsystem.dto.CurrentLocationDto;
import com.tutorial.transportsystem.entity.CurrentLocation;
import org.mapstruct.Mapper;

@Mapper
public interface CurrentLocationMapper {
    CurrentLocation currentLocationDtoToCurrentLocation(CurrentLocationDto currentLocationDto);

    CurrentLocationDto currentLocationToCurrentLocationDto(CurrentLocation currentLocation);


}
