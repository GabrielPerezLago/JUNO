package com.gabriel.juno.domain.port.auth;

import com.gabriel.juno.domain.models.empleado.EmpleadoDTO;
import com.gabriel.juno.domain.models.empleado.EmpleadoUserDTO;
import com.gabriel.juno.domain.models.usuario.Usuario;
import com.gabriel.juno.domain.models.usuario.UsuarioDTO;

public interface AuthRepositoryPort {
    public UsuarioDTO loginUsuario(String email, String password);
    public EmpleadoDTO loginEmpleado(String email, String password);
    public UsuarioDTO registerUsuario(Usuario usuario);
    public EmpleadoDTO registerEmpleado(EmpleadoUserDTO empleado);
}
