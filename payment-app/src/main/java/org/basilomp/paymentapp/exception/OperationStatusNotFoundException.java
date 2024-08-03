package org.basilomp.paymentapp.exception;

import lombok.Getter;

@Getter
public class OperationStatusNotFoundException extends RuntimeException {

    public OperationStatusNotFoundException(String message) {
        super(message);
    }
}
