package com.tutorial.transportsystem.controllers;

import com.tutorial.transportsystem.payload.request.LoginRequest;
import com.tutorial.transportsystem.payload.request.SignupRequest;
import com.tutorial.transportsystem.payload.responce.MessageResponse;
import com.tutorial.transportsystem.payload.responce.UserInfoResponse;
import com.tutorial.transportsystem.service.general.UserAuthenticateService;
import com.tutorial.transportsystem.service.general.impl.UserSignOutServiceImpl;
import com.tutorial.transportsystem.service.general.impl.UserSignUpServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

  private final UserSignUpServiceImpl userSignUpServiceImpl;
  private final UserSignOutServiceImpl userSignOutServiceImpl;
  private final UserAuthenticateService userAuthenticateServiceImpl;

  @PostMapping("/signin")
  public ResponseEntity<UserInfoResponse> authenticateUser(@RequestBody LoginRequest lq) {
    return userAuthenticateServiceImpl.authenticateUser(lq);
  }

  @PostMapping(value = "/signup", consumes = "application/json", produces = "application/json")
  public MessageResponse registerUser(@RequestBody SignupRequest rq) {
    return userSignUpServiceImpl.signUp(rq);
  }

  @PostMapping("/signout")
  public ResponseEntity<?> logoutUser() {
    return userSignOutServiceImpl.signOut();
  }
}
