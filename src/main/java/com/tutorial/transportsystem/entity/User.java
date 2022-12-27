package com.tutorial.transportsystem.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
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

  @NotNull(message = "Name may not be null")
  String firstname;

  @NotNull(message = "lastname may not be null")
  String lastname;

  @OneToOne Passport passport;

  @OneToOne
  @JoinColumn(name = "eticket_id")
  Ticket ticket;
}
