package com.gabriel.juno.domain.models;

public class Usuario {
    private Long id;
    private String nombre;
    private String apellidos;
    private String dni;
    private String email;
    private String password;
    private Boolean activo;


    public Usuario() {}

    public Usuario(String nombre, String apellidos, String dni, String email, String password, Boolean activo) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.dni = dni;
        this.email = email;
        this.password = password;
        this.activo = activo;
    }

    public Long id() {
        return id;
    }

    public Usuario setId(Long id) {
        this.id = id;
        return this;
    }

    public String nombre() {
        return nombre;
    }

    public Usuario setNombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public String apellidos() {
        return apellidos;
    }

    public Usuario setApellidos(String apellidos) {
        this.apellidos = apellidos;
        return this;
    }

    public String dni() {
        return dni;
    }

    public Usuario setDni(String dni) {
        this.dni = dni;
        return this;
    }

    public String email() {
        return email;
    }

    public Usuario setEmail(String email) {
        this.email = email;
        return this;
    }

    public String password() {
        return password;
    }

    public Usuario setPassword(String password) {
        this.password = password;
        return this;
    }

    public Boolean activo() {
        return activo;
    }

    public Usuario setActivo(Boolean activo) {
        this.activo = activo;
        return this;
    }

    @Override
    public String toString() {
        return "Usuario: { ID: " + id + ", Nombre: " + nombre + ", Apellidos: " + apellidos + ", Email: " + email + ", DNI: " + dni + " }" ;
    }






}
