package com.critiqo.critiqo_backend.exception.expired;

public class TokenExpiredException extends RuntimeException{
    public TokenExpiredException(String message){
        super(message);
    }
}
