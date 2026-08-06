package com.gabriel.juno.application.auth;

import com.gabriel.juno.domain.models.auth.AuthReq;
import com.gabriel.juno.domain.models.auth.AuthResponseMapper;
import com.gabriel.juno.domain.models.empleado.EmpleadoFullDTO;
import com.gabriel.juno.domain.models.empleado.utils.Estado;
import com.gabriel.juno.domain.models.empleado.utils.Rol;
import com.gabriel.juno.domain.models.usuario.Usuario;
import com.gabriel.juno.domain.port.auth.AuthRepositoryPort;

public class AuthServiceAdapter{

    private AuthRepositoryPort authPort;

    public AuthServiceAdapter(AuthRepositoryPort authPort) {
        this.authPort = authPort;
    }

    public AuthResponseMapper singup(AuthReq authReq) {
        if (authReq.rol() == null) {
            Usuario usuario = new Usuario.builder()
                    .nombre(authReq.nombre())
                    .apellidos(authReq.apellidos())
                    .dni(authReq.dni())
                    .email(authReq.email())
                    .password(authReq.password())
                    .telefono(authReq.telefono())
                    .nacimiento(authReq.nacimiento())
                    .build();

            return authPort.registerUsuario(usuario);
        } else {
            EmpleadoFullDTO empleado = new EmpleadoFullDTO.builder()
                    .nombre(authReq.nombre())
                    .apellidos(authReq.apellidos())
                    .dni(authReq.dni())
                    .email(authReq.email())
                    .password(authReq.password())
                    .telefono(authReq.telefono())
                    .nacimiento(authReq.nacimiento())
                    .rol(Rol.valueOf(authReq.rol().toUpperCase()))
                    .estado(Estado.valueOf(authReq.estado().toUpperCase()))
                    .idAula(authReq.idAula())
                    .idCentro(authReq.idCentro())
                    .build();
            return authPort.registerEmpleado(empleado);
        }
    }

    public AuthResponseMapper login(String email, String password) {

    }
}
