package org.basilomp.spring.controller;

import org.basilomp.spring.dto.ErrorResponseDto;
import org.basilomp.spring.exception.ProductNotFoundException;
import org.basilomp.spring.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ProductNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorResponseDto handleProductNotFoundException(ProductNotFoundException e) {
        return new ErrorResponseDto(HttpStatus.NOT_FOUND.value(), e.getMessage());
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ErrorResponseDto handleUserNotFoundException(UserNotFoundException e) {
        return new ErrorResponseDto(HttpStatus.NOT_FOUND.value(), e.getMessage());
    }

}
