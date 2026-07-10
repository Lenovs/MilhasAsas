package com.Milhas.dto;

import com.Milhas.model.Oferta;
import java.time.LocalDate;

public record OfertaResponseDTO(
        Long id,
        String tipo,
        String descricao,
        Double preco,
        Integer pontos,
        LocalDate validade,
        Boolean ativo
) {
    // 🔧 Método auxiliar para converter entidade em DTO
    public static OfertaResponseDTO fromEntity(Oferta oferta) {
        return new OfertaResponseDTO(
                oferta.getId(),
                oferta.getOfertaTipo() != null ? oferta.getOfertaTipo().name() : null, // supondo que tipo seja enum
                oferta.getDescricao(),
                oferta.getValor(),
                oferta.getMilhasNecessarias(),
                oferta.getValidade(),
                oferta.isAtiva()
        );
    }
}