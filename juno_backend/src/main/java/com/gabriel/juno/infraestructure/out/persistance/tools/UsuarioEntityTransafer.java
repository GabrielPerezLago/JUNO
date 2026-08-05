package com.gabriel.juno.infraestructure.out.persistance.tools;


import com.gabriel.juno.domain.models.usuario.Usuario;
import com.gabriel.juno.domain.models.usuario.UsuarioDTO;
import com.gabriel.juno.infraestructure.out.persistance.entities.usuario.UsuarioEntity;

public class UsuarioEntityTransafer {

    public Usuario transferToUsuario(UsuarioEntity usEntity) {
        return new Usuario.builder()
                .id(usEntity.getId())
                .nombre(usEntity.getNombre())
                .apellidos(usEntity.getApellidos())
                .dni(usEntity.getDni())
                .email(usEntity.getEmail())
                .password(usEntity.getPassword())
                .telefono(usEntity.getTelefono())
                .nacimiento(usEntity.getNacimiento())
                .build();
    }



}
