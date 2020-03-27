package com.example.demo.exception;

public class SystemException extends RuntimeException {

    public SystemException(String message) {
        super(message);
    }

    public SystemException(String code, Throwable cause) {
        super(cause);
    }
}
