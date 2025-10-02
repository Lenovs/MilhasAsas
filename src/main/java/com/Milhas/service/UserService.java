package com.Milhas.service;

import com.Milhas.model.FormaPagamento;
import com.Milhas.model.Transacao;
import com.Milhas.model.User;
import com.Milhas.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;


    // 🔁 Método para criar Usuario
    public void createUser(long id, String nome, String cpf, String email, String senha) {
        // Aqui deve estar sua lógica de negócio para criar e salvar a transação
        // Exemplo simplificado:
        User  user = new User();
        user.setId(id);
        user.setNome(nome);
        user.setCpf(cpf);
        user.setEmail(email);
        user.setSenha(senha);
        userRepository.save(user);
    }

    // 📥 Novo método para listar todas as transações
    public List<User> listarUser() {
        return userRepository.findAll();
    }
}