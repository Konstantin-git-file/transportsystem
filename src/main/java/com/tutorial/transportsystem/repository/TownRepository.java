package com.tutorial.transportsystem.repository;

import com.tutorial.transportsystem.entity.Town;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TownRepository extends JpaRepository<Town, Long> {
}