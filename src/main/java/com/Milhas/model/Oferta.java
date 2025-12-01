package com.Milhas.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Oferta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private OfertaTipo ofertaTipo;

    private String descricao;
    private double valor;
    private int milhasNecessarias;
    private LocalDate validade;
    private boolean ativa = true;

    // lado dono da relação: cada oferta filha aponta para seu ofertaPai
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "oferta_pai_id")
    @JsonBackReference
    private Oferta ofertaPai;

    // lado inverso: lista de filhos
    @OneToMany(mappedBy = "ofertaPai", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Oferta> minhasOfertas = new ArrayList<>();

    // Construtor útil sem id e sem lista (ativa por parâmetro)
    public Oferta(OfertaTipo ofertaTipo, String descricao, double valor,
                  int milhasNecessarias, LocalDate validade, boolean ativa) {
        this.ofertaTipo = ofertaTipo;
        this.descricao = descricao;
        this.valor = valor;
        this.milhasNecessarias = milhasNecessarias;
        this.validade = validade;
        this.ativa = ativa;
        this.ofertaPai = null;
        this.minhasOfertas = new ArrayList<>();
    }
}