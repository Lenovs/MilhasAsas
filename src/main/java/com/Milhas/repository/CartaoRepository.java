package com.Milhas.repository;


import com.Milhas.model.CartaoBancario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartaoRepository extends JpaRepository<CartaoBancario, Long> {
}
