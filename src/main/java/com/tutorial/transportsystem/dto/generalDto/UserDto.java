package com.tutorial.transportsystem.dto.generalDto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tutorial.transportsystem.entity.Passport;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class UserDto implements Serializable {
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSS")
  private LocalDateTime createdAt;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSS")
  private LocalDateTime updatedAt;

  private String login;
  private String email;
  private String firstname;
  private String lastname;
  private String password;
  private Passport passport;
}
