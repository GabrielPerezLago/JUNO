package com.gabriel.juno.infraestructure.security.filters;

import com.gabriel.juno.domain.models.usuario.exception.UsuarioArgsException;
import com.gabriel.juno.infraestructure.security.filters.implement.auth.JnAuthFilterProvider;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.lang.management.ManagementPermission;
import java.util.Map;

@Component
public class SignUpFilter extends JnAuthFilterProvider {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if("/auth/signin/".equals(request.getServletPath()) && "POST".equalsIgnoreCase(request.getMethod())) {
            ContentCachingRequestWrapper reqWrapper = new ContentCachingRequestWrapper(request, request.getContentLength());

            filterChain.doFilter(reqWrapper, response);

            byte[] body = reqWrapper.getContentAsByteArray();

            if(body.length > 0) {
                Map<String, Object> jsonMap = objectMapper.readValue(body, Map.class);

                String email = (String) jsonMap.get("email");
                String telefono = (String) jsonMap.get("telefono");
                String dni = (String) jsonMap.get("dni");

                if (email == null || !email.contains("@") || !email.contains(".")) {
                    sendResponse(
                            response,
                            new ErrorResponseMapperHttpStatus(
                                    new UsuarioArgsException("El email no es valido").getMessage(),
                                    HttpStatus.UNAUTHORIZED,
                                    "Parametros no validos"
                            ),
                            objectMapper
                    );
                    return;
                }

                if(telefono != null) {
                    if (!telefono.contains("+")) {
                        throw new UsuarioArgsException("El telefono debe contener prefijos");
                    }
                }

                if(dni == null || dni.endsWith(".*[a-zA-z].*")) {
                    throw new UsuarioArgsException("El dni no es valido");
                }

                return;
            }
            return;
        }
        filterChain.doFilter(request, response);
    }


}
