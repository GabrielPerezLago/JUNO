package com.gabriel.juno.application.auth;

import com.gabriel.juno.domain.models.auth.AuthReq;
import com.gabriel.juno.domain.models.auth.AuthResponseMapper;
import com.gabriel.juno.domain.models.auth.SujetoDTO;
import com.gabriel.juno.domain.models.empleado.EmpleadoFullDTO;
import com.gabriel.juno.domain.models.empleado.utils.Estado;
import com.gabriel.juno.domain.models.empleado.utils.Rol;
import com.gabriel.juno.domain.models.token.TokenDataContainerDTO;
import com.gabriel.juno.domain.models.usuario.Usuario;
import com.gabriel.juno.domain.port.auth.AuthRepositoryPort;

public class AuthServiceAdapter{

    private AuthRepositoryPort authPort;

    public AuthServiceAdapter(AuthRepositoryPort authPort) {
        this.authPort = authPort;
    }

    public TokenDataContainerDTO singup(SujetoDTO sujeto) {
        if (sujeto.rol() == null) {
            Usuario usuario = new Usuario.builder()
                    .nombre(sujeto.nombre())
                    .apellidos(sujeto.apellidos())
                    .dni(sujeto.dni())
                    .email(sujeto.email())
                    .password(sujeto.password())
                    .telefono(sujeto.telefono())
                    .nacimiento(sujeto.nacimiento())
                    .build();

            return authPort.registerUsuario(usuario);
        } else {
            EmpleadoFullDTO empleado = new EmpleadoFullDTO.builder()
                    .nombre(sujeto.nombre())
                    .apellidos(sujeto.apellidos())
                    .dni(sujeto.dni())
                    .email(sujeto.email())
                    .password(sujeto.password())
                    .telefono(sujeto.telefono())
                    .nacimiento(sujeto.nacimiento())
                    .rol(Rol.valueOf(sujeto.rol().toString()))
                    .estado(Estado.valueOf(sujeto.estado().toString()))
                    .idAula(sujeto.idAula())
                    .idCentro(sujeto.idCentro())
                    .build();
            return authPort.registerEmpleado(empleado);
        }
    }

    public TokenDataContainerDTO login(String email, String password) {
        return this.authPort.login(email, password);
    }
}
