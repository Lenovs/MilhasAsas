package com.Milhas.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.security.SecurityProperties;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Milhas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String companhiaAerea;
    private int quantidade;

    @ManyToOne
    private SecurityProperties.User usuario;
}
