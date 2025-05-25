package com.tutorial.transportsystem.repository;

import com.tutorial.transportsystem.entity.City;
import com.tutorial.transportsystem.entity.Destination;
import com.tutorial.transportsystem.entity.Station;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DestinationRepository extends JpaRepository<Destination, Long> {

    @Query("SELECT d FROM Destination d WHERE d.city = :city AND d.station = :station")
    Page<Destination> findByCityAndStation(@Param("city") City city,
                                           @Param("station") Station station,
                                           Pageable pageable);
}