package com.tutorial.transportsystem.dto;

import com.tutorial.transportsystem.entity.CityAndStation;
import lombok.Data;

@Data
public class TicketDTO {
    private Long id;
    private boolean booked;
    private boolean paid;
    private CityAndStation cityAndStation;
    private UserDTO user;
    private PassportDTO passport;
    private CurrentLocationDTO currentLocation;
    private DestinationDTO destination;
}