package com.gabriel.juno.infraestructure.in.controller.auth;

import com.gabriel.juno.application.auth.AuthServiceAdapter;
import com.gabriel.juno.domain.models.auth.AuthReq;
import com.gabriel.juno.domain.models.auth.AuthResponseMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/juno/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthServiceAdapter service;

    public ResponseEntity<AuthResponseMapper> signUp(AuthReq authReq) {
        return ResponseEntity.status(200).body(service.singup(authReq));
    }

}
