package com.gabriel.juno.domain.port.auth;

import com.gabriel.juno.domain.models.auth.AuthModel;

public interface AuthRepositoryPort {
    public AuthModel login(String email, String password);
}
