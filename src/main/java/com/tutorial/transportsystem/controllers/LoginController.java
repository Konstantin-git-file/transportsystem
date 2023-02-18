package com.tutorial.transportsystem.controllers;

import com.tutorial.transportsystem.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
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

  @PostMapping("/registration")
  public String registrationForm(Model model) {
            UserDto user = new UserDto();
            model.addAttribute("user", user);
    return "registration";
  }
}


