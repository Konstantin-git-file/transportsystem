package com.tutorial.transportsystem.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)

public class Ticket {

    @EqualsAndHashCode.Include
    @Id
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

}
