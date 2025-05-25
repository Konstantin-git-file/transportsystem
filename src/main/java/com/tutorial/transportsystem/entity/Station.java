package com.tutorial.transportsystem.entity;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum Station {
    ЛЕНИНГРАДСКИЙ(City.МОСКВА),
    КАЗАНСКИЙ(City.МОСКВА),
    ЯРОСЛАВСКИЙ(City.МОСКВА),
    МОСКОВСКИЙ(City.САНКТ_ПЕТЕРБУРГ),
    ВИТЕБСКИЙ(City.САНКТ_ПЕТЕРБУРГ),
    ЛАДОЖСКИЙ(City.САНКТ_ПЕТЕРБУРГ),
    КАЗАНЬ_ПАССАЖИРСКАЯ(City.КАЗАНЬ);

    private final City city;

    Station(City city) {
        this.city = city;
    }

    public City getCity() {
        return city;
    }

    public static List<Station> getStationsByCity(City city) {
        return Arrays.stream(values())
                .filter(st -> st.getCity() == city)
                .collect(Collectors.toList());
    }
}