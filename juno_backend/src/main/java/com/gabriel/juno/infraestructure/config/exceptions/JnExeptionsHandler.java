package com.gabriel.juno.infraestructure.config.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDate;
import java.time.LocalDateTime;

@RestControllerAdvice
public abstract class JnExeptionsHandler {

    protected interface ErrorResponse{};
    protected record ErrorResponseCode (
            String message,
            Integer StatusCode,
            String error,
            LocalDateTime timeStamp
    ) implements  ErrorResponse {
        public ErrorResponseCode(String message, Integer statusCode, String error) {
            this(message, statusCode, error, LocalDateTime.now());
        };
    }

    protected record ErrorResponseHttpStatus(
            String message,
            HttpStatus statusCode,
            String error,
            LocalDateTime timeStamp
    ) implements  ErrorResponse {
        public ErrorResponseHttpStatus(String message, HttpStatus statusCode, String error) {
            this(message, statusCode, error, LocalDateTime.now());
        }
    }
}
