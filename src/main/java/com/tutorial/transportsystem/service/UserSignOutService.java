package com.tutorial.transportsystem.service;

import com.tutorial.transportsystem.payload.responce.MessageResponse;
import org.springframework.http.ResponseEntity;

public interface UserSignOutService {

    ResponseEntity<MessageResponse> signOut();
}
