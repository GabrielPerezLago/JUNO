package com.gabriel.juno.infraestructure.security.filters;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gabriel.juno.domain.models.usuario.exception.UsuarioArgsException;
import com.gabriel.juno.infraestructure.config.exceptions.JnExeptionsHandler;
import com.gabriel.juno.infraestructure.security.filters.implement.auth.JnAuthFilterProvider;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SignInFilter extends JnAuthFilterProvider {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    private final tools.jackson.databind.ObjectMapper objectMapper;

    public SignInFilter(tools.jackson.databind.ObjectMapper objectMapper) {
        super();
        this.objectMapper = objectMapper;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if("/auth/signin".equals(request.getServletPath()) || "POST".equalsIgnoreCase(request.getMethod())) {
            final String email = request.getParameter("email");

            if (email == null || !email.contains("@")) {
                final ErrorResponseMapperCode errMapper = new ErrorResponseMapperCode(
                        new UsuarioArgsException("El email no es valido").getMessage(),
                        401,
                        "Parametros de usuario no validos"
                );
                sendResponse(response, errMapper, objectMapper);
                return;
            }
        }
        filterChain.doFilter(request,response);
    }
}
