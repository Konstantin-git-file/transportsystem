package com.tutorial.transportsystem.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class LoginController {

  @RequestMapping("/login")
  public String loginForm() {
    return "login";
  }
}
