package com.tutorial.transportsystem.service.booking;

import com.tutorial.transportsystem.dto.userBookingDto.UserBookTicketDtoRq;
import com.tutorial.transportsystem.dto.userBookingDto.UserBookTicketDtoRs;

public interface UserBookTicketService {

    //TODO create String paymentMethod
    UserBookTicketDtoRs bookTicket(UserBookTicketDtoRq rq);
}
