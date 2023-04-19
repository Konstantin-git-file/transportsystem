package com.tutorial.transportsystem.service.general;

import com.tutorial.transportsystem.payload.responce.MessageResponse;
import org.springframework.http.ResponseEntity;

public interface UserSignOutService {

  ResponseEntity<MessageResponse> signOut();
}
