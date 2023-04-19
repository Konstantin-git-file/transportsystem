package com.tutorial.transportsystem.payload.responce;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class MessageResponse {

  private String message;

  public MessageResponse(String s) {
    this.message = s;
  }
}
