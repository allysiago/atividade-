package com.kanux.service;

import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

/**
 * Serviço de autenticação.
 * Em produção, busque o usuário do banco de dados.
 * Aqui usamos um usuário fixo para demonstração.
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Usuário de demonstração: admin / admin123
        if ("admin".equals(username)) {
            return User.builder()
                    .username("admin")
                    // senha: admin123 (codificada com BCrypt)
                    .password("$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy")
                    .roles("ADMIN")
                    .build();
        }
        throw new UsernameNotFoundException("Usuário não encontrado: " + username);
    }
}
