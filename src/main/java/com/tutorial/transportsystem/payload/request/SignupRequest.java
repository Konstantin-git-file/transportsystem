package com.tutorial.transportsystem.payload.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.tutorial.transportsystem.entity.ERole;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SignupRequest {
  @NotBlank
  @Size(min = 3, max = 20)
  private String username;

  @NotBlank
  @Size(max = 250)
  private String email;

  @JsonFormat(shape = JsonFormat.Shape.OBJECT)
  private List<ERole> role;

  @NotBlank
  @Size(min = 6, max = 100)
  private String password;
}
