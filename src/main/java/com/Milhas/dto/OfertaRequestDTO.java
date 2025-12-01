package com.Milhas.dto;

import com.Milhas.model.Oferta;
import com.Milhas.model.OfertaTipo;

import java.time.LocalDate;

public record OfertaRequestDTO(
        String tipo,          // nome do enum em texto
        String descricao,
        Double preco,
        Integer pontos,
        LocalDate validade,
        Boolean ativo
) {
    // 🔧 Converte DTO em entidade Oferta
    public Oferta toEntity() {
        Oferta oferta = new Oferta();
        oferta.setOfertaTipo(Enum.valueOf(OfertaTipo.class, tipo)); // usa o enum OfertaTipo
        oferta.setDescricao(descricao);
        oferta.setValor(preco);
        oferta.setMilhasNecessarias(pontos);
        oferta.setValidade(validade);
        oferta.setAtiva(ativo != null ? ativo : true);
        return oferta;
    }
}