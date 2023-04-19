package com.tutorial.transportsystem.repository;

import com.tutorial.transportsystem.entity.Destination;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DestinationRepository extends JpaRepository<Destination, Long> {}
