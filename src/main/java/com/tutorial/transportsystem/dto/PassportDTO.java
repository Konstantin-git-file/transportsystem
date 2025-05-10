package com.tutorial.transportsystem.dto;

import com.tutorial.transportsystem.entity.Passport.Gender;
import lombok.Data;

@Data
public class PassportDTO {
    private Long id;
    private String serial;
    private String number;
    private Gender gender;
}