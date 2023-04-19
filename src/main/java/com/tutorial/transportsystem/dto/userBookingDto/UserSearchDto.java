package com.tutorial.transportsystem.dto.userBookingDto;

import com.tutorial.transportsystem.dto.generalDto.TownDto;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
public class UserSearchDto implements Serializable {

    private TownDto currentTown;
    private TownDto destinationTown;
    private LocalDate departureTime;
}
