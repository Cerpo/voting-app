package com.example.voting.payload.exception;

import org.springframework.http.HttpStatus;
import java.time.LocalDateTime;

public record ExceptionResponse(HttpStatus httpStatus, String message, LocalDateTime issuedAt) {
}
