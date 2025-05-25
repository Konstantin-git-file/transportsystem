package com.tutorial.transportsystem.dto;
import com.tutorial.transportsystem.entity.City;
import com.tutorial.transportsystem.entity.Station;
import jakarta.validation.constraints.NotNull;

public class TicketDTO {

    private Long id;

    @NotNull(message = "ID пользователя не должен быть пустым")
    private Long userId;

    private boolean booked;
    private boolean paid;

    @NotNull(message = "Город отправления обязателен")
    private City fromCity;

    @NotNull(message = "Станция отправления обязательна")
    private Station fromStation;

    @NotNull(message = "Город назначения обязателен")
    private City toCity;

    @NotNull(message = "Станция назначения обязательна")
    private Station toStation;

    // геттеры и сеттеры

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public boolean isBooked() {
        return booked;
    }

    public void setBooked(boolean booked) {
        this.booked = booked;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    public City getFromCity() {
        return fromCity;
    }

    public void setFromCity(City fromCity) {
        this.fromCity = fromCity;
    }

    public Station getFromStation() {
        return fromStation;
    }

    public void setFromStation(Station fromStation) {
        this.fromStation = fromStation;
    }

    public City getToCity() {
        return toCity;
    }

    public void setToCity(City toCity) {
        this.toCity = toCity;
    }

    public Station getToStation() {
        return toStation;
    }

    public void setToStation(Station toStation) {
        this.toStation = toStation;
    }
}
