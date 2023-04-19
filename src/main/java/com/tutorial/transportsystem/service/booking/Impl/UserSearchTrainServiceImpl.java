package com.tutorial.transportsystem.service.booking.Impl;

import com.tutorial.transportsystem.dto.generalDto.TrainDto;
import com.tutorial.transportsystem.dto.userBookingDto.UserSearchDto;
import com.tutorial.transportsystem.entity.Town;
import com.tutorial.transportsystem.entity.Train;
import com.tutorial.transportsystem.repository.TownRepository;
import com.tutorial.transportsystem.repository.TrainRepository;
import com.tutorial.transportsystem.service.booking.UserSearchTrainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserSearchTrainServiceImpl implements UserSearchTrainService {

    private final TrainRepository trainRepository;
    private final TownRepository townRepository;

//    @Override
//    public List <TrainDto> searchTrains(UserSearchDto ) {
//
//        return trainRepository.findByCurrentLocationAndDestination(currentLocation, destination);
//    }

    @Override
    public List<TrainDto> searchTrains(UserSearchDto rq) {

        Town currentTown = townRepository.findById(rq.getCurrentTown().getId()).orElseThrow(() -> new RuntimeException());
        Town destTown = townRepository.findById(rq.getDestinationTown().getId()).orElseThrow(() -> new RuntimeException());

        LocalDateTime startDepartureTime = rq.getDepartureTime().atStartOfDay();
        LocalDateTime endDepartureTime = startDepartureTime.plusDays(1L);

        List<Train> trains = trainRepository.findByCurrentLocationAndDestinationAndDepartureTimeBetweenAndIsSoldOutFalse(
                currentTown,
                destTown,
                startDepartureTime,
                endDepartureTime);

        return trainToTrainDto(trains);
    }

    private List<TrainDto> trainToTrainDto(List<Train> trains) {
        return trains.stream()
                .filter(Objects::nonNull)
                .map(this::trainToTrainDto)
                .collect(Collectors.toList());
    }

    private TrainDto trainToTrainDto(Train train) {
        TrainDto trainDto = new TrainDto();
        trainDto.setId(train.getId());
        trainDto.setTrainName(train.getTrainName());
        trainDto.setDepartureTime(train.getDepartureTime());
        trainDto.setArrivalTime(train.getArrivalTime());
//        trainDto.setCurrentLocation(train.getCurrentLocation());
//        trainDto.setDestination(train.getDestination());
        trainDto.setAvailibleSeatCount(train.getSeatCount() - train.getBookedSeatCount());
        return trainDto;
    }

}
