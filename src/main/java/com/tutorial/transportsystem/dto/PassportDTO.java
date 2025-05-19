package com.tutorial.transportsystem.dto;

import com.tutorial.transportsystem.entity.Passport.Gender;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;


public class PassportDTO {
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    @NotBlank(message = "Серия паспорта не должна быть пустой")
    private String serial;
    @NotBlank(message = "Номер паспорта не должен быть пустым")
    private String number;
    private Gender gender;


}