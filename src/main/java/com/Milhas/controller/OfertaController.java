package com.Milhas.controller;
import com.Milhas.dto.OfertaRequestDTO;
import com.Milhas.dto.OfertaResponseDTO;
import com.Milhas.service.OfertaService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/ofertas")
@PreAuthorize("hasRole('GerenteNegocios')")
@RequiredArgsConstructor
public class OfertaController {

    @Qualifier("ofertaServiceImpl") // injeta a implementação correta
    private final OfertaService ofertaService;

    // ➕ POST: Gerente cria oferta (inativa por padrão)
    @PostMapping
    public ResponseEntity<OfertaResponseDTO> createOferta(@RequestBody OfertaRequestDTO dto) {
        // força a oferta a ser criada como inativa
        OfertaResponseDTO created = ofertaService.createRoot(
                new OfertaRequestDTO(
                        dto.tipo(),
                        dto.descricao(),
                        dto.preco(),
                        dto.pontos(),
                        dto.validade(),
                        false // gerente não pode ativar direto
                )
        );
        URI location = URI.create("/ofertas/" + created.id());
        return ResponseEntity.created(location).body(created);
    }

    // 📥 GET: Listar todas as ofertas (ADM e Gerente podem ver)
    @PreAuthorize("hasAnyRole('ADM','GERENTE')")
    @GetMapping
    public ResponseEntity<List<OfertaResponseDTO>> listarOfertas() {
        List<OfertaResponseDTO> ofertas = ofertaService.findAll();
        return ResponseEntity.ok(ofertas);
    }

    // 🔍 GET: Buscar oferta por ID
    @PreAuthorize("hasAnyRole('ADM','GERENTE')")
    @GetMapping("/{id}")
    public ResponseEntity<OfertaResponseDTO> buscarOfertaPorId(@PathVariable Long id) {
        return ofertaService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
