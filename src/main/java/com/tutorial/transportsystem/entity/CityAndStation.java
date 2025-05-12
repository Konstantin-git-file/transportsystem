package com.tutorial.transportsystem.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

public enum CityAndStation {
    LENINGRADSKY("MOSCOW"),
    KAZANSKY("MOSCOW"),
    YAROSLAVLSKY("MOSCOW"),
    MOSKOVSKY("SAINT_PETERSBURG"),
    VITEBSKY("SAINT_PETERSBURG"),
    LADOZHSKY("SAINT_PETERSBURG"),
    KAZAN_PASSAZHIRSKAYA("KAZAN");

    private final String city;

    CityAndStation(String city) {
        this.city = city;
    }

    public String getCity() {
        return city;
    }
}