package com.Milhas.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Transacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate data;
    private double valor;
    private String tipo; // CARTAO, SALDO, MILHAS

    @ManyToOne
    private User usuario;

    @ManyToOne
    private Oferta oferta;
}
