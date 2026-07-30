package com.gabriel.juno.application.auth;

import com.gabriel.juno.domain.models.auth.AuthModel;
import com.gabriel.juno.domain.port.auth.AuthRepositoryPort;

public class AuthService {

    private AuthRepositoryPort authPort;

    public AuthService(AuthRepositoryPort authPort) {
        this.authPort = authPort;
    }

    public AuthModel signin(String email, String password) {
        return authPort.login(email, password);
    }
}
