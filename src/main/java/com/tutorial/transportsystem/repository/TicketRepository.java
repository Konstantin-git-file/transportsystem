package com.tutorial.transportsystem.repository;

import com.tutorial.transportsystem.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, Long> {}
