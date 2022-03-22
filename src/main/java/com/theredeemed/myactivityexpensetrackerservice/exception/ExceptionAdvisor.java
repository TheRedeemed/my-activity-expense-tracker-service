package com.theredeemed.myactivityexpensetrackerservice.exception;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
public class ExceptionAdvisor extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ActivityException.class)
    public ResponseEntity<Object> handleActivityException(ActivityException activityException,
                                                          WebRequest request) {
//        Map<String, Object> error = new LinkedHashMap<>();
//        error.put("Error Code", activityException.getError().getCode());
//        error.put("Error Title", activityException.getError().getTitle());
//        error.put("Error Details", activityException.getMessage());
//        error.put("Timestamp", LocalDateTime.now());
        ErrorMessage errorMessage = ErrorMessage.builder()
                .errorCode(activityException.getError().getCode())
                .errorTitle(activityException.getError().getTitle())
                .errorDescription(activityException.getErrorDescription())
                .timestamp(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }

    @Override
    @NonNull
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException e,
                                                                  @NonNull HttpHeaders headers,
                                                                  @NonNull HttpStatus status,
                                                                  @NonNull WebRequest request) {
        String errorDetails = e.getMessage();

        if (e.getCause() instanceof InvalidFormatException) {
            InvalidFormatException ifx = (InvalidFormatException) e.getCause();
            if (ifx.getTargetType() != null && ifx.getTargetType().isEnum()) {
                errorDetails = String.format("%s is not a valid enum value for the field: %s. The value must be one of these: %s",
                        ifx.getValue(),
                        ifx.getPath().get(ifx.getPath().size() - 1).getFieldName(),
                        Arrays.toString(ifx.getTargetType().getEnumConstants()));
            }
        }

        ErrorMessage errorMessage = ErrorMessage.builder()
                .errorCode(HttpStatus.BAD_REQUEST.value())
                .errorTitle(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .errorDescription(errorDetails)
                .timestamp(LocalDateTime.now())
                .build();

        return handleExceptionInternal(e, errorMessage, headers, HttpStatus.BAD_REQUEST, request);
    }
}
