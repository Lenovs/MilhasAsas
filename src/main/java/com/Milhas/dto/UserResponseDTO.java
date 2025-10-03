package com.Milhas.dto;

import com.Milhas.model.User;
import lombok.Data;

@Data
public class UserResponseDTO {
    private Long id;
    private String nome;
    private String cpf;
    private String email;

    public UserResponseDTO(User user) {
        this.id = user.getId();
        this.nome = user.getNome();
        this.cpf = user.getCpf();
        this.email = user.getEmail();
    }
}
