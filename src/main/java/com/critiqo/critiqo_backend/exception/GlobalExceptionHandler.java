package com.critiqo.critiqo_backend.exception;

import com.critiqo.critiqo_backend.exception.expired.TokenExpiredException;
import com.critiqo.critiqo_backend.exception.resource.ResourceNotFoundException;
import com.critiqo.critiqo_backend.exception.validation.TokenValidationException;
import com.critiqo.critiqo_backend.response.api.ErrorResponse;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public @ResponseBody ErrorResponse handleResourceNotFoundException(ResourceNotFoundException rs) {
        return new ErrorResponse("RESOURCE-NOT-FOUND-EXCEPTION", rs.getMessage());
    }


    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public @ResponseBody ErrorResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException rs) {
        List<String> errors = new ArrayList<>();
        for (ObjectError error : rs.getAllErrors()) {
            errors.add(error.getDefaultMessage());
        }
        return new ErrorResponse("METHOD-ARGUMENT-NOT-VALID-EXCEPTION", errors);
    }

    @ExceptionHandler(value = TokenValidationException.class)
    @ResponseStatus(HttpStatus.UNAVAILABLE_FOR_LEGAL_REASONS)
    public @ResponseBody ErrorResponse handleTokenValidationException(TokenValidationException rs) {
        return new ErrorResponse("INVALID-TOKEN-EXCEPTION", rs.getMessage());
    }

    @ExceptionHandler(value = TokenExpiredException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public @ResponseBody ErrorResponse handleTokenExpiredException(TokenExpiredException rs) {
        return new ErrorResponse("EXPIRED-TOKEN-EXCEPTION", rs.getMessage());
    }

    @ExceptionHandler(value = ExpiredJwtException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public @ResponseBody ErrorResponse handleExpiredJwtException(ExpiredJwtException rs) {
        return new ErrorResponse("EXPIRED-TOKEN-EXCEPTION", rs.getMessage());
    }





}
