package com.gabriel.juno.domain.models.usuario;

import com.gabriel.juno.domain.models.auth.AuthResponseMapper;
import com.gabriel.juno.domain.utils.modeluitls.BuilderModelBase;
import com.gabriel.juno.domain.utils.out.RestResponseMapper;

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
 *
 * Clase sin Arg {password} expecificamente diseñada para ser eviada al cliente
 *
 */
public record UsuarioDTO(
        Long id,
        String nombre,
        String apellidos,
        String dni,
        String email,
        String telefono,
        LocalDateTime nacimiento
) implements AuthResponseMapper, RestResponseMapper {

    public class builder implements BuilderModelBase<UsuarioDTO> {

        private Long id;
        private String nombre;
        private String apellidos;
        private String dni;
        private String email;
        private String telefono;
        private LocalDateTime nacimiento;

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

        @Override
        public UsuarioDTO build() {
            return new UsuarioDTO(
                    this.id,
                    this.nombre,
                    this.apellidos,
                    this.dni,
                    this.email,
                    this.telefono,
                    this.nacimiento
            );
        }
    }

}
