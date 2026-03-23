package com.kanux.controller;

import com.kanux.dto.JwtUtil;
import com.kanux.dto.LoginRequest;
import com.kanux.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Autenticação
 *
 * POST /api/auth/login
 * Body: { "username": "admin", "password": "admin123" }
 * Retorna: { "token": "eyJ..." }
 */
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        try {
            UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());

            // Verifica a senha
            if (!passwordEncoder.matches(request.getPassword(), userDetails.getPassword())) {
                return ResponseEntity.status(401)
                    .body(Map.of("erro", "Credenciais inválidas"));
            }

            // Gera e retorna o token JWT
            String token = jwtUtil.gerarToken(userDetails.getUsername());
            return ResponseEntity.ok(Map.of(
                "token", token,
                "tipo", "Bearer",
                "usuario", userDetails.getUsername()
            ));

        } catch (Exception e) {
            return ResponseEntity.status(401)
                .body(Map.of("erro", "Credenciais inválidas"));
        }
    }
}
