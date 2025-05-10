package com.tutorial.transportsystem.entity;

import jakarta.persistence.*;
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
public class CurrentLocation {

    @EqualsAndHashCode.Include
    @Id
    @Column(name = "id", nullable = false)
    Long id;

    LocalDateTime localDateTime;

    @Enumerated(EnumType.STRING)
    CityAndStation cityAndStation;

    public CurrentLocation(LocalDateTime localDateTime, CityAndStation cityAndStation) {
        this.localDateTime = localDateTime;
        this.cityAndStation = cityAndStation;
    }
}