package com.tutorial.transportsystem.service.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tutorial.transportsystem.entity.User;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.sql.Date;

@Data
@Builder
public class TicketDto implements Serializable {
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    private final Date createdAt;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    private final Long id;
    private final User user;
    private final PassportDto passport;
    private final Boolean booked;
    private final Boolean paid;
    private final CurrentLocationDto currentLocation;
    private final DestinationDto destination;
}
