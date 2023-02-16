package com.tutorial.transportsystem.mapper;

import com.tutorial.transportsystem.entity.Station;
import com.tutorial.transportsystem.dto.StationDto;
import org.mapstruct.*;

@Mapper
public interface StationEntityMapper {
    Station stationEntityDtoToStationEntity(StationDto stationDto);

    StationDto stationEntityToStationEntityDto(Station station);


}
