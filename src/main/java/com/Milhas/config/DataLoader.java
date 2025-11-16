package com.Milhas.config;

import com.Milhas.model.*;
import com.Milhas.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.List;

@Configuration
@RequiredArgsConstructor
public class DataLoader {

    private final UserRepository userRepo;
    private final ContaRepository contaRepo;
    private final OfertaRepository ofertaRepo;
    private final MilhasRepository milhasRepo;
    private final TransacaoRepository transacaoRepo;
    private final PasswordEncoder passwordEncoder;

    @Bean
    public CommandLineRunner loadData() {
        return args -> {

            if (userRepo.count() == 0) {
                User adm = new User("Admin", "00000000000", "adm@email.com", passwordEncoder.encode("123456"));
                adm.setRole(LoginRole.ADM);
                adm = userRepo.save(adm);

                User gerente = new User("Gerente", "11111111111", "gerente@email.com", passwordEncoder.encode("123456"));
                gerente.setRole(LoginRole.GerenteNegocios);
                gerente = userRepo.save(gerente);

                User comum = new User("Usuário", "22222222222", "user@email.com", passwordEncoder.encode("123456"));
                comum.setRole(LoginRole.UserPlataforma);
                comum = userRepo.save(comum);
            }

            Conta conta = new Conta();
            conta.setSaldo(500.0);

            User usuario = new User();
            usuario.setNome("Edileno");
            usuario.setCpf("33333333333");
            usuario.setEmail("edileno@email.com");
            usuario.setSenha(passwordEncoder.encode("123456"));
            usuario.setRole(LoginRole.UserPlataforma);
            usuario.setConta(conta);

            usuario = userRepo.save(usuario);

            Oferta oferta = new Oferta();
            oferta.setDescricao("Passagem para Salvador");
            oferta.setValor(300.0);
            oferta.setMilhasNecessarias(1000);
            oferta = ofertaRepo.save(oferta);

            Milhas milhas = new Milhas();
            milhas.setQuantidade(1500);
            milhas.setUsuario(usuario);
            milhas = milhasRepo.save(milhas);

            usuario.setMilhas(List.of(milhas));
            userRepo.save(usuario);

            Transacao transacao = new Transacao(usuario.getId(), oferta.getId(), FormaPagamento.MILHAS);
            transacao.setDataTransacao(LocalDateTime.now());
            transacaoRepo.save(transacao);

            System.out.println("✅ Dados de teste carregados com sucesso!");
            System.out.println("Usuário: " + usuario.getEmail() + " | Role: " + usuario.getRole());
        };
    }
}