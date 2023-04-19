package com.tutorial.transportsystem.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import java.nio.file.AccessDeniedException;

@ControllerAdvice
@Slf4j
public class RestResponseEntityExceptionHandler {

  @ExceptionHandler(value = {IllegalArgumentException.class, IllegalStateException.class})
  protected ErrorRsDto handleConflict(RuntimeException ex, WebRequest request) {
    log.error("Обработка ошибки...", ex);
    String bodyOfResponse = "This should be application specific";
    return new ErrorRsDto().setErrorCode(-1).setDescription(ex.getMessage());
  }

  @ExceptionHandler({AccessDeniedException.class})
  public ErrorRsDto handleAccessDeniedException(Exception ex, WebRequest request) {
    log.error("Обработка ошибки...", ex);
    return new ErrorRsDto().setErrorCode(-1).setDescription(ex.getMessage());
  }

  @ExceptionHandler({TransportSystemException.class})
  public ErrorRsDto handleAccessDeniedException(TransportSystemException ex) {
    log.error("Обработка ошибки...", ex);
    ErrorEnum errorEnum = ex.getErrorEnum();
    return new ErrorRsDto()
        .setErrorCode(errorEnum.getErrorCode())
        .setDescription(errorEnum.getDescription());
  }

  @ExceptionHandler(value = {Exception.class})
  public ErrorRsDto handleGeneralException(Exception ex, WebRequest request) {
    log.error("Обработка ошибки...", ex);
    String bodyOfResponse = "This should be application specific";
    return new ErrorRsDto().setErrorCode(-1).setDescription(ex.getMessage());
  }

  @ExceptionHandler({HttpMessageNotReadableException.class})
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ErrorRsDto handle(HttpMessageNotReadableException e) {
    log.warn("Returning HTTP 400 Bad Request", e);
    return new ErrorRsDto().setErrorCode(-1).setDescription(e.getMessage());
  }
}
