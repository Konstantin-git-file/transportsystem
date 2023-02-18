 package com.tutorial.transportsystem.mapper;

 import com.tutorial.transportsystem.entity.Town;
 import com.tutorial.transportsystem.dto.TownDto;
 import org.mapstruct.*;

 @Mapper
 public interface TownEntityMapper {

    Town townEntityDtoToTownEntity(TownDto townDto);

    TownDto townEntityToTownEntityDto(Town town);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Town updateTownEntityFromTownEntityDto(TownDto townDto, @MappingTarget Town town);
 }
