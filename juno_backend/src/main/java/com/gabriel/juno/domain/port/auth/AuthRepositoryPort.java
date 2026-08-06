package com.gabriel.juno.domain.port.auth;

import com.gabriel.juno.domain.models.auth.AuthResponseMapper;
import com.gabriel.juno.domain.models.empleado.EmpleadoSecureDTO;
import com.gabriel.juno.domain.models.empleado.EmpleadoFullDTO;
import com.gabriel.juno.domain.models.usuario.Usuario;
import com.gabriel.juno.domain.models.usuario.UsuarioDTO;

public interface AuthRepositoryPort {
    public AuthResponseMapper login(String email, String password);
    public AuthResponseMapper loginByToken(String token);
    public UsuarioDTO registerUsuario(Usuario usuario);
    public EmpleadoSecureDTO registerEmpleado(EmpleadoFullDTO empleado);
}
