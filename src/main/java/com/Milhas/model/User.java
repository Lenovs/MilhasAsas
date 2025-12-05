package com.Milhas.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String cpf;

    @Column(unique = true, nullable = false)
    private String email;

    private String senha;

    // 🔑 Enum armazenado como texto no banco
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private LoginRole role = LoginRole.UserPlataforma; // valor padrão

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "conta_id")
    private Conta conta;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Milhas> milhas = new ArrayList<>();

    // Construtor para usuários comuns
    public User(String nome, String cpf, String email, String senha) {
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.senha = senha;
        this.role = LoginRole.UserPlataforma;
    }

    // Construtor para criar usuários com role específico
    public User(String nome, String cpf, String email, String senha, LoginRole role) {
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.senha = senha;
        this.role = role;
    }

//    public void addMilhas(Milhas m) {
//        milhas.add(m);
//        m.setUsuario(this);
//    }
//
//    public void removeMilhas(Milhas m) {
//        milhas.remove(m);
//        m.setUsuario(null);
//    }
}