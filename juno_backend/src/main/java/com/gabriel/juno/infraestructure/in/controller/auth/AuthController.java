package com.gabriel.juno.infraestructure.in.controller.auth;

import com.gabriel.juno.application.auth.AuthService;
import com.gabriel.juno.domain.models.auth.AuthModel;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService service;

    @PostMapping("/signin")
    public AuthModel signIn(String email, String password, String type) {
        return service.signin(email, password, type);
    }

}
