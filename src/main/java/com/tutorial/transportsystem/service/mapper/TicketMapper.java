package com.tutorial.transportsystem.service.mapper;

import com.tutorial.transportsystem.entity.Ticket;
import com.tutorial.transportsystem.service.dto.TicketDto;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "cdi")
public interface TicketMapper {
    Ticket ticketDtoToTicket(TicketDto ticketDto);

    TicketDto ticketToTicketDto(Ticket ticket);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Ticket updateTicketFromTicketDto(TicketDto ticketDto, @MappingTarget Ticket ticket);
}
