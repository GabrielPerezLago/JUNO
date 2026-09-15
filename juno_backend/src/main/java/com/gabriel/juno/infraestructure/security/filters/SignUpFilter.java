package com.gabriel.juno.infraestructure.security.filters;

import com.gabriel.juno.domain.models.usuario.exception.UsuarioArgsException;
import com.gabriel.juno.infraestructure.security.filters.implement.auth.JnAuthFilterProvider;
import com.gabriel.juno.infraestructure.spring.uitls.resquest.RequestBodyContentWrapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.util.ContentCachingRequestWrapper;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Map;

@Component
public class SignUpFilter extends JnAuthFilterProvider {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if("/auth/signup".equals(request.getServletPath()) && "POST".equalsIgnoreCase(request.getMethod())) {

            RequestBodyContentWrapper contentWrapper = new RequestBodyContentWrapper(request);

            contentWrapper.getInputStream();

            byte[] body = contentWrapper.getContentAsByteArray();

            if (body.length == 0) {
                this.senderErrorResponse(response, "El body no puede estar vacío");
                return;
            }

            if(body.length > 0) {
                Map<String, Object> jsonMap = objectMapper.readValue(body, Map.class);


                String email = (String) jsonMap.get("email");
                String telefono = (String) jsonMap.get("telefono");
                String dni = (String) jsonMap.get("dni");
                String password = (String) jsonMap.get("password");

                if (email == null || !email.contains("@") || !email.contains(".")) {
                    this.senderErrorResponse(response, "El email no es valido");
                    return;
                }

                if(telefono != null) {
                    if (!telefono.contains("+")) {
                        this.senderErrorResponse(response, "El número de telefono debe contener prefijos");
                        return;
                    }
                }

                if(dni == null || !dni.matches(".*[a-zA-z1-9].*")) {
                    this.senderErrorResponse(response, "El dni no es valido");
                    return;
                }

                if (password == null) {
                    this.senderErrorResponse(response, "La contraseña no puede estar vacía");
                    return;
                }

                filterChain.doFilter(contentWrapper, response);
                return;
            }
            return;
        }
        filterChain.doFilter(request, response);
    }

    private void senderErrorResponse(final HttpServletResponse response, final String msg) {
        sendResponse(
                response,
                new ErrorResponseMapperHttpStatus(
                        new UsuarioArgsException(msg).getMessage(),
                        HttpStatus.UNAUTHORIZED,
                        "Parametros no validos"
                ),
                objectMapper
        );
    }
}
