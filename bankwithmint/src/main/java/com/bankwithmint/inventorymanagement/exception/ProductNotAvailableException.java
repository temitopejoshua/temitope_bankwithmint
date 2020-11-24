package com.bankwithmint.inventorymanagement.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class ProductNotAvailableException extends ResponseStatusException {
    public ProductNotAvailableException(HttpStatus status) {
        super(status);
    }

    public ProductNotAvailableException(HttpStatus status, String reason) {
        super(status, reason);
    }
}
