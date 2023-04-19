package com.tutorial.transportsystem.service.booking;

import com.tutorial.transportsystem.dto.generalDto.TrainDto;
import com.tutorial.transportsystem.dto.userBookingDto.UserSearchDto;

import java.util.List;

public interface UserSearchTrainService {

    List <TrainDto> searchTrains(UserSearchDto rq);

}
