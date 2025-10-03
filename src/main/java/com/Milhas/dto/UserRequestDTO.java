package com.Milhas.dto;

import lombok.Data;

@Data
public class UserRequestDTO {
    private String nome;
    private String cpf;
    private String email;
    private String senha;
}