package com.tutorial.transportsystem.mapper;

import com.tutorial.transportsystem.entity.Station;
import com.tutorial.transportsystem.dto.StationDto;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "cdi")
public interface StationEntityMapper {
    Station stationEntityDtoToStationEntity(StationDto stationDto);

    StationDto stationEntityToStationEntityDto(Station station);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Station updateStationEntityFromStationEntityDto(StationDto stationDto, @MappingTarget Station station);
}
