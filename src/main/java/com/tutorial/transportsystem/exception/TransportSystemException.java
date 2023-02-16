package com.tutorial.transportsystem.exception;

import lombok.Getter;

@Getter
public class TransportSystemException extends RuntimeException {

  private ErrorEnum errorEnum;

  public TransportSystemException(ErrorEnum code) {
    super(code.getDescription());
    this.errorEnum = code;
  }
}
