package com.tutorial.transportsystem.dto;

import com.tutorial.transportsystem.entity.CityAndStation;
import lombok.Data;

import java.time.LocalDateTime;


public class CurrentLocationDTO {
    private Long id;
    private LocalDateTime localDateTime;
    private CityAndStation cityAndStation;

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
}