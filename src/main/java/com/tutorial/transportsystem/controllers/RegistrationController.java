package com.tutorial.transportsystem.controllers;

import com.tutorial.transportsystem.dto.user.UserRegistrationDtoRq;
import com.tutorial.transportsystem.dto.user.UserRegistrationDtoRs;
import com.tutorial.transportsystem.service.UserRegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RegistrationController {

  private final UserRegistrationService userRegistrationService;

  @PostMapping("/registration")
  public UserRegistrationDtoRs makeRegistrationUser(@RequestBody UserRegistrationDtoRq rq) {
    return userRegistrationService.makeRegistrationUser(rq);
  }
}
