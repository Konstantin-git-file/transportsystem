package com.tutorial.transportsystem.payload.responce;


import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class UserCancelTicketServiceRs {

    private String message;

    public UserCancelTicketServiceRs (String message) {
        this.message = message;
    }
}
