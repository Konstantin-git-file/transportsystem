package com.tutorial.transportsystem.repository;

import com.tutorial.transportsystem.entity.CurrentLocation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CurrentLocationRepository extends JpaRepository<CurrentLocation, Long> {

    Page<CurrentLocation> findByCityContainingIgnoreCase(String city, Pageable pageable);
    Page<CurrentLocation> findByStationContainingIgnoreCase(String station, Pageable pageable);
    Page<CurrentLocation> findByUserId(Long userId, Pageable pageable);
    Page<CurrentLocation> findByCityContainingIgnoreCaseAndStationContainingIgnoreCase(String city, String station, Pageable pageable);
}