package org.basilomp.productapp.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class IntegrationException extends RuntimeException {

    private HttpStatus httpStatus;

    public IntegrationException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }
}
