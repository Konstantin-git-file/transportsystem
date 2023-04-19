package com.tutorial.transportsystem.service.booking.Impl;

import com.tutorial.transportsystem.dto.userBookingDto.UserBookTicketDtoRq;
import com.tutorial.transportsystem.dto.userBookingDto.UserBookTicketDtoRs;
import com.tutorial.transportsystem.entity.Ticket;
import com.tutorial.transportsystem.entity.Train;
import com.tutorial.transportsystem.repository.TicketRepository;
import com.tutorial.transportsystem.repository.TrainRepository;
import com.tutorial.transportsystem.service.booking.UserBookTicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserBookTicketServiceImpl implements UserBookTicketService {

    private final TrainRepository trainRepository;
    private final TicketRepository ticketRepository;

    @Override
    public UserBookTicketDtoRs bookTicket(UserBookTicketDtoRq rq) {

//
        Train train = trainRepository.findById(rq.getTrainDto().getId()).orElseThrow(() -> new RuntimeException(""));

//        if (train == null) {
//            throw new RuntimeException("Train with the requested location not found");
//        }


        if (train.getSeatCount() == train.getBookedSeatCount()) {
            throw new RuntimeException("All tickets sold out for this train");
        }

       Ticket ticket = new Ticket();
        Long ticketID = Long.valueOf(String.valueOf(UUID.randomUUID()));
        ticket.setId(ticketID);

//        ticket.setUser(rq.setUserBookingDto());

        ticket.setTrain(train);

        ticket.setBooked(true);
//      ticket.setPayment(payment);
//      train.setSeatCount(train.getSeatCount() - 1);
//      train.setBookedSeatCount(train.getBookedSeatCount() + 1);
        ticketRepository.save(ticket);

        return new UserBookTicketDtoRs("Ticket successfully purchased! Ticket ID: " + ticketID);
    }
}
