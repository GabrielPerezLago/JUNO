package com.gabriel.juno.domain.models.auth;

import com.gabriel.juno.domain.models.auth.utils.Tipo;
import com.gabriel.juno.domain.models.empleados.EmpleadoDTO;
import com.gabriel.juno.domain.models.empleados.utils.Estado;
import com.gabriel.juno.domain.models.empleados.utils.Rol;
import com.gabriel.juno.domain.models.usuario.Usuario;

import java.time.LocalDateTime;

public record AuthModel(
        Tipo tipo,
        Long id,
        String nombre,
        String apellidos,
        String dni,
        String email,
        String telefono,
        LocalDateTime nacimiento,
        Estado estado,
        Rol rol,
        Long idCentro,
        Long idAula
) {
    public AuthModel(EmpleadoDTO empleado) {
        this(
                Tipo.EMPLEADO,
                empleado.getId(),
                empleado.getNombre(),
                empleado.getApellidos(),
                empleado.getDni(),
                empleado.getEmail(),
                empleado.getTelefono(),
                empleado.getNacimeinto(),
                empleado.getEstado(),
                empleado.getRol(),
                empleado.getIdCentro(),
                empleado.getIdAula()
        );
    }

    public AuthModel(Usuario usuario) {
        this(
                Tipo.USUARIO,
                usuario.getId(),
                usuario.getNombre(),
                usuario.getApellidos(),
                usuario.getDni(),
                usuario.getEmail(),
                usuario.getTelefono(),
                usuario.getNacimiento(),
                null,
                null,
                null,
                null
        );
    }
}
