package com.tutorial.transportsystem.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Ticket {

    @EqualsAndHashCode.Include
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

    boolean booked;

    boolean paid;

    @Enumerated(EnumType.STRING)
    CityAndStation cityAndStation;

    @OneToOne
    @JoinColumn(name = "current_location_id")
    CurrentLocation currentLocation;

    @OneToOne
    @JoinColumn(name = "destination_location_id")
    Destination destination;
}