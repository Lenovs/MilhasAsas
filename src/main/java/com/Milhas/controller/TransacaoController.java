package com.Milhas.TransacaoController;

import com.Milhas.user.FormaPagamento;
import com.Milhas.service.TransacaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transacoes")
@RequiredArgsConstructor
public class TransacaoController {

    private final TransacaoService transacaoService;

    @PostMapping
    public ResponseEntity<String> realizarTransacao(
            @RequestParam Long usuarioId,
            @RequestParam Long ofertaId,
            @RequestParam FormaPagamento forma
    ) {
        transacaoService.realizarTransacao(usuarioId, ofertaId, forma);
        return ResponseEntity.ok("Transação realizada com sucesso!");
    }
}