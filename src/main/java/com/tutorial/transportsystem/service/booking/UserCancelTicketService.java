package com.tutorial.transportsystem.service.booking;

import com.tutorial.transportsystem.payload.responce.UserCancelTicketServiceRs;

public interface UserCancelTicketService {

    UserCancelTicketServiceRs cancelTicket(Long ticketId);
}
