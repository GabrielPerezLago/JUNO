package com.gabriel.juno.domain.models;

import com.gabriel.juno.domain.utils.modeluitls.BuilderModelBase;

import java.time.LocalDateTime;

/**
 * @params {}
 * @params { nombre, email, fundacion }
 * @param { id, nombre, email, fundacion }
 *
 * Modelo que representa el Centro en el dominio.
 */
public class Centro {
    private Long id;
    private String nombre;
    private String email;
    private LocalDateTime fundacion;

    /* Constructor Vacío */
    public Centro() {}

    /* Constructor sin id */
    public Centro(String nombre, String email, LocalDateTime fundacion) {
        this.nombre = nombre;
        this.email = email;
        this.fundacion = fundacion;
    }

    /* Constructor completo */
    public Centro(Long id, String nombre, String email, LocalDateTime fundacion) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.fundacion = fundacion;
    }

    /* Constructor del Builder */
    private Centro(builder builder) {
        this.id = builder.id;
        this.nombre = builder.nombre;
        this.email = builder.email;
        this.fundacion = builder.fundacion;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDateTime getFundacion() {
        return fundacion;
    }

    public void setFundacion(LocalDateTime fundacion) {
        this.fundacion = fundacion;
    }


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
            return new Centro(this);
        }
    }
}
