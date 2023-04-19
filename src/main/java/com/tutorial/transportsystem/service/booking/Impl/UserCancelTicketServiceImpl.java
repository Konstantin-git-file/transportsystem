package com.tutorial.transportsystem.service.booking.Impl;

import com.tutorial.transportsystem.entity.Ticket;
import com.tutorial.transportsystem.entity.Train;
import com.tutorial.transportsystem.payload.responce.UserCancelTicketServiceRs;
import com.tutorial.transportsystem.repository.TicketRepository;
import com.tutorial.transportsystem.service.booking.UserCancelTicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserCancelTicketServiceImpl implements UserCancelTicketService {

    private final TicketRepository ticketRepository;

    @Override
    public UserCancelTicketServiceRs cancelTicket(Long ticketId) {

        Optional<Ticket> optionalTicket = ticketRepository.findById(ticketId);
        if (!optionalTicket.isPresent()) {
            throw new RuntimeException("Ticket with ID " + ticketId + " not found");
        }

        Ticket ticket = optionalTicket.get();
        if (!ticket.isBooked()) {
            throw new RuntimeException("Ticket with ID " + ticketId + " is not yet booked");
        }

        ticket.setBooked(false);
        ticketRepository.delete(ticket);

        Train train = ticket.getTrain();
        train.setBookedSeatCount(train.getBookedSeatCount() - 1);
        train.setSoldOut(train.getSeatCount() > train.getBookedSeatCount() ? false : true);

        return new UserCancelTicketServiceRs("Ticket with ID" + ticketId + " was cancelled");
    }
}
