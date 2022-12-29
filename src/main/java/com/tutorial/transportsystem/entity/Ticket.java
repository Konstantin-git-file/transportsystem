package com.tutorial.transportsystem.entity;

import com.tutorial.transportsystem.listener.BaseDateEntityListener;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Entity
@Table(name = "ticket")
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

    //    @ManyToOne
    //    @JoinColumn(name = "city_and_station_ID")
    //    @Enumerated (EnumType.STRING) // не совсем понял там можно или нет..
    //    CityAndStation cityAndStation;

    @OneToOne
    @JoinColumn(name = "current_location_id")
    CurrentLocation currentLocation;

    @OneToOne
    @JoinColumn(name = "destination_location_id")
    Destination destination;

    @Builder
    public Ticket(Long id, User user, Passport passport, Boolean booked, Boolean paid, CurrentLocation currentLocation, Destination destination) {
        this.id = id;
        this.user = user;
        this.passport = passport;
        this.booked = booked;
        this.paid = paid;
        this.currentLocation = currentLocation;
        this.destination = destination;
    }
}
