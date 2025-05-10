package com.tutorial.transportsystem.mapper;

import com.tutorial.transportsystem.dto.*;
import com.tutorial.transportsystem.entity.*;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDTO toDto(User user);
    User toEntity(UserDTO userDTO);

    TicketDTO toDto(Ticket ticket);
    Ticket toEntity(TicketDTO ticketDTO);

    PassportDTO toDto(Passport passport);
    Passport toEntity(PassportDTO passportDTO);

    CurrentLocationDTO toDto(CurrentLocation location);
    CurrentLocation toEntity(CurrentLocationDTO locationDTO);

    DestinationDTO toDto(Destination destination);
    Destination toEntity(DestinationDTO destinationDTO);
}
