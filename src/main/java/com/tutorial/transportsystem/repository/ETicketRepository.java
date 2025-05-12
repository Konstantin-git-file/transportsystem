package com.tutorial.transportsystem.repository;

import com.tutorial.transportsystem.entity.CityAndStation;
import com.tutorial.transportsystem.entity.Ticket;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ETicketRepository extends JpaRepository<Ticket, Long> {
    @Query("SELECT t FROM Ticket t WHERE t.cityAndStation = :cityAndStation")
    Page<Ticket> findByCityAndStation(CityAndStation cityAndStation, Pageable pageable);
}