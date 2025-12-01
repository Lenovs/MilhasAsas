package com.Milhas.service;

import com.Milhas.model.User; // sua entidade de usuário
import com.Milhas.repository.UserRepository; // seu repositório de usuários
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("userDetailsServiceImpl") // nome explícito para evitar conflito
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Busca usuário no banco
        User user = userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado: " + username));

        // Converte para UserDetails do Spring Security
        return org.springframework.security.core.userdetails.User
                .withUsername(user.getEmail())
                .password(user.getSenha()) // já deve estar criptografada com BCrypt
                .roles(user.getRole().name()) // enum Role → "ADM", "UserPlataforma", etc.
                .build();
    }
}