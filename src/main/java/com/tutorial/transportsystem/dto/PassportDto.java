package com.tutorial.transportsystem.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class PassportDto implements Serializable {
  private String serial;
  private String number;
  //    TODO Enum
  private String gender;
}
