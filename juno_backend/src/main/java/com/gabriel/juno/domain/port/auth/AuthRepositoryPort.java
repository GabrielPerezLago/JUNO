package com.gabriel.juno.domain.port.auth;

import com.gabriel.juno.domain.models.auth.AuthResponseMapper;
import com.gabriel.juno.domain.models.auth.SujetoDTO;
import com.gabriel.juno.domain.models.empleado.EmpleadoSecureDTO;
import com.gabriel.juno.domain.models.empleado.EmpleadoFullDTO;
import com.gabriel.juno.domain.models.token.TokenDataContainerDTO;
import com.gabriel.juno.domain.models.usuario.Usuario;
import com.gabriel.juno.domain.models.usuario.UsuarioDTO;

public interface AuthRepositoryPort {
    public TokenDataContainerDTO login(String email, String password);
    public TokenDataContainerDTO loginByToken(String token);
    public TokenDataContainerDTO registerUsuario(Usuario usuario);
    public TokenDataContainerDTO registerEmpleado(EmpleadoFullDTO empleado);
}
