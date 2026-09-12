package com.gabriel.juno.infraestructure.security.filters.implement.auth;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.filter.OncePerRequestFilter;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.time.LocalDateTime;

public abstract class JnAuthFilterProvider extends OncePerRequestFilter {

    public  interface ErrorResponseMapper {
        Object getStatus();
    }

    protected record ErrorResponseMapperCode(
            String exception,
            Integer statusCode,
            String error,
            LocalDateTime timeStamp
    )  implements ErrorResponseMapper {
        public ErrorResponseMapperCode(String exception, Integer statusCode, String error) {
            this(exception, statusCode, error, LocalDateTime.now());
        }

        @Override
        public Integer getStatus() {
            return statusCode;
        }
    }

    protected record ErrorResponseMapperHttpStatus(
            String exception,
            HttpStatus statusCode,
            String error,
            LocalDateTime timeStamp
    ) implements ErrorResponseMapper {
        public ErrorResponseMapperHttpStatus(String exception, HttpStatus httpStatus, String error) {
            this(exception, httpStatus, error, LocalDateTime.now());
        }

        @Override
        public HttpStatus getStatus() {
            return statusCode;
        }
    }


    protected void sendResponse(HttpServletResponse response, ErrorResponseMapperCode errorResponseMapper, ObjectMapper objectMapper) {
        try {
            response.setStatus(errorResponseMapper.getStatus());
            response.setContentType("application/json");
            response.getWriter().write(objectMapper.writeValueAsString(errorResponseMapper));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    protected void sendResponse(HttpServletResponse response, ErrorResponseMapperHttpStatus errorResponseMapper, ObjectMapper objectMapper) {
        try {
            response.setStatus(errorResponseMapper.getStatus().value());
            response.setContentType("application/json");
            response.getWriter().write(objectMapper.writeValueAsString(errorResponseMapper));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

}
