package com.Milhas.config;

import com.Milhas.model.*;
import com.Milhas.repository.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
            try {
                seedData();
                System.out.println("✅ Dados de teste carregados com sucesso!");
            } catch (Exception e) {
                e.printStackTrace();
                System.err.println("Erro ao carregar dados de teste: " + e.getMessage());
            }
        };
    }

    @Transactional
    public void seedData() {
        // 🔧 Removemos o if (userRepo.count() == 0) para SEMPRE inserir os dados
        // Se quiser evitar duplicados, pode limpar a tabela antes:
        userRepo.deleteAll();
        contaRepo.deleteAll();
        ofertaRepo.deleteAll();
        milhasRepo.deleteAll();
        transacaoRepo.deleteAll();

        // Usuários
        User adm = new User("Admin", "00000000000", "adm@email.com", passwordEncoder.encode("123456"));
        adm.setRole(LoginRole.ADM);
        userRepo.save(adm);

        User gerente = new User("Gerente", "11111111111", "gerente@email.com", passwordEncoder.encode("123456"));
        gerente.setRole(LoginRole.GerenteNegocios);
        userRepo.save(gerente);

        User comum = new User("Usuário", "22222222222", "user@email.com", passwordEncoder.encode("123456"));
        comum.setRole(LoginRole.UserPlataforma);
        userRepo.save(comum);

        // Conta + Usuário Edileno
        Conta conta = new Conta();
        conta.setSaldo(500.0);
        conta = contaRepo.save(conta);

        User usuario = new User("Edileno", "33333333333", "edileno@email.com",
                passwordEncoder.encode("123456"));
        usuario.setRole(LoginRole.UserPlataforma);
        usuario.setConta(conta);
        usuario = userRepo.save(usuario);

        // Ofertas
        Oferta pai = new Oferta(OfertaTipo.PROMO, "Pacote Família", 1000.0, 5000,
                LocalDate.now().plusMonths(3), true);
        Oferta filho1 = new Oferta(OfertaTipo.PROMO, "Voo ida", 300.0, 1000,
                LocalDate.now().plusMonths(1), true);
        Oferta filho2 = new Oferta(OfertaTipo.PROMO, "Hotel 3 noites", 400.0, 2000,
                LocalDate.now().plusMonths(1), true);

        filho1.setOfertaPai(pai);
        filho2.setOfertaPai(pai);
        pai.getMinhasOfertas().add(filho1);
        pai.getMinhasOfertas().add(filho2);

        Oferta savedPai = ofertaRepo.save(pai);

        // Milhas
        Milhas milhas = new Milhas();
        milhas.setQuantidade(1500);
        milhas.setUsuario(usuario);
        Milhas savedMilhas = milhasRepo.save(milhas);

        usuario.setMilhas(new ArrayList<>(List.of(savedMilhas)));
        userRepo.save(usuario);

        // Transação
        Transacao transacao = new Transacao(usuario.getId(), savedPai.getId(), FormaPagamento.MILHAS);
        transacao.setDataTransacao(LocalDateTime.now());
        transacaoRepo.save(transacao);

        System.out.println("Usuário de teste criado: " + usuario.getEmail() + " | Role: " + usuario.getRole());
    }
}