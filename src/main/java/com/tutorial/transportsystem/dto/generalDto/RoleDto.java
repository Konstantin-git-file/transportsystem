package com.tutorial.transportsystem.dto.generalDto;

import lombok.Data;

import java.io.Serializable;

@Data
public class RoleDto implements Serializable {
  private Long id;
  private String name;
}
