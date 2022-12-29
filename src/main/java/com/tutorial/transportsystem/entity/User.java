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
@Table (name = "users")
@Accessors (chain = true)
@EntityListeners(value = BaseDateEntityListener.class)
@NoArgsConstructor

public class User extends BaseDateEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Long id;

  @Column(name = "first_name")
  private String firstname;

  @Column(name = "last_name")
  private String lastname;

  @OneToOne
  @JoinColumn(name = "passport_id")
  Passport passport;

  @OneToOne
  @JoinColumn(name = "ticket_id")
  Ticket ticket;

  @Builder
  public User(Long id, String firstname, String lastname, Passport passport, Ticket ticket) {
    this.id = id;
    this.firstname = firstname;
    this.lastname = lastname;
    this.passport = passport;
    this.ticket = ticket;
  }
}
