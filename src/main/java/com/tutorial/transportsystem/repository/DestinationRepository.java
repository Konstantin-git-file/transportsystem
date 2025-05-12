package com.tutorial.transportsystem.repository;

import com.tutorial.transportsystem.entity.CityAndStation;
import com.tutorial.transportsystem.entity.Destination;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DestinationRepository extends JpaRepository<Destination, Long> {
    @Query("SELECT d FROM Destination d WHERE d.cityAndStation = :cityAndStation")
    Page<Destination> findByCityAndStation(@Param("cityAndStation") CityAndStation cityAndStation, Pageable pageable);
}