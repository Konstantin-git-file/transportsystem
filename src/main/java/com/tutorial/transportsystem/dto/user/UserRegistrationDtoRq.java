package com.tutorial.transportsystem.dto.user;

import com.tutorial.transportsystem.dto.PassportDto;
import lombok.Data;

@Data
public class UserRegistrationDtoRq {
  private String login;
  private String email;
  private String firstname;
  private String lastname;
  private PassportDto passport;
}
