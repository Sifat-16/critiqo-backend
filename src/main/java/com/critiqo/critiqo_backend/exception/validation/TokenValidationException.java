package com.critiqo.critiqo_backend.exception.validation;

public class TokenValidationException extends RuntimeException{
    public TokenValidationException(String message){
        super(message);
    }
}
