package com.Milhas.service;

import com.Milhas.model.User;
import com.Milhas.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    private UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public User autenticar(String email, String senha) {
        User usuario = userRepository.findByEmail(email);

        if (usuario != null && passwordEncoder.matches(senha, usuario.getSenha())) {
            return usuario;
        }

        return null;
    }
}