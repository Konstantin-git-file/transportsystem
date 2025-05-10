package com.tutorial.transportsystem.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CityAndStation {
    LENINGRADSKY("MOSCOW"),
    KAZANSKY("MOSCOW"),
    YAROSLAVLSKY("MOSCOW"),
    MOSKOVSKY("SAINT_PETERSBURG"),
    VITEBSKY("SAINT_PETERSBURG"),
    LADOZHSKY("SAINT_PETERSBURG"),
    KAZAN_PASSAZHIRSKAYA("KAZAN");

    private final String city;
}