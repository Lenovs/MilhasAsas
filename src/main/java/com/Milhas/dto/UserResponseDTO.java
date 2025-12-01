package com.Milhas.dto;

import com.Milhas.model.LoginRole;
import com.Milhas.model.User;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

@Data
public class UserResponseDTO {

    private Long id;
    private String nome;
    private String cpf;
    private String email;
    @Enumerated(EnumType.STRING)
    private LoginRole role;

    // Construtor vazio para frameworks
    public UserResponseDTO() {}

    // Construtor a partir da entidade
    public UserResponseDTO(User user) {
        this.id = user.getId();
        this.nome = user.getNome();
        this.cpf = user.getCpf();
        this.email = user.getEmail();
        this.role = (user.getRole() != null) ? user.getRole() : LoginRole.UserPlataforma;
    }

    // Método estático para conversão
    public static UserResponseDTO fromEntity(User user) {
        return new UserResponseDTO(user);
    }
}