package com.Milhas.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "companhias_aereas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CompanhiasAereas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @OneToOne(mappedBy = "companhiaArea")
    @JsonBackReference
    @ToString.Exclude
    private Milhas milhas;
}