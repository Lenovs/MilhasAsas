package com.Milhas.service;

import com.Milhas.dto.OfertaRequestDTO;
import com.Milhas.dto.OfertaResponseDTO;

import java.util.List;
import java.util.Optional;

public interface OfertaService {

    // 🔎 Buscar todas as ofertas

    List<OfertaResponseDTO> findAll();

    // 🔎 Buscar oferta por ID
    Optional<OfertaResponseDTO> findById(Long id);

    // ➕ Criar oferta raiz (sem pai)
    OfertaResponseDTO createRoot(OfertaRequestDTO dto);

    // ➕ Criar oferta filha vinculada a uma oferta pai
    OfertaResponseDTO createChild(Long parentId, OfertaRequestDTO dto);

    // ✏️ Atualizar oferta raiz
    OfertaResponseDTO updateRoot(Long id, OfertaRequestDTO dto);

    // ✏️ Atualizar oferta filha
    OfertaResponseDTO updateChild(Long parentId, Long childId, OfertaRequestDTO dto);

    // ❌ Deletar oferta raiz
    void deleteRoot(Long id);

    // ❌ Deletar oferta filha
    void deleteChild(Long parentId, Long childId);
}