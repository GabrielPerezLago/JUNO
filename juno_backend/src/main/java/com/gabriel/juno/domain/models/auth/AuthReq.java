package com.gabriel.juno.domain.models.auth;

import java.time.LocalDateTime;

public record AuthReq(
        String nombre,
        String apellidos,
        String dni,
        String email,
        String password,
        String telefono,
        LocalDateTime nacimiento,
        String estado,
        String rol,
        Long idCentro,
        Long idAula
) {

}
