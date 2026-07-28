package com.gabriel.juno.application.auth;

import com.gabriel.juno.domain.models.auth.AuthModelEntity;
import com.gabriel.juno.domain.models.empleados.EmpleadoDTO;
import com.gabriel.juno.domain.models.usuario.Usuario;

public interface AuthService {
    public AuthModelEntity signin();
    public AuthModelEntity signup();
}
