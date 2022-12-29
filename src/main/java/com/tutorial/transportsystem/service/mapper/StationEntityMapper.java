package com.tutorial.transportsystem.service.mapper;

import com.tutorial.transportsystem.entity.Station;
import com.tutorial.transportsystem.service.dto.StationDto;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "cdi")
public interface StationEntityMapper {
    Station stationEntityDtoToStationEntity(StationDto stationDto);

    StationDto stationEntityToStationEntityDto(Station station);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Station updateStationEntityFromStationEntityDto(StationDto stationDto, @MappingTarget Station station);
}
