package com.tutorial.transportsystem.dto;

import lombok.Data;

@Data
public class UserDTO {
    private Long id;
    private String firstname;
    private String lastname;
    private PassportDTO passport;
    private TicketDTO ticket;
}