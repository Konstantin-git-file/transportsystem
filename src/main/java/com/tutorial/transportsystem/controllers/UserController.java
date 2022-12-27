package com.tutorial.transportsystem.controllers;

import com.tutorial.transportsystem.entity.User;
import com.tutorial.transportsystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {

  // inject through lombok
  private UserRepository userRepository;

  @Autowired
  public UserController(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @GetMapping("all")
  public List<User> allUsers() {
    return userRepository.findAll();
  }
}
