package com.Milhas.controller;

import com.Milhas.dto.UserRequestDTO;
import com.Milhas.dto.UserResponseDTO;
import com.Milhas.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // 🔁 POST: Criar novo usuário (rota pública)
    @PostMapping
    public ResponseEntity<UserResponseDTO> createUser(@RequestBody UserRequestDTO dto) {
        UserResponseDTO novoUsuario = userService.createUser(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoUsuario);
    }

    // 📥 GET: Listar todos os usuários (ADM)
    @PreAuthorize("hasRole('ADM')")
    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> listarUser() {
        return ResponseEntity.ok(userService.listarUser());
    }

    // 🔍 GET: Buscar usuário por ID (ADM)
    @PreAuthorize("hasRole('ADM')")
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(userService.buscarPorId(id));
    }

    // ✏️ PUT: Atualizar usuário (ADM)
    @PreAuthorize("hasRole('ADM')")
    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDTO> atualizarUsuario(@PathVariable Long id, @RequestBody UserRequestDTO dto) {
        return ResponseEntity.ok(userService.atualizarUsuario(id, dto));
    }

    // ❌ DELETE: Remover usuário (ADM)
    @PreAuthorize("hasRole('ADM')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarUsuario(@PathVariable Long id) {
        userService.deletarUsuario(id);
        return ResponseEntity.noContent().build();
    }

    // 👤 GET: Perfil do usuário logado (UserPlataforma)
    @PreAuthorize("hasRole('UserPlataforma')")
    @GetMapping("/me")
    public ResponseEntity<String> buscarMeuPerfil(Authentication auth) {
        String email = auth.getName();
        String role = auth.getAuthorities().toString();
        return ResponseEntity.ok("Usuário logado: " + email + " | Perfil: " + role);
    }
}