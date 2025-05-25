package com.tutorial.transportsystem.repository;

import com.tutorial.transportsystem.entity.City;
import com.tutorial.transportsystem.entity.Station;
import com.tutorial.transportsystem.entity.Ticket;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ETicketRepository extends JpaRepository<Ticket, Long> {

    @Query("SELECT t FROM Ticket t WHERE t.fromCity = :fromCity AND t.fromStation = :fromStation")
    Page<Ticket> findByFromCityAndStation(@Param("fromCity") City fromCity,
                                          @Param("fromStation") Station fromStation,
                                          Pageable pageable);

    @Query("SELECT t FROM Ticket t WHERE t.toCity = :toCity AND t.toStation = :toStation")
    Page<Ticket> findByToCityAndStation(@Param("toCity") City toCity,
                                        @Param("toStation") Station toStation,
                                        Pageable pageable);

    // Пример с фильтрацией по обоим направлениям
    @Query("SELECT t FROM Ticket t WHERE t.fromCity = :fromCity AND t.toCity = :toCity")
    Page<Ticket> findByFromCityAndToCity(@Param("fromCity") City fromCity,
                                         @Param("toCity") City toCity,
                                         Pageable pageable);
}