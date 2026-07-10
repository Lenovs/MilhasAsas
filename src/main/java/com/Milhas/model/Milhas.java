package com.Milhas.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;


@Entity
@Table(name = "milhas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Milhas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int quantidade;
    private LocalDate validade;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    @JsonManagedReference
    @ToString.Exclude
    private User usuario;
    @OneToOne
    @JoinColumn(name = "companhia_id")
    @JsonManagedReference
    private CompanhiasAereas companhiaArea;


}
