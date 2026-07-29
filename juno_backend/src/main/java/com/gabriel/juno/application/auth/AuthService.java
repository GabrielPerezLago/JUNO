package com.gabriel.juno.application.auth;

import com.gabriel.juno.domain.models.auth.AuthModel;

public interface AuthService {
    public AuthModel signin();
    public AuthModel signup();
}
