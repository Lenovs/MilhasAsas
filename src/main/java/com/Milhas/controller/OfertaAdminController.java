package com.Milhas.controller;

import com.Milhas.dto.OfertaRequestDTO;
import com.Milhas.dto.OfertaResponseDTO;
import com.Milhas.service.OfertaService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/admin/ofertas")
@RequiredArgsConstructor
public class OfertaAdminController {
    @Qualifier("ofertaAdminService")
    private final OfertaService ofertaService;

    // 📥 GET: Listar todas as ofertas
    @GetMapping
    public ResponseEntity<List<OfertaResponseDTO>> listAll() {
        List<OfertaResponseDTO> list = ofertaService.findAll();
        return ResponseEntity.ok(list);
    }

    // 🔍 GET: Buscar oferta por ID
    @GetMapping("/{id}")
    public ResponseEntity<OfertaResponseDTO> getById(@PathVariable Long id) {
        return ofertaService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // ➕ POST: Criar oferta raiz
    @PostMapping
    public ResponseEntity<OfertaResponseDTO> createRoot(@RequestBody OfertaRequestDTO dto) {
        OfertaResponseDTO created = ofertaService.createRoot(dto);
        URI location = URI.create("/admin/ofertas/" + created.id());
        return ResponseEntity.created(location).body(created);
    }

    // ➕ POST: Criar oferta filha
    @PostMapping("/{parentId}/children")
    public ResponseEntity<OfertaResponseDTO> createChild(@PathVariable Long parentId,
                                                         @RequestBody OfertaRequestDTO dto) {
        OfertaResponseDTO created = ofertaService.createChild(parentId, dto);
        URI location = URI.create("/admin/ofertas/" + created.id());
        return ResponseEntity.created(location).body(created);
    }

    // ❌ DELETE: Remover oferta raiz
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRoot(@PathVariable Long id) {
        ofertaService.deleteRoot(id);
        return ResponseEntity.noContent().build();
    }

    // ❌ DELETE: Remover oferta filha
    @DeleteMapping("/{parentId}/children/{childId}")
    public ResponseEntity<Void> deleteChild(@PathVariable Long parentId,
                                            @PathVariable Long childId) {
        ofertaService.deleteChild(parentId, childId);
        return ResponseEntity.noContent().build();
    }
}