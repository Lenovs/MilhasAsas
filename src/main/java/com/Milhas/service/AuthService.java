package com.Milhas.service;

import com.Milhas.dto.LoginRequestDTO;
import com.Milhas.dto.LoginResponseDTO;
import com.Milhas.model.User;
import com.Milhas.repository.UserRepository;
import com.Milhas.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    public LoginResponseDTO autenticar(LoginRequestDTO dto) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getSenha())
            );

            User user = userRepository.findByEmail(dto.getEmail())
                    .orElseThrow(() -> new BadCredentialsException("Usuário não encontrado"));

            String token = jwtUtil.gerarToken(user.getEmail(), user.getRole().name());

            return new LoginResponseDTO(token, user.getId(), user.getEmail(), user.getRole().name());

        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Email ou senha inválidos");
        }
    }
}