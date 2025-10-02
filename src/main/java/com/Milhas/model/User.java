package com.Milhas.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "usuarios")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String cpf;
    private String email;

    @ToString.Exclude
    private String senha;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    @JsonManagedReference
    @ToString.Exclude
    private List<CartaoBancario> cartoes;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "conta_id")
    @JsonManagedReference
    @ToString.Exclude
    private Conta conta;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    @JsonBackReference
    @ToString.Exclude
    private List<Milhas> milhas;

    public User(Long id, String nome, String cpf, String email, String senha) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.senha = senha;
    }
}