package com.tutorial.transportsystem.dto.generalDto;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class TrainDto implements Serializable {
    private Long id;
    private String trainName;
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;
    private TownDto  currentLocation;
    private TownDto  destination;
    private int availibleSeatCount;


}
