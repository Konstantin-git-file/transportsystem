package com.tutorial.transportsystem.dto.userRegistrationDto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;


@Data
public class UserRegistrationDtoRq {

  @NotNull
  private String login;

  @NotNull
  private String email;

  private String password;

  }
