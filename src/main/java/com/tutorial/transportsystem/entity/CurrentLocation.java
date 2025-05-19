package com.tutorial.transportsystem.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;



@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CurrentLocation {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    Long id;

    LocalDateTime localDateTime;

    @Enumerated(EnumType.STRING)
    CityAndStation cityAndStation;

    String city;

    public CurrentLocation(LocalDateTime localDateTime, CityAndStation cityAndStation) {
        this.localDateTime = localDateTime;
        this.cityAndStation = cityAndStation;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    public CityAndStation getCityAndStation() {
        return cityAndStation;
    }

    public void setCityAndStation(CityAndStation cityAndStation) {
        this.cityAndStation = cityAndStation;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}