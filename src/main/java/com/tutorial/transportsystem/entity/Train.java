package com.tutorial.transportsystem.entity;

import com.tutorial.transportsystem.listener.BaseDateEntityListener;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "t_train")
@Accessors(chain = true)
@EntityListeners(value = BaseDateEntityListener.class)
@NoArgsConstructor

public class Train {

    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    private String trainName;

    LocalDateTime departureTime;

    @Column(name = "arrival_time")
    LocalDateTime arrivalTime;

    @OneToMany
    @JoinColumn(name = "ticket_id")
    private List<Ticket> ticket;

    private int seatCount;

    private int bookedSeatCount;

    private boolean isSoldOut = false;

    @OneToOne
    //  ManyToOne
    //  Town
    CurrentLocation currentLocation;

    @OneToOne
    //  ManyToOne
    //  Town
    Destination destination;
}


