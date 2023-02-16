package com.tutorial.transportsystem.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.nio.file.AccessDeniedException;

@ControllerAdvice
@Slf4j
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(value = {IllegalArgumentException.class, IllegalStateException.class})
  protected ResponseEntity<Object> handleConflict(RuntimeException ex, WebRequest request) {
    String bodyOfResponse = "This should be application specific";
    return handleExceptionInternal(
        ex, bodyOfResponse, new HttpHeaders(), HttpStatus.CONFLICT, request);
  }

  @ExceptionHandler({AccessDeniedException.class})
  public ResponseEntity<Object> handleAccessDeniedException(Exception ex, WebRequest request) {
    return new ResponseEntity(
        "Access denied message here", new HttpHeaders(), HttpStatus.FORBIDDEN);
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
  public ResponseEntity<Object> handleGeneralException(Exception ex, WebRequest request) {
    log.error("Обработка ошибки...", ex);
    String bodyOfResponse = "This should be application specific";
    return handleExceptionInternal(
            ex, bodyOfResponse, new HttpHeaders(), HttpStatus.CONFLICT, request);
  }

}
