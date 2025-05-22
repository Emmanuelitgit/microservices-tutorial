package com.student_service.student_service.exception;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
@ControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Object> handleNotFoundException(NotFoundException notFoundException){

        Map<String, Object> response = new HashMap<>();
        response.put("message", notFoundException.getMessage());
        response.put("status", HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(response, HttpStatusCode.valueOf(404));
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(ServerException.class)
    public ResponseEntity<Object> handleServerException(ServerException exception) throws JsonProcessingException {
        Map<String, Object> response = new HashMap<>();
        String status  = exception.getMessage().split(" ")[0];
        response.put("status", status);

        Pattern pattern = Pattern.compile("\\{\"message\":\"(.*?)\"");
        Matcher matcher = pattern.matcher(exception.getMessage());
        if (matcher.find()) {
            String message = matcher.group(1);
            response.put("message", message);
        }
        return new ResponseEntity<>(response, HttpStatusCode.valueOf(Integer.parseInt(status)));
    }
}
