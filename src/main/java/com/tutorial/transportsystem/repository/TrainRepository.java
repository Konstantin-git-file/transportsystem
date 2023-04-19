package com.tutorial.transportsystem.repository;

import com.tutorial.transportsystem.entity.CurrentLocation;
import com.tutorial.transportsystem.entity.Destination;
import com.tutorial.transportsystem.entity.Town;
import com.tutorial.transportsystem.entity.Train;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface TrainRepository extends JpaRepository<Train, Long> {
    @Query("select t from Train t where t.currentLocation = ?1 and t.destination = ?2")
    Train findByCurrentLocationAndDestinationAndDepartureTimeBetweenAndIsSoldOutFalse(CurrentLocation currentLocation, Destination destination);

    @Query("select t from Train t " +
            "where t.currentLocation = ?1 and t.destination = ?2 and t.departureTime " +
            "between ?3 and ?4 " +
            "and t.isSoldOut = false")
    List<Train> findByCurrentLocationAndDestinationAndDepartureTimeBetweenAndIsSoldOutFalse(Town currentTown,
                                                                                            Town destTown,
                                                                                            LocalDateTime startDepartureTime,
                                                                                            LocalDateTime endDepartureTime);


}