package com.tutorial.transportsystem.service.mapper;

import com.tutorial.transportsystem.entity.Town;
import com.tutorial.transportsystem.service.dto.TownDto;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "cdi")
public interface TownEntityMapper {
    Town townEntityDtoToTownEntity(TownDto townDto);

    TownDto townEntityToTownEntityDto(Town town);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Town updateTownEntityFromTownEntityDto(TownDto townDto, @MappingTarget Town town);
}
