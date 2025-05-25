package com.tutorial.transportsystem.repository;

import com.tutorial.transportsystem.entity.Passport;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PassportRepository extends JpaRepository<Passport, Long> {
    Page<Passport> findBySerialContainingIgnoreCase(String serial, Pageable pageable);
    Page<Passport> findAllBySerial(String serial, Pageable pageable);
}