package com.student_service.student_service.exception;

import org.springframework.http.HttpStatus;

public class ServerException extends RuntimeException{
    public ServerException(String message) {
        super(message);
    }

    public ServerException(String message, Throwable cause) {
        super(message, cause);
    }
}
