package com.Milhas.service.impl;

import com.Milhas.dto.OfertaRequestDTO;
import com.Milhas.dto.OfertaResponseDTO;
import com.Milhas.model.Oferta;
import com.Milhas.model.OfertaTipo;
import com.Milhas.repository.OfertaRepository;
import com.Milhas.service.OfertaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service("ofertaServiceImpl")
public class OfertaServiceImpl implements OfertaService {

    private final OfertaRepository ofertaRepository;

    public OfertaServiceImpl(OfertaRepository ofertaRepository) {
        this.ofertaRepository = ofertaRepository;
    }

    @Override
    public List<OfertaResponseDTO> findAll() {
        return ofertaRepository.findAll()
                .stream()
                .map(OfertaResponseDTO::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<OfertaResponseDTO> findById(Long id) {
        return ofertaRepository.findById(id).map(OfertaResponseDTO::fromEntity);
    }

    @Override
    public OfertaResponseDTO createRoot(OfertaRequestDTO dto) {
        Oferta oferta = dto.toEntity();
        Oferta saved = ofertaRepository.save(oferta);
        return OfertaResponseDTO.fromEntity(saved);
    }

    @Override
    public OfertaResponseDTO createChild(Long parentId, OfertaRequestDTO dto) {
        Oferta oferta = dto.toEntity();
        oferta.setOfertaPai(ofertaRepository.findById(parentId).orElse(null));
        Oferta saved = ofertaRepository.save(oferta);
        return OfertaResponseDTO.fromEntity(saved);
    }

    @Override
    public OfertaResponseDTO updateRoot(Long id, OfertaRequestDTO dto) {
        Oferta oferta = ofertaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Oferta não encontrada"));

        oferta.setDescricao(dto.descricao());
        oferta.setValor(dto.preco());
        oferta.setMilhasNecessarias(dto.pontos());
        oferta.setValidade(dto.validade());
        oferta.setAtiva(dto.ativo());
        oferta.setOfertaTipo(Enum.valueOf(OfertaTipo.class, dto.tipo()));

        Oferta updated = ofertaRepository.save(oferta);
        return OfertaResponseDTO.fromEntity(updated);
    }

    @Override
    public OfertaResponseDTO updateChild(Long parentId, Long childId, OfertaRequestDTO dto) {
        Oferta oferta = ofertaRepository.findById(childId)
                .orElseThrow(() -> new RuntimeException("Oferta filha não encontrada"));

        oferta.setDescricao(dto.descricao());
        oferta.setValor(dto.preco());
        oferta.setMilhasNecessarias(dto.pontos());
        oferta.setValidade(dto.validade());
        oferta.setAtiva(Optional.ofNullable(dto.ativo()).orElse(true));
        oferta.setOfertaTipo(OfertaTipo.valueOf(dto.tipo().toUpperCase()));


        // vincula ao pai, se existir
        oferta.setOfertaPai(ofertaRepository.findById(parentId).orElse(null));

        Oferta updated = ofertaRepository.save(oferta);
        return OfertaResponseDTO.fromEntity(updated);
    }

    @Override
    public void deleteRoot(Long id) {
        ofertaRepository.deleteById(id);
    }

    @Override
    public void deleteChild(Long parentId, Long childId) {
        Oferta child = ofertaRepository.findById(childId)
                .orElseThrow(() -> new RuntimeException("Oferta filha não encontrada"));
        if (child.getOfertaPai() == null || !child.getOfertaPai().getId().equals(parentId)) {
            throw new RuntimeException("Oferta filha não pertence ao pai informado");
        }
        ofertaRepository.delete(child);
    }

}