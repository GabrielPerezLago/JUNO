package com.gabriel.juno.domain.models.centro;

import com.gabriel.juno.domain.utils.modeluitls.BuilderModelBase;

import java.time.LocalDateTime;

/**
 * @params {}
 * @PARAMS Builder
 *
 * Modelo que representa el Centro en el dominio.
 */
public record Centro(
        Long id,
        String nombre,
        String email,
        LocalDateTime fundacion
) {

    /**
     * @params NO_PARAMS
     * Constuctor de calse de manera simplificada
     *
     * @author Gebriel
     */
    public static class builder implements BuilderModelBase<Centro> {
        private Long id;
        private String nombre;
        private String email;
        private LocalDateTime fundacion;

        public builder() {}


        public builder id(Long id) {
            this.id = id;
            return this;
        }

        public builder nombre(String nombre) {
            this.nombre = nombre;
            return this;

        }

        public builder email(String email) {
            this.email = email;
            return this;
        }

        public builder fundacion(LocalDateTime fundacion) {
            this.fundacion = fundacion;
            return this;
        }


        @Override
        public Centro build() {
            return new Centro(
                    this.id,
                    this.nombre,
                    this.email,
                    this.fundacion
            );
        }
    }
}
