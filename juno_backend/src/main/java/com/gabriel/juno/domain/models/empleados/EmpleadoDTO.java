package com.gabriel.juno.domain.models.empleados;

import com.gabriel.juno.domain.models.empleados.exceptions.EmpleadoIncorrectParamException;
import com.gabriel.juno.domain.models.empleados.implement.EmpleadoImplement;
import com.gabriel.juno.domain.models.empleados.utils.Estado;
import com.gabriel.juno.domain.models.empleados.utils.Rol;
import com.gabriel.juno.domain.utils.modeluitls.BuilderModelBase;

import java.time.*;


/* DOCUMENTAR */
public class EmpleadoDTO extends EmpleadoImplement {
    private Long id;
    private String nombre;
    private String apellidos;
    private String dni;
    private String email;
    private LocalDateTime nacimeinto;
    private String telefono;
    private Estado estado;
    private Rol rol;
    private Long idCentro;
    private Long idAula;

    public EmpleadoDTO() {}
    private EmpleadoDTO(builder builder) {
        this.id = builder.id;
        this.nombre = builder.nombre;
        this.apellidos = builder.apellidos;
        this.dni = builder.dni;
        this.email = builder.email;
        this.telefono = builder.telefono;
        this.nacimeinto = builder.nacimiento;
        this.estado = builder.estado;
        this.rol = builder.rol;
        this.idCentro = builder.idCentro;
        this.idAula = builder.idAula;
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

    public Long getIdAula() {
        return idAula;
    }

    public void setIdAula(Long idAula) {
        this.idAula = idAula;
    }

    public Long getIdCentro() {
        return idCentro;
    }

    public void setIdCentro(Long idCentro) {
        this.idCentro = idCentro;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public LocalDateTime getNacimeinto() {
        return nacimeinto;
    }

    public void setNacimeinto(LocalDateTime nacimeinto) {
        this.nacimeinto = nacimeinto;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    /* TEMINAR */
    @Override
    protected void checkAndSanitize() {
        if (this.id == null) throw new EmpleadoIncorrectParamException("El id no puede ser null");
        if (this.nombre == null) throw  new EmpleadoIncorrectParamException("El nombre no puede ser null");
        if (this.email == null || !(!this.email.contains("@") || this.email.contains("."))) throw new EmpleadoIncorrectParamException("El email no puede estar vacion y debe contener un '@' y al menos un '.' ");

    }

    public class builder implements BuilderModelBase<EmpleadoDTO> {
        private Long id;
        private String nombre;
        private String apellidos;
        private String dni;
        private String email;
        private String telefono;
        private LocalDateTime nacimiento;
        private Estado estado;
        private Rol rol;
        private Long idCentro;
        private Long idAula;

        public builder() {}

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

        public builder estado(Estado estado) {
            this.estado = estado;
            return this;
        }

        public builder rol(Rol rol) {
            this.rol = rol;
            return this;
        }

        public builder idAula(Long idAula) {
            this.idAula = idAula;
            return this;
        }

        public builder idCentro(Long idCentro) {
            this.idCentro = idCentro;
            return this;
        }

        @Override
        public EmpleadoDTO build() {
            return new EmpleadoDTO(this);
        }
    }

}
