package com.gabriel.juno.infraestructure.config.exceptions.impls;

import com.gabriel.juno.domain.models.usuario.exception.UsuarioArgsException;
import com.gabriel.juno.domain.models.usuario.exception.UsuarioIsExistException;
import com.gabriel.juno.domain.models.usuario.exception.UsuarioNotExistException;
import com.gabriel.juno.infraestructure.config.exceptions.JnExeptionsHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class UsuarioExceptionsHandler extends JnExeptionsHandler {

    @ExceptionHandler(UsuarioIsExistException.class)
    public ResponseEntity<ErrorResponse> handleIsExistUsuario(UsuarioIsExistException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ErrorResponseCode(
           ex.getMessage(),
           401,
                "Acceso Denegado"
        ));
    }


    @ExceptionHandler(UsuarioNotExistException.class)
    public ResponseEntity<ErrorResponse> handleNotExistUsuario(UsuarioNotExistException ex) {
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(new ErrorResponseCode(
                  ex.getMessage(),
                  401,
                  "Acceso denegado"
                ));
    }


    @ExceptionHandler(UsuarioArgsException.class)
    public ResponseEntity<ErrorResponse> handleUsuarioArgs(UsuarioArgsException ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponseCode(
                        ex.getMessage(),
                        400,
                        "Parametros no validos o erroneos"
                ));
    }





}
