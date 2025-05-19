package com.tutorial.transportsystem.dto;

import com.tutorial.transportsystem.entity.CityAndStation;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


public class TicketDTO {
    private Long id;

    @NotNull(message = "ID пользователя не должен быть пустым")
    private Long userId;

    @NotNull(message = "ID текущей локации не должен быть пустым")
    private Long locationId;

    @NotNull(message = "ID пункта назначения не должен быть пустым")
    private Long destinationId;

    private boolean booked;
    private boolean paid;
    private CityAndStation cityAndStation;

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

    public Long getLocationId() {
        return locationId;
    }

    public void setLocationId(Long locationId) {
        this.locationId = locationId;
    }

    public Long getDestinationId() {
        return destinationId;
    }

    public void setDestinationId(Long destinationId) {
        this.destinationId = destinationId;
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

    public CityAndStation getCityAndStation() {
        return cityAndStation;
    }

    public void setCityAndStation(CityAndStation cityAndStation) {
        this.cityAndStation = cityAndStation;
    }
}