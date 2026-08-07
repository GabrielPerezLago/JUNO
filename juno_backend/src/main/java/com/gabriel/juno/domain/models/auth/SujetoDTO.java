package com.gabriel.juno.domain.models.auth;

import com.gabriel.juno.domain.models.empleado.utils.Estado;
import com.gabriel.juno.domain.models.empleado.utils.Rol;
import com.gabriel.juno.domain.utils.modeluitls.BuilderModelBase;

import java.time.LocalDate;
import java.time.LocalDateTime;


/**
 *
 * @param id
 * @param nombre
 * @param apellidos
 * @param dni
 * @param email
 * @param telefono
 * @param nacimiento
 * @param estado
 * @param rol
 * @param idAula
 * @param idCentro
 *
 *
 *
 * La entidad sujeto es la entidad GLOBAL para manejar de manera conjunta a los empleados y usuarios independientemente de su rol en la app, esta nos permite tramitar a ambas entidades de maneja conjunta sin tener que manejar por separdo dos entidaddes.
 *
 * -- Esta no tramitara datos sensibles
 * @apiNote Entidad de Tramite de datos
 */

public record SujetoDTO(
            Long id,
            String nombre,
            String apellidos,
            String dni,
            String email,
            String password,
            String telefono,
            LocalDateTime nacimiento,
            Estado estado,
            Rol rol,
            Long idAula,
            Long idCentro
) {


    public static class builder implements BuilderModelBase<SujetoDTO> {

        private Long id;
        private String nombre;
        private String apellidos;
        private String dni;
        private String email;
        private String password;
        private String telefono;
        private LocalDateTime nacimineto;
        private Rol rol;
        private Estado estado;
        private Long idCentro;
        private Long idAula;


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

        public builder password(String password) {
            this.password = password;
            return this;
        }

        public builder telefono(String telefono) {
            this.telefono = telefono;
            return this;
        }

        public builder nacimineto(LocalDateTime nacimineto) {
            this.nacimineto = nacimineto;
            return this;
        }

        public builder rol(Rol rol) {
            this.rol = rol;
            return this;
        }

        public builder estado(Estado estado) {
            this.estado = estado;
            return this;
        }

        public builder idCentro(Long idCentro) {
            this.idCentro = idCentro;
            return this;
        }

        public builder idAula(Long idAula) {
            this.idAula = idAula;
            return this;
        }

        @Override
        public SujetoDTO build() {
            return new SujetoDTO(
                    id,
                    nombre,
                    apellidos,
                    dni,
                    email,
                    password,
                    telefono,
                    nacimineto,
                    estado,
                    rol,
                    idAula,
                    idCentro
            );
        }
    }
}
