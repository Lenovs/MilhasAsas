package com.Milhas.controller;

import com.Milhas.dto.UserRequestDTO;
import com.Milhas.dto.UserResponseDTO;
import com.Milhas.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // 🔁 POST: Criar novo usuário
    @PostMapping("/create")
    public ResponseEntity<UserResponseDTO> createUser(@RequestBody UserRequestDTO dto) {
        UserResponseDTO novoUsuario = userService.createUser(dto);
        return ResponseEntity.ok(novoUsuario);
    }

    // 📥 GET: Listar todos os usuários
    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> listarUser() {
        List<UserResponseDTO> usuarios = userService.listarUser();
        return ResponseEntity.ok(usuarios);
    }

    // 🔍 GET: Buscar usuário por ID
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> buscarPorId(@PathVariable Long id) {
        UserResponseDTO usuario = userService.buscarPorId(id);
        return ResponseEntity.ok(usuario);
    }

    // ✏️ PUT: Atualizar usuário
    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDTO> atualizarUsuario(@PathVariable Long id, @RequestBody UserRequestDTO dto) {
        UserResponseDTO usuarioAtualizado = userService.atualizarUsuario(id, dto);
        return ResponseEntity.ok(usuarioAtualizado);
    }

    // ❌ DELETE: Remover usuário
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarUsuario(@PathVariable Long id) {
        userService.deletarUsuario(id);
        return ResponseEntity.noContent().build();
    }
}