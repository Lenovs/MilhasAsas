package com.Milhas.config;

import com.Milhas.model.*;
import com.Milhas.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.util.List;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Configuration
@RequiredArgsConstructor
public class DataLoader {

    private final UserRepository userRepo;
    private final ContaRepository contaRepo;
    private final OfertaRepository ofertaRepo;
    private final MilhasRepository milhasRepo;
    private final TransacaoRepository transacaoRepo; // ✅ Adicione isso

    @Bean
    public CommandLineRunner loadData() {
        return args -> {
            // Criar a conta
            Conta conta = new Conta();
            conta.setSaldo(500.0);

            // Criar o usuário e associar a conta
            User usuario = new User();

            usuario.setConta(conta);
            usuario.setNome("Edileno");

            usuario = userRepo.save(usuario);

            // Criar e salvar a oferta
            Oferta oferta = new Oferta();
            oferta.setDescricao("Passagem para Salvador");
            oferta.setValor(300.0);
            oferta.setMilhasNecessarias(1000);
            oferta = ofertaRepo.save(oferta);

            // Criar e salvar milhas
            Milhas milhas = new Milhas();
            milhas.setQuantidade(1500);
            milhas.setUsuario(usuario);
            milhas = milhasRepo.save(milhas);

            // Atualizar o usuário com a lista de milhas
            usuario.setMilhas(List.of(milhas));
            userRepo.save(usuario);

            // ✅ Criar e salvar uma transação
            Transacao transacao = new Transacao(usuario.getId(), oferta.getId(), FormaPagamento.MILHAS);
            transacao.setDataTransacao(LocalDateTime.now());
            transacaoRepo.save(transacao);

            System.out.println("✅ Dados de teste carregados com sucesso!");
            System.out.println(usuario.getConta());

        };
    }
}