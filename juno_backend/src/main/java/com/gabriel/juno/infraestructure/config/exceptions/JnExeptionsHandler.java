package com.gabriel.juno.infraestructure.config.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDate;
import java.time.LocalDateTime;

@RestControllerAdvice
public class JnExeptionsHandler {

    protected interface ErrorResponse{};
    protected record ErrorResponseCode (
            String exception,
            Integer StatusCode,
            String error,
            LocalDateTime timeStamp
    ) implements  ErrorResponse {
        public ErrorResponseCode(String exception, Integer statusCode, String error) {
            this(exception, statusCode, error, LocalDateTime.now());
        };
    }

    protected record ErrorResponseHttpStatus(
            String exception,
            HttpStatus statusCode,
            String error,
            LocalDateTime timeStamp
    ) implements  ErrorResponse {
        public ErrorResponseHttpStatus(String exception, HttpStatus statusCode, String error) {
            this(exception, statusCode, error, LocalDateTime.now());
        }
    }
}
