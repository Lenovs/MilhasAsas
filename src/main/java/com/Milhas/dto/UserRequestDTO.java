package com.Milhas.dto;

import com.Milhas.model.LoginRole;
import lombok.Data;

@Data
public class UserRequestDTO {
    private String nome;
    private String cpf;
    private String email;
    private String senha;
    private LoginRole role;


}