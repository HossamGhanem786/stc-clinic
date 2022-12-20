package com.stc.clinic.web;

import org.modelmapper.spi.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;

@ControllerAdvice
public class ControllerExceptionHandler {
  
  @ExceptionHandler(value = {ResponseStatusException.class})
  public ResponseEntity<ErrorMessage> resourceNotFoundException(ResponseStatusException ex, WebRequest request) {
    ErrorMessage message = new ErrorMessage(
        ex.getMessage(),
        ex.getRootCause());
    
    return new ResponseEntity<ErrorMessage>(message, HttpStatus.BAD_REQUEST);
  }
}