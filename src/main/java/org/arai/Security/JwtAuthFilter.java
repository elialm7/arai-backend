package org.arai.Security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.arai.Model.JwtClaim.JwtClaims;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
public class JwtAuthFilter  extends OncePerRequestFilter {

    private final JwtManager jwtManager;

    private static final List<String> EXCLUDED_PATHS = List.of("/auth/login");

    public JwtAuthFilter(JwtManager jwtManager) {
        this.jwtManager = jwtManager;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {
        String path = request.getRequestURI();
        if(EXCLUDED_PATHS.contains(path)) {
            filterChain.doFilter(request, response);
            return;
        }
        String authHeader = request.getHeader("Authorization");
        if(authHeader == null || !authHeader.startsWith("Bearer ")) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token no proporcionado!!");
            return;
        }
        String token = authHeader.substring(6).trim();
        try{
            JwtClaims claims = jwtManager.parseAndValidateToken(token);
            request.setAttribute("jwtClaims", claims);
            filterChain.doFilter(request, response);
        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Error de autenticacion!!");
        }
    }}
