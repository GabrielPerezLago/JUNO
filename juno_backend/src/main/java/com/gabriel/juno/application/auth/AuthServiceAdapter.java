package com.gabriel.juno.application.auth;

import com.gabriel.juno.domain.models.auth.SujetoDTO;
import com.gabriel.juno.domain.models.empleado.EmpleadoFullDTO;
import com.gabriel.juno.domain.models.empleado.utils.Estado;
import com.gabriel.juno.domain.models.empleado.utils.Rol;
import com.gabriel.juno.domain.models.token.Token;
import com.gabriel.juno.domain.models.token.TokenDataContainerDTO;
import com.gabriel.juno.domain.models.usuario.Usuario;
import com.gabriel.juno.domain.port.auth.AuthRepositoryPort;

public class AuthServiceAdapter{

    private AuthRepositoryPort authPort;

    public AuthServiceAdapter(AuthRepositoryPort authPort) {
        this.authPort = authPort;
    }

    public TokenDataContainerDTO singup(final SujetoDTO sujeto) {
        if (sujeto.rol() == null) {
            return authPort.registerUsuario(sujeto.transferToUsuario());
        } else {
            return authPort.registerEmpleado(sujeto.transferToEmpleado());
        }
    }

    public TokenDataContainerDTO login(String email, String password) {
        return this.authPort.login(email, password);
    }

    public TokenDataContainerDTO loginByToken(final String token) {
        return  this.authPort.loginByToken(token);
    }
}
