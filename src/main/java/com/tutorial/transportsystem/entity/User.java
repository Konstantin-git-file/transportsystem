package com.tutorial.transportsystem.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tutorial.transportsystem.listener.BaseDateEntityListener;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "t_user")
@Accessors(chain = true)
@EntityListeners(value = BaseDateEntityListener.class)
@NoArgsConstructor

public class User extends BaseDateEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Size(min = 1, max = 50)
  @Column(name = "username", length = 50, unique = true, nullable = false)
  private String login;

  @JsonIgnore
  @NotNull
  @NotNull(message = "Password can't be null")
  @Size(min = 6, max = 200, message = "Password should contain 6 to 12 character only")
  private String password;

  @Size(min = 5, max = 100)
  @Column(name = "email", length = 100, unique = true)
  private String email;

  @Column(name = "role")
  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(
      name = "t_user_role",
      joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
      inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")})
  private Set<Role> roles = new HashSet<>();

  @Column(name = "first_name")
  private String firstname;

  @Column(name = "last_name")
  private String lastname;

  @OneToOne
  @JoinColumn(name = "passport_id")
  private Passport passport;

  @OneToOne
  @JoinColumn(name = "ticket_id")
  private Ticket ticket;

  public User(String login, String email, String encode) {
    this.login = login;
    this.email = email;
    this.password = encode;
  }

}
