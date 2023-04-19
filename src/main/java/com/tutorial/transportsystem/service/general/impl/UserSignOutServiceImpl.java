package com.tutorial.transportsystem.service.general.impl;

import com.tutorial.transportsystem.config.jwt.JwtUtils;
import com.tutorial.transportsystem.service.general.UserSignOutService;
import com.tutorial.transportsystem.payload.responce.MessageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserSignOutServiceImpl implements UserSignOutService {

  private final JwtUtils jwtUtils;

  @Override
  public ResponseEntity<MessageResponse> signOut() {
    ResponseCookie cookie = jwtUtils.getCleanJwtCookie();
    return ResponseEntity.ok()
        .header(HttpHeaders.SET_COOKIE, cookie.toString())
        .body(new MessageResponse("You've been signed out!"));
  }
}
