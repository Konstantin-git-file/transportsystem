package com.tutorial.transportsystem.repository;

import com.tutorial.transportsystem.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ETicketRepository extends JpaRepository<Ticket, Long> {}
