package com.tutorial.transportsystem.dto.userBookingDto;

import com.tutorial.transportsystem.dto.generalDto.TrainDto;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
// TODO все DTO
public class UserBookTicketDtoRq {

    private TrainDto trainDto;
    private Long userID;

//    private LocalDateTime localDateTime;
//
//    @NotNull
//    private CurrentLocation currentLocation;
//
//    @NotNull
//    private Destination destination;
//
//    @NotNull
//    private UserBookingDto userBookingDto;
}
