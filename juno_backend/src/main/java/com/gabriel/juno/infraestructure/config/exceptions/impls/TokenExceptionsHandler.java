package com.gabriel.juno.infraestructure.config.exceptions.impls;

import com.gabriel.juno.domain.models.token.exception.ExpiredTokenException;
import com.gabriel.juno.domain.models.token.exception.InvalidTokenException;
import com.gabriel.juno.infraestructure.config.exceptions.JnExeptionsHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;


public class TokenExceptionsHandler extends JnExeptionsHandler {

    @ExceptionHandler(InvalidTokenException.class)
    public ResponseEntity<ErrorResponse> handlerInvalidToken(InvalidTokenException ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponseHttpStatus(
                   ex.getMessage(),
                   HttpStatus.BAD_REQUEST,
                   "Token no valido"
                ));
    }

    @ExceptionHandler(ExpiredTokenException.class)
    public ResponseEntity<ErrorResponse> handlerExpiredToken(ExpiredTokenException ex) {
        return ResponseEntity
                .status(HttpStatus.NOT_ACCEPTABLE)
                .body(new ErrorResponseHttpStatus(
                        ex.getMessage(),
                        HttpStatus.NOT_ACCEPTABLE,
                        "Token expirado"
                ));
    }
}
