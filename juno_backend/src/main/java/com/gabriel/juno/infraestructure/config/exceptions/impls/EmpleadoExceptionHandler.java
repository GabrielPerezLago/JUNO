package com.gabriel.juno.infraestructure.config.exceptions.impls;

import com.gabriel.juno.domain.models.empleado.exceptions.EmpleadoEstadoException;
import com.gabriel.juno.domain.models.empleado.exceptions.EmpleadoIsExistException;
import com.gabriel.juno.domain.models.empleado.exceptions.EmpleadoNotExistException;
import com.gabriel.juno.domain.models.empleado.exceptions.EmpleadoRolException;
import com.gabriel.juno.infraestructure.config.exceptions.JnExeptionsHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;


public class EmpleadoExceptionHandler extends JnExeptionsHandler {




    @ExceptionHandler(EmpleadoRolException.class)
    public ResponseEntity<ErrorResponse> handlerRolException(EmpleadoRolException ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponseCode(
                        ex.getMessage(),
                        400,
                        "Rol de empleado no valido"
                ));
    }

    @ExceptionHandler(EmpleadoEstadoException.class)
    public ResponseEntity<ErrorResponse> handlerEstadoException(EmpleadoEstadoException ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponseCode(
                        ex.getMessage(),
                        400,
                        "Estado del empleado no valido"
                ));
    }

    @ExceptionHandler(EmpleadoNotExistException.class)
    public ResponseEntity<ErrorResponse> handlerNotExistException(EmpleadoNotExistException ex) {
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(new ErrorResponseCode(
                        ex.getMessage(),
                        401,
                        "Empleado no existente"
                ));
    }

    @ExceptionHandler(EmpleadoIsExistException.class)
    public ResponseEntity<ErrorResponse> handlerIsExistException(EmpleadoIsExistException ex) {
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(new ErrorResponseCode(
                        ex.getMessage(),
                        401,
                        "Empleado existente"
                ));
    }

}
