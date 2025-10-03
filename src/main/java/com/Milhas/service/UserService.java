package com.Milhas.service;

import com.Milhas.dto.UserRequestDTO;
import com.Milhas.dto.UserResponseDTO;
import com.Milhas.model.User;
import com.Milhas.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    // 🔁 Criar usuário
    public UserResponseDTO createUser(UserRequestDTO dto) {
        User user = new User(dto.getNome(), dto.getCpf(), dto.getEmail(), dto.getSenha());
        return new UserResponseDTO(userRepository.save(user));
    }

    // 📥 Listar todos os usuários
    public List<UserResponseDTO> listarUser() {
        return userRepository.findAll().stream()
                .map(UserResponseDTO::new)
                .collect(Collectors.toList());
    }

    // 🔍 Buscar usuário por ID
    public UserResponseDTO buscarPorId(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        return new UserResponseDTO(user);
    }

    // ✏️ Atualizar usuário
    public UserResponseDTO atualizarUsuario(Long id, UserRequestDTO dto) {
        User userExistente = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        userExistente.setNome(dto.getNome());
        userExistente.setCpf(dto.getCpf());
        userExistente.setEmail(dto.getEmail());
        userExistente.setSenha(dto.getSenha());

        return new UserResponseDTO(userRepository.save(userExistente));
    }

    // ❌ Deletar usuário
    public void deletarUsuario(Long id) {
        userRepository.deleteById(id);
    }
}