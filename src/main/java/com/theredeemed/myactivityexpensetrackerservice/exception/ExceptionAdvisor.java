package com.theredeemed.myactivityexpensetrackerservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
public class ExceptionAdvisor extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ActivityException.class)
    public ResponseEntity<Object> handleActivityException(ActivityException activityException,
                                                                  WebRequest request) {
        Map<String, Object> error = new LinkedHashMap<>();
        error.put("Error Message", activityException.getMessage());
        error.put("Timestamp", LocalDateTime.now());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
}
