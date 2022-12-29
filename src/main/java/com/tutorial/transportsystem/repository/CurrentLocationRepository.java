package com.tutorial.transportsystem.repository;

import com.tutorial.transportsystem.entity.CurrentLocation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrentLocationRepository extends JpaRepository<CurrentLocation, Long> {
}