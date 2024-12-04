package com.example.e_commerce.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class CustomException extends RuntimeException {
    private final String message;

    public CustomException(String message) {
        super(message);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}

