package com.tutorial.transportsystem.repository;

import com.tutorial.transportsystem.entity.Passport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PassportRepository extends JpaRepository<Passport, Long> {
}