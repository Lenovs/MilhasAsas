package com.Milhas.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "usuarios")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String cpf;
    private String email;
    private String senha;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<CartaoBancario> cartoes;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "conta_id") // ✅ User é o dono da relação
    private Conta conta;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<Milhas> milhas;
}