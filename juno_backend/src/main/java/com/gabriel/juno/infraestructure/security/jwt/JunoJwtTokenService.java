package com.gabriel.juno.infraestructure.security.jwt;


import com.gabriel.juno.domain.models.usuario.Usuario;
import org.springframework.beans.factory.annotation.Value;

public class JunoJwtTokenService {

    @Value("${api.security.jwt.secret}")
    private String key;

    @Value("${api.security.jwt.expiration}")
    private Integer expiration;


    @Value("${api.security.jwt.regfresh-token.expiration}")
    private Integer refreshTokenExpiration;

    @Value("${api.security.jwt.issuer}")
    private String issuer;





}
