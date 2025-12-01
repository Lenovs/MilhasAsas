package com.Milhas.service;

import com.Milhas.dto.OfertaRequestDTO;
import com.Milhas.dto.OfertaResponseDTO;
import com.Milhas.exception.ResourceNotFoundException;
import com.Milhas.model.Oferta;
import com.Milhas.model.OfertaTipo;
import com.Milhas.repository.OfertaRepository;
import com.Milhas.service.OfertaService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Primary
@RequiredArgsConstructor
public class OfertaAdminService implements OfertaService {

    private final OfertaRepository ofertaRepository;

    // Converte entidade em DTO
    private OfertaResponseDTO toDto(Oferta o) {
        return new OfertaResponseDTO(
                o.getId(),
                o.getOfertaTipo() != null ? o.getOfertaTipo().name() : null, // ✅ enum -> String
                o.getDescricao(),
                o.getValor(),
                o.getMilhasNecessarias(),
                o.getValidade(),
                o.isAtiva()
        );
    }

    // Atualiza entidade a partir do DTO
    private void updateEntityFromDto(Oferta entity, OfertaRequestDTO dto) {
        entity.setOfertaTipo(Enum.valueOf(OfertaTipo.class, dto.tipo())); // ✅ String -> enum
        entity.setDescricao(dto.descricao());
        entity.setValor(dto.preco());
        entity.setMilhasNecessarias(dto.pontos());
        entity.setValidade(dto.validade());
        entity.setAtiva(dto.ativo());
    }

    // Busca oferta ou lança exceção
    private Oferta getOfertaOrThrow(Long id) {
        return ofertaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Oferta não encontrada: " + id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<OfertaResponseDTO> findAll() {
        return ofertaRepository.findAll()
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<OfertaResponseDTO> findById(Long id) {
        return ofertaRepository.findById(id).map(this::toDto);
    }

    @Override
    @Transactional
    public OfertaResponseDTO createRoot(OfertaRequestDTO dto) {
        Oferta oferta = new Oferta(
                Enum.valueOf(OfertaTipo.class, dto.tipo()), // ✅ corrigido
                dto.descricao(),
                dto.preco(),
                dto.pontos(),
                dto.validade(),
                dto.ativo()
        );
        return toDto(ofertaRepository.save(oferta));
    }

    @Override
    @Transactional
    public OfertaResponseDTO createChild(Long parentId, OfertaRequestDTO dto) {
        Oferta parent = getOfertaOrThrow(parentId);

        Oferta child = new Oferta(
                Enum.valueOf(OfertaTipo.class, dto.tipo()), // ✅ corrigido
                dto.descricao(),
                dto.preco(),
                dto.pontos(),
                dto.validade(),
                dto.ativo()
        );

        child.setOfertaPai(parent);
        parent.getMinhasOfertas().add(child);

        ofertaRepository.save(parent);

        return toDto(child);
    }

    @Override
    @Transactional
    public OfertaResponseDTO updateRoot(Long id, OfertaRequestDTO dto) {
        Oferta existing = getOfertaOrThrow(id);
        updateEntityFromDto(existing, dto);
        return toDto(ofertaRepository.save(existing));
    }

    @Override
    @Transactional
    public OfertaResponseDTO updateChild(Long parentId, Long childId, OfertaRequestDTO dto) {
        Oferta parent = getOfertaOrThrow(parentId);

        Oferta child = parent.getMinhasOfertas()
                .stream()
                .filter(f -> f.getId().equals(childId))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Oferta filha não encontrada: " + childId));

        updateEntityFromDto(child, dto);
        ofertaRepository.save(parent);

        return toDto(child);
    }

    @Override
    @Transactional
    public void deleteRoot(Long id) {
        Oferta existing = getOfertaOrThrow(id);
        ofertaRepository.delete(existing);
    }

    @Override
    @Transactional
    public void deleteChild(Long parentId, Long childId) {
        Oferta parent = getOfertaOrThrow(parentId);

        boolean removed = parent.getMinhasOfertas().removeIf(f -> f.getId().equals(childId));
        if (!removed) {
            throw new ResourceNotFoundException("Oferta filha não encontrada: " + childId);
        }
        ofertaRepository.save(parent);
    }
}