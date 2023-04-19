package com.tutorial.transportsystem.service.general;

import com.tutorial.transportsystem.payload.request.LoginRequest;
import com.tutorial.transportsystem.payload.responce.UserInfoResponse;
import org.springframework.http.ResponseEntity;

public interface UserAuthenticateService {

  ResponseEntity<UserInfoResponse> authenticateUser(LoginRequest lq);
}
