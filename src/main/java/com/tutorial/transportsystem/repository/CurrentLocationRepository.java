package com.tutorial.transportsystem.repository;

import com.tutorial.transportsystem.entity.CurrentLocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrentLocationRepository extends JpaRepository<CurrentLocation, Long> {
}