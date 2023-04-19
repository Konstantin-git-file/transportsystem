package com.tutorial.transportsystem.dto.userBookingDto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class UserBookTicketDtoRs {

    private String message;

    public UserBookTicketDtoRs(String message) {
        this.message = message;
    }
}
