package com.Milhas.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "transacoes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Transacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 🔗 ID do usuário que realizou a transação
    private Long usuarioId;

    // 🔗 ID da oferta utilizada
    private Long ofertaId;

    // 💳 Forma de pagamento (ENUM)
    @Enumerated(EnumType.STRING)
    private FormaPagamento formaPagamento;

    // 🕒 Data e hora da transação
    private LocalDateTime dataTransacao;

    // ✅ Construtor auxiliar para facilitar criação
    public Transacao(Long usuarioId, Long ofertaId, FormaPagamento formaPagamento) {
        this.usuarioId = usuarioId;
        this.ofertaId = ofertaId;
        this.formaPagamento = formaPagamento;
        this.dataTransacao = LocalDateTime.now();
    }
}