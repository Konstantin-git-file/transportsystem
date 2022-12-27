package com.tutorial.transportsystem.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Destination {

  @EqualsAndHashCode.Include
  @Id
  @Column(name = "id", nullable = false)
  private Long id;

  private LocalDateTime localDateTime;
  private CityAndStation cityAndStation;

  @Autowired
  public Destination(LocalDateTime localDateTime, CityAndStation cityAndStation) {
    this.localDateTime = localDateTime;
    this.cityAndStation = cityAndStation;
  }
}
