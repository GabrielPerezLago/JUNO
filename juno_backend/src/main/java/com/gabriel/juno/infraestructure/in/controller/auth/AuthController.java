package com.gabriel.juno.infraestructure.in.controller.auth;

import com.gabriel.juno.application.auth.AuthServiceAdapter;
import com.gabriel.juno.domain.models.auth.SujetoDTO;
import com.gabriel.juno.domain.models.token.TokenDataContainerDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/juno/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthServiceAdapter service;

    @PostMapping("/signup")
    public ResponseEntity<TokenDataContainerDTO> signup(@RequestBody SujetoDTO sujeto) {
        return ResponseEntity.status(200).body(service.singup(sujeto));
    }

    @PostMapping("/signin")
    public ResponseEntity<TokenDataContainerDTO> signin(
            @RequestParam(name = "email") String email,
            @RequestParam(name = "password") String password
    ) {
        return ResponseEntity
                .status(200)
                .body(service.login(email, password));
    }

}
