package com.bank.service.bankservice.exception;

public class HttpProcessingException extends RuntimeException {
    public HttpProcessingException(String message) {
        super(message);
    }

    public HttpProcessingException(String message, Throwable cause) {
        super(message, cause);
    }

}
