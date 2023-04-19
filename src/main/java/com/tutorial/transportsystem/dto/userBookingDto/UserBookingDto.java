package com.tutorial.transportsystem.dto.userBookingDto;

import com.tutorial.transportsystem.entity.Passport;
import lombok.Data;

import java.io.Serializable;

@Data
public class UserBookingDto implements Serializable {
    private String firstname;
    private String lastname;
    private Passport passport;
}
