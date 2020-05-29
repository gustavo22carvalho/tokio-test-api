package com.example.api.domain.exception;

import java.util.ArrayList;
import java.util.List;

public class BusinessException extends Exception{

    private static final long serialVersionUID = 2030339739863941181L;

    private List<String> errors = new ArrayList<>();

    public BusinessException() {
        super();
    }
   
    public BusinessException(String message, List<String> errors) {
        super(message);
        this.errors = errors;
    }

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }

    public BusinessException(Throwable cause) {
        super(cause);
    }
    
    public List<String> getErrors() {
        return errors;
    }

    public boolean addError(String error){
        return errors.add(error);
    }
}
