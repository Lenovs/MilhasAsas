package com.Milhas.controller;

import com.Milhas.model.FormaPagamento;
import com.Milhas.model.Transacao;
import com.Milhas.service.TransacaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transacoes")
@RequiredArgsConstructor
public class TransacaoController {

    private final TransacaoService transacaoService;

    // 🔁 POST: realiza uma transação
    @PostMapping
    public String realizarTransacao(
            @RequestParam Long usuarioId,
            @RequestParam Long ofertaId,
            @RequestParam FormaPagamento formaPagamento
    ) {
        transacaoService.realizarTransacao(usuarioId, ofertaId, formaPagamento);
        return "Transação realizada com sucesso!";
    }

    // 📥 GET: lista todas as transações
    @GetMapping
    public List<Transacao> listarTransacoes() {
        return transacaoService.listarTransacoes();
    }
}