package com.Milhas.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.catalina.User;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartaoBancario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String numero;
    private String nomeTitular;
    private String validade;
    private String bandeira;

    @ManyToOne
    private User usuario;
}
