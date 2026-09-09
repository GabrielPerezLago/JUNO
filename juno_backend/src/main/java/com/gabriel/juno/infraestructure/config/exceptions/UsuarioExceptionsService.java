package com.gabriel.juno.infraestructure.config.exceptions;

import com.gabriel.juno.domain.models.usuario.exception.UsuarioArgsException;
import com.gabriel.juno.domain.models.usuario.exception.UsuarioIsExistException;
import com.gabriel.juno.domain.models.usuario.exception.UsuarioNotExistException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class UsuarioExceptionsService {

    private record UsuarioErrorResponse(
            String errMessage,
            Integer statusCode,
            String error,
            LocalDateTime timestamp
    ){
        public UsuarioErrorResponse(String errMessage, Integer statusCode, String error) {
            this(errMessage, statusCode, error, LocalDateTime.now());
        }
    }

    @ExceptionHandler(UsuarioIsExistException.class)
    public ResponseEntity<UsuarioErrorResponse> handleIsExistUsuario(UsuarioIsExistException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new UsuarioErrorResponse(
           ex.getMessage(),
           401,
                "Acceso Denegado"
        ));
    }


    @ExceptionHandler(UsuarioNotExistException.class)
    public ResponseEntity<UsuarioErrorResponse> handleNotExistUsuario(UsuarioNotExistException ex) {
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(new UsuarioErrorResponse(
                  ex.getMessage(),
                  401,
                  "Acceso denegado"
                ));
    }


    @ExceptionHandler(UsuarioArgsException.class)
    public ResponseEntity<UsuarioErrorResponse> handleUsuarioArgs(UsuarioArgsException ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new UsuarioErrorResponse(
                        ex.getMessage(),
                        400,
                        "Parametros no validos o erroneos"
                ));
    }





}
