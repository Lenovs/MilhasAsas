package com.Milhas.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;


@Entity
@Table( name = "cartao_bancario")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CartaoBancario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String numero;
    private String nomeTitular;
    private String validade;
    private String bandeira;
    private String nvc ;


    @ManyToOne
    @JoinColumn (name = "usuario_id")
    @JsonBackReference
    @ToString.Exclude
    private User usuario;
}
