package com.tutorial.transportsystem.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

public enum CityAndStation {
    ЛЕНИНГРАДСКИЙ("МОСКВА"),
    КАЗАНСКИЙ("МОСКВА"),
    ЯРОСЛАВСКИЙ("МОСКВА"),
    МОСКОВСКИЙ("САНКТ-ПЕТЕРБУРГ"),
    ВИТЕБСКИЙ("САНКТ-ПЕТЕРБУРГ"),
    ЛАДОЖСКИЙ("САНКТ-ПЕТЕРБУРГ"),
    КАЗАНЬ_ПАССАЖИРСКАЯ("КАЗАНЬ");
    private final String city;

    CityAndStation(String city) {
        this.city = city;
    }

    public String getCity() {
        return city;
    }
}