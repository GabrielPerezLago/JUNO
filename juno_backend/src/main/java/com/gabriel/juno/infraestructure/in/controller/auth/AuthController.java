package com.gabriel.juno.infraestructure.in.controller.auth;

import com.gabriel.juno.application.auth.AuthServiceAdapter;
import com.gabriel.juno.domain.models.auth.SujetoDTO;
import com.gabriel.juno.domain.models.token.Token;
import com.gabriel.juno.domain.models.token.TokenDataContainerDTO;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthServiceAdapter service;

    @PostMapping("/signup")
    public ResponseEntity<TokenDataContainerDTO> signup(@RequestBody SujetoDTO sujeto) {
        return ResponseEntity
                .status(200)
                .body(service.singup(sujeto));
    }

    @PostMapping("/signin")
    public ResponseEntity<TokenDataContainerDTO> signin( @RequestParam(name = "email") String email, @RequestParam(name = "password") String password) {
        return ResponseEntity
                .status(200)
                .body(service.login(email, password));
    }

    @PostMapping("/signin/token")
    public ResponseEntity<TokenDataContainerDTO> signinByToken(@RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(service.loginByToken(token));
    }

}
