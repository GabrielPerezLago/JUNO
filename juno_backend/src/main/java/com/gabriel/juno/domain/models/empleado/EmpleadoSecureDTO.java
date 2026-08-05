package com.gabriel.juno.domain.models.empleado;


import com.gabriel.juno.domain.models.auth.AuthResponseMapper;
import com.gabriel.juno.domain.models.empleado.utils.Estado;
import com.gabriel.juno.domain.models.empleado.utils.Rol;
import com.gabriel.juno.domain.utils.modeluitls.BuilderModelBase;
import com.gabriel.juno.domain.utils.out.RestResponseMapper;

import java.time.LocalDateTime;

/**
 *  Entidad de Empleado Diseñada para ser transferioda al usuario
 * @implSpec AuthResponseMapper, RestResponseMapper
 *  */
public record EmpleadoSecureDTO(
        Long id,
        String nombre,
        String apellidos,
        String dni,
        String email,
        String telefono,
        LocalDateTime nacimiento,
        Rol rol,
        Estado estado,
        Long idCentro,
        Long idAula,
        String token

) implements AuthResponseMapper, RestResponseMapper {

    public static class builder implements BuilderModelBase<EmpleadoSecureDTO> {

        private Long id;
        private String nombre;
        private String apellidos;
        private String dni;
        private String email;
        private String telefono;
        private LocalDateTime nacimiento;
        private Rol rol;
        private Estado estado;
        private Long idCentro;
        private Long idAula;
        private String token;


        public builder id(Long id) {
            this.id = id;
            return this;
        }

        public builder nombre(String nombre) {
            this.nombre = nombre;
            return this;
        }

        public builder apellidos(String apellidos) {
            this.apellidos = apellidos;
            return this;
        }

        public builder dni(String dni) {
            this.dni = dni;
            return this;
        }

        public builder email(String email) {
            this.email = email;
            return this;
        }

        public builder telefono(String telefono) {
            this.telefono = telefono;
            return this;
        }

        public builder nacimiento(LocalDateTime nacimiento) {
            this.nacimiento = nacimiento;
            return this;
        }

        public builder rol(Rol rol) {
            this.rol = rol;
            return this;
        }

        public builder idCentro(Long idCentro) {
            this.idCentro = idCentro;
            return this;
        }

        public builder estado(Estado estado) {
            this.estado = estado;
            return this;
        }

        public builder idAula(Long idAula) {
            this.idAula = idAula;
            return this;
        }

        public builder token(String token) {
            this.token = token;
            return this;
        }

        @Override
        public EmpleadoSecureDTO build() {
            return new EmpleadoSecureDTO(id, nombre, apellidos, dni, email, telefono, nacimiento, rol, estado, idCentro, idAula, token);
        }
    }

}
