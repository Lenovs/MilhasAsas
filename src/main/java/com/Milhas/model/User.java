package com.Milhas.user;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String cpf;
    private String email;
    private String senha;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<CartaoBancario> cartoes;

    @OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL)
    private Conta conta;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<Milhas> milhas;
}