package com.tutorial.transportsystem.entity;

import com.tutorial.transportsystem.listener.BaseDateEntityListener;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Entity
@Table(name = "t_ticket")
@Accessors(chain = true)
@EntityListeners(value = BaseDateEntityListener.class)
@NoArgsConstructor

public class Ticket extends BaseDateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    User user;

    @OneToOne
    @JoinColumn(name = "passport_id")
    Passport passport;

    Boolean booked;

    Boolean paid;

    @OneToOne
    @JoinColumn(name = "current_location_id")
    CurrentLocation currentLocation;

    @OneToOne
    @JoinColumn(name = "destination_location_id")
    Destination destination;

}
