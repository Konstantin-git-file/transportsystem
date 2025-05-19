package com.tutorial.transportsystem.mapper;

import com.tutorial.transportsystem.dto.TicketDTO;
import com.tutorial.transportsystem.entity.Ticket;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface TicketMapper {
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "currentLocation", ignore = true)
    @Mapping(target = "destination", ignore = true)
    @Mapping(target = "passport", ignore = true)
    Ticket toEntity(TicketDTO ticketDTO);

    @Mapping(target = "userId", source = "user.id")
    @Mapping(target = "locationId", source = "currentLocation.id")
    @Mapping(target = "destinationId", source = "destination.id")
    TicketDTO toDto(Ticket ticket);
    }