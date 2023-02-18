 package com.tutorial.transportsystem.mapper;

 import com.tutorial.transportsystem.entity.Ticket;
 import com.tutorial.transportsystem.dto.TicketDto;
 import org.mapstruct.*;

 @Mapper
 public interface TicketMapper {

    Ticket ticketDtoToTicket(TicketDto ticketDto);

    TicketDto ticketToTicketDto(Ticket ticket);

 }
