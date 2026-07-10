package com.Milhas.service;

import com.Milhas.model.FormaPagamento;
import com.Milhas.model.Transacao;
import com.Milhas.repository.TransacaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TransacaoService {

    private final TransacaoRepository transacaoRepository;

    // 🔁 Método para realizar uma transação
    public void realizarTransacao(Long usuarioId, Long ofertaId, FormaPagamento formaPagamento) {
        // Aqui deve estar sua lógica de negócio para criar e salvar a transação
        // Exemplo simplificado:
        Transacao transacao = new Transacao();
        transacao.setUsuarioId(usuarioId);
        transacao.setOfertaId(ofertaId);
        transacao.setFormaPagamento(formaPagamento);
        transacaoRepository.save(transacao);
    }

    // 📥 Novo método para listar todas as transações
    public List<Transacao> listarTransacoes() {
        return transacaoRepository.findAll();
    }
}