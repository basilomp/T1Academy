package org.basilomp.productapp.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class InsufficientFundsException extends RuntimeException {
    private HttpStatus httpStatus;

    public InsufficientFundsException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }
}
