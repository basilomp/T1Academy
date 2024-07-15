package org.basilomp.spring.exception;

import lombok.Getter;

@Getter
public class ProductNotFoundException extends RuntimeException {

    public ProductNotFoundException(String message) {
        super(message);
    }
}
