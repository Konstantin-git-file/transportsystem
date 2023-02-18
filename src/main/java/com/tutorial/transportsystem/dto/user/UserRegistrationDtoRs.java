package com.tutorial.transportsystem.dto.user;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class UserRegistrationDtoRs {

  private Long userId;
}
