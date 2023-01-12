package com.tutorial.transportsystem.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class TicketDto implements Serializable {
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    private final LocalDateTime createdAt;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    private final LocalDateTime updatedAt;
    private final Long id;
    private final UserDto user;
    private final PassportDto passport;
    private final Boolean booked;
    private final Boolean paid;
    private final CurrentLocationDto currentLocation;
    private final DestinationDto destination;
}