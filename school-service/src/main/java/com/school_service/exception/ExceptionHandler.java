package com.school_service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Object> handleNotFoundException(NotFoundException notFoundException){

        Map<String, Object> response = new HashMap<>();
        response.put("message", notFoundException.getMessage());
        response.put("status", HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(response, HttpStatusCode.valueOf(404));
    }
}
