package com.gabriel.juno.domain.port.auth;

import com.gabriel.juno.domain.models.empleado.EmpleadoSecureDTO;
import com.gabriel.juno.domain.models.empleado.EmpleadoFullDTO;
import com.gabriel.juno.domain.models.usuario.Usuario;
import com.gabriel.juno.domain.models.usuario.UsuarioDTO;

public interface AuthRepositoryPort {
    public UsuarioDTO loginUsuario(String email, String password);
    public EmpleadoSecureDTO loginEmpleado(String email, String password);
    public UsuarioDTO registerUsuario(Usuario usuario);
    public EmpleadoSecureDTO registerEmpleado(EmpleadoFullDTO empleado);
}
