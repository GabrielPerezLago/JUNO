package com.gabriel.juno.domain.models.empleados;

import com.gabriel.juno.domain.models.empleados.exceptions.EmpleadoIncorrectParamException;
import com.gabriel.juno.domain.models.empleados.implement.EmpleadoImplement;
import com.gabriel.juno.domain.utils.modeluitls.BuilderModelBase;

public class Empleado extends EmpleadoImplement {
    private Long id;
    private String nombre;
    private String apellidos;
    private String dni;
    private String email;
    private String password;
    private String telefono;

    public Empleado() {}
    private Empleado(builder build) {
        this.id = build.id;
        this.nombre = build.nombre;
        this.apellidos = build.apellidos;
        this.dni = build.dni;
        this.email = build.email;
        this.password = build.password;
        this.telefono = build.telefono;
        checkAndSanitize();
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

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Override
    protected void checkAndSanitize() {
        if (id == null) throw new EmpleadoIncorrectParamException("El id no puede ser null");
        if
    }

    public class builder implements BuilderModelBase<Empleado> {
        private Long id;
        private String nombre;
        private String apellidos;
        private String dni;
        private String email;
        private String password;
        private String telefono;

        public builder id(Long id) {
            this.id = id;
            return this;
        }

        public builder apellidos(String apellidos) {
            this.apellidos = apellidos;
            return this;
        }

        public builder nombre(String nombre) {
            this.nombre = nombre;
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

        @Override
        public Empleado build() {
            return new Empleado(this);
        }
    }
}
