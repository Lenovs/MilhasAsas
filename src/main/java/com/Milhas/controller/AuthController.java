package com.Milhas.controller;


import com.Milhas.model.User;
import com.Milhas.model.dto.LoginRequest;
import com.Milhas.model.dto.LoginResponse;
import com.Milhas.service.JwtUtil;
import com.Milhas.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class AuthController {

    @Autowired
    private LoginService loginService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping
    public ResponseEntity<LoginResponse> autenticar(@RequestBody LoginRequest loginRequest) {
        User usuario = loginService.autenticar(loginRequest.getEmail(), loginRequest.getSenha());

        if (usuario == null) {
            return ResponseEntity.status(401).build(); // Unauthorized
        }

        String token = jwtUtil.gerarToken(usuario.getId());
        return ResponseEntity.ok(new LoginResponse(token));
    }
}