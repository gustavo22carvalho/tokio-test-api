package com.example.api.web.rest;

import javax.persistence.EntityNotFoundException;

import com.example.api.domain.exception.BusinessException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ControllerAdviceCustom extends ResponseEntityExceptionHandler {
 
    @ExceptionHandler(value = { EntityNotFoundException.class })
    protected ResponseEntity<Object> handleEntityNotFound(RuntimeException ex, WebRequest request) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new BusinessException(ex.getMessage()));
    }
    
    @ExceptionHandler(value = { BusinessException.class })
    protected ResponseEntity<Object> handleBusinessException(RuntimeException ex, WebRequest request) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }
   
}