package com.tutorial.transportsystem.service.general;

import com.tutorial.transportsystem.payload.request.SignupRequest;
import com.tutorial.transportsystem.payload.responce.MessageResponse;

public interface UserSignUpService {

  MessageResponse signUp(SignupRequest rq);
}
