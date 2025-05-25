package com.tutorial.transportsystem.dto;

import com.tutorial.transportsystem.entity.City;
import com.tutorial.transportsystem.entity.Station;

import java.time.LocalDateTime;

public class CurrentLocationDTO {
    private Long id;
    private LocalDateTime localDateTime;
    private City city;
    private Station station;

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

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Station getStation() {
        return station;
    }

    public void setStation(Station station) {
        this.station = station;
    }
}