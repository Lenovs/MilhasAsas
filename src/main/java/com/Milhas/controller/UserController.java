package com.Milhas.controller;

import com.Milhas.dto.UserRequestDTO;
import com.Milhas.dto.UserResponseDTO;
import com.Milhas.service.UserService;
import jakarta.validation.Valid;
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

    @PostMapping
    public ResponseEntity<UserResponseDTO> createUser(@RequestBody UserRequestDTO dto, Authentication auth) {
        UserResponseDTO novoUsuario = userService.createUser(dto, auth);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoUsuario);
    }

    @PreAuthorize("hasRole('ADM')")
    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> listarUser() {
        return ResponseEntity.ok(userService.listarUser());
    }

    @PreAuthorize("hasRole('ADM')")
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(userService.buscarPorId(id));
    }

    @PreAuthorize("hasRole('ADM')")
    @GetMapping("/email/{email}")
    public ResponseEntity<UserResponseDTO> buscarPorEmail(@PathVariable String email) {
        return ResponseEntity.ok(userService.buscarPorEmail(email));
    }

    @PreAuthorize("hasRole('ADM')")
    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDTO> atualizarUsuario(@PathVariable Long id, @Valid @RequestBody UserRequestDTO dto) {
        return ResponseEntity.ok(userService.atualizarUsuario(id, dto));
    }

    @PreAuthorize("hasRole('ADM')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarUsuario(@PathVariable Long id) {
        userService.deletarUsuario(id);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasRole('UserPlataforma')")
    @GetMapping("/me/{email}")
    public ResponseEntity<UserResponseDTO> buscarMeuPerfil(Authentication auth) {
        String email = auth.getName();
        UserResponseDTO usuario = userService.buscarPorEmail(email);
        return ResponseEntity.ok(usuario);
    }
}