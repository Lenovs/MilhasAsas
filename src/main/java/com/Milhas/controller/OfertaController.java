package com.Milhas.controller;

import com.Milhas.dto.OfertaResponseDTO;
import com.Milhas.service.OfertaService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ofertas")
@RequiredArgsConstructor
public class OfertaController {
    @Qualifier("ofertaServiceImpl") // injeta a implementação correta
    private final OfertaService ofertaService;

    // 📥 GET: Listar todas as ofertas
    @GetMapping
    public ResponseEntity<List<OfertaResponseDTO>> listarOfertas() {
        List<OfertaResponseDTO> ofertas = ofertaService.findAll(); // ✅ método correto
        return ResponseEntity.ok(ofertas);
    }

    // 🔍 GET: Buscar oferta por ID
    @GetMapping("/{id}")
    public ResponseEntity<OfertaResponseDTO> buscarOfertaPorId(@PathVariable Long id) {
        return ofertaService.findById(id) // ✅ retorna Optional
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}