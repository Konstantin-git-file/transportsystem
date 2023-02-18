package com.tutorial.transportsystem.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class TicketDto implements Serializable {
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSS")
  private LocalDateTime createdAt;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSS")
  private LocalDateTime updatedAt;

  private Long id;
  private UserDto user;
  private PassportDto passport;
  private Boolean booked;
  private Boolean paid;
  private CurrentLocationDto currentLocation;
  private DestinationDto destination;
}
