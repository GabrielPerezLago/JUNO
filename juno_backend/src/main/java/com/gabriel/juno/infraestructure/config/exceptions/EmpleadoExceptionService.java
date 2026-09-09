package com.gabriel.juno.infraestructure.config.exceptions;

import com.gabriel.juno.domain.models.empleado.exceptions.EmpleadoEstadoException;
import com.gabriel.juno.domain.models.empleado.exceptions.EmpleadoIsExistException;
import com.gabriel.juno.domain.models.empleado.exceptions.EmpleadoNotExistException;
import com.gabriel.juno.domain.models.empleado.exceptions.EmpleadoRolException;
import org.springframework.data.repository.init.RepositoriesPopulatedEvent;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.http.HttpClient;
import java.time.LocalDateTime;

@RestControllerAdvice
public class EmpleadoExceptionService {

    private record EmpleadoExcetionResponse(
            String errorMessage,
            Integer statusCode,
            String error,
            LocalDateTime timestamp
    ) {
        public  EmpleadoExcetionResponse(String errorMessage, Integer statusCode, String error) {
            this(
                    errorMessage,statusCode,error, LocalDateTime.now()
            );
        }
    }


    @ExceptionHandler(EmpleadoRolException.class)
    public ResponseEntity<EmpleadoExcetionResponse> handlerRolException(EmpleadoRolException ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new EmpleadoExcetionResponse(
                        ex.getMessage(),
                        400,
                        "Rol de empleado no valido"
                ));
    }

    @ExceptionHandler(EmpleadoEstadoException.class)
    public ResponseEntity<EmpleadoExcetionResponse> handlerEstadoException(EmpleadoEstadoException ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new EmpleadoExcetionResponse(
                        ex.getMessage(),
                        400,
                        "Estado del empleado no valido"
                ));
    }

    @ExceptionHandler(EmpleadoNotExistException.class)
    public ResponseEntity<EmpleadoExcetionResponse> handlerNotExistException(EmpleadoNotExistException ex) {
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(new EmpleadoExcetionResponse(
                        ex.getMessage(),
                        401,
                        "Empleado no existente"
                ));
    }

    @ExceptionHandler(EmpleadoIsExistException.class)
    public ResponseEntity<EmpleadoExcetionResponse> handlerIsExistException(EmpleadoIsExistException ex) {
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(new EmpleadoExcetionResponse(
                        ex.getMessage(),
                        401,
                        "Empleado existente"
                ));
    }

}
