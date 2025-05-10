package com.tutorial.transportsystem.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Long id;

    @NotBlank(message = "Name may not be blank")
    String firstname;

    @NotBlank(message = "Lastname may not be blank")
    String lastname;

    @OneToOne
    Passport passport;

    @OneToOne
    @JoinColumn(name = "eticket_id")
    Ticket ticket;
}