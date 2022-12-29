package com.tutorial.transportsystem.entity;

import com.tutorial.transportsystem.listener.BaseDateEntityListener;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Entity
@Table (name = "passport")
@Accessors(chain = true)
@EntityListeners(value = BaseDateEntityListener.class)
@NoArgsConstructor

public class Passport extends BaseDateEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  @Column(name = "id", nullable = false)
  Long id;

  @Pattern(regexp = "^\\d{10}\\D")
  @Column (name = "serial")
  String serial;

  @Pattern(regexp = "^\\d{10}\\D")
  @Column (name = "number")
  String number;

  @Pattern(regexp = "(?:m|M|male|Male|f|F|female|Female|FEMALE|MALE)$")
  @Column (name = "gender")
  String gender;

  @Builder
  public Passport(Long id, String serial, String number, String gender) {
    this.id = id;
    this.serial = serial;
    this.number = number;
    this.gender = gender;
  }
}
