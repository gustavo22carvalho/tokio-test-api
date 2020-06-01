package com.example.api.web.rest;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.api.domain.exception.BusinessException;

@RestControllerAdvice
public class ControllerAdviceCustom {
 
    @ExceptionHandler(value = { EntityNotFoundException.class })
    protected ResponseEntity<Object> handleEntityNotFound(EntityNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(this.makeMessage(ex.getMessage()));
    }
    
    @ExceptionHandler(value = { BusinessException.class })
    protected ResponseEntity<Object> handleBusinessException(BusinessException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(this.makeMessage(ex.getMessage(), ex.getErrors()));
    }

    private Map<String, Object> makeMessage(String message){
        return this.makeMessage(message, Collections.emptyList());
    }

    private Map<String, Object> makeMessage(String message, List<String> errors){
        Map<String, Object> responseError = new HashMap<>();
        responseError.put("message", message);
        responseError.put("errors", errors);
        return responseError;
    }
   
}