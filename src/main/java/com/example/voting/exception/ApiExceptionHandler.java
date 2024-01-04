package com.example.voting.exception;

import com.example.voting.payload.exception.ExceptionResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import java.time.LocalDateTime;

@ControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler(ApiException.class)
    @ResponseBody
    public ResponseEntity<ExceptionResponse> resolveException(ApiException exception) {
        var exceptionResponse = new ExceptionResponse(exception.getHttpStatus(), exception.getMessage(), LocalDateTime.now());
        return new ResponseEntity<>(exceptionResponse, exceptionResponse.httpStatus());
    }
}
