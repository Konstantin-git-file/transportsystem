package com.tutorial.transportsystem.mapper;

import com.tutorial.transportsystem.dto.*;
import com.tutorial.transportsystem.entity.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "ticket", ignore = true)
    User toEntity(UserDTO userDTO);

    @Mapping(target = "ticketId", source = "ticket.id")
    UserDTO toDto(User user);

    @Mapping(target = "ticket", ignore = true)
    @Mapping(target = "password", ignore = true)
    void update(@MappingTarget User user, UserDTO userDTO);

    Passport toEntity(PassportDTO passportDTO);
    PassportDTO toDto(Passport passport);

    TicketDTO toDto(Ticket ticket);
    Ticket toEntity(TicketDTO ticketDTO);

    CurrentLocationDTO toDto(CurrentLocation location);
    CurrentLocation toEntity(CurrentLocationDTO locationDTO);

    DestinationDTO toDto(Destination destination);
    Destination toEntity(DestinationDTO destinationDTO);
}