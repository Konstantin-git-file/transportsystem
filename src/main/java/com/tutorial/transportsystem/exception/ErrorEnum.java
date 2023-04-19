package com.tutorial.transportsystem.exception;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public enum ErrorEnum {
  UNKNOWN_ERROR(0, "Неизвестная ошибка"),
  USER_LOGIN_ALREADY_EXISTS(1, "Пользователь с таким логином уже существует"),
  USER_EMAIL_ALREADY_EXISTS(2, "Пользователь с таким адресом электронной почты уже существует"),
  USER_DOESNT_EXIST(3, "Такого пользователя не существует");

  private final int errorCode;
  private final String description;

  ErrorEnum(int errorCode, String description) {

    this.errorCode = errorCode;
    this.description = description;
  }
}
