package com.tutorial.transportsystem.repository;

import com.tutorial.transportsystem.entity.CurrentLocation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface CurrentLocationRepository extends JpaRepository<CurrentLocation, Long> {
//    @Query("SELECT c FROM CurrentLocation c WHERE LOWER(c.cityAndStation.city) LIKE LOWER(CONCAT('%', :city, '%'))")
//    Page<CurrentLocation> findByCityContainingIgnoreCase(@Param("city") String city, Pageable pageable);
    Page<CurrentLocation> findByCityContainingIgnoreCase(String city, Pageable pageable);
}