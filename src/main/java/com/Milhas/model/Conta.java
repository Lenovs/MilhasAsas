package com.Milhas.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "contas")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Conta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double saldo = 0.0;

    // Se quiser referência bidirecional, descomente e ajuste mappedBy no User
    // @OneToOne(mappedBy = "conta", fetch = FetchType.LAZY)
    // private User usuario;
}