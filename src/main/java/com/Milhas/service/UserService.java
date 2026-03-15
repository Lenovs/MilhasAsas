package com.Milhas.service;

import com.Milhas.dto.UserRequestDTO;
import com.Milhas.dto.UserResponseDTO;
import com.Milhas.model.LoginRole;
import com.Milhas.model.User;
import com.Milhas.repository.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // 🔁 Criar usuário
    public UserResponseDTO createUser(@Valid UserRequestDTO dto, Authentication auth) {

        String roleToAssign;

        if (auth == null) {
            // Usuário não autenticado → sempre cria UserPlataforma
            roleToAssign = "UserPlataforma";
        } else {
            boolean isAdmin = auth.getAuthorities().stream()
                    .anyMatch(a -> a.getAuthority().equals("ROLE_ADM"));

            if (isAdmin) {
                // ADM pode criar ADM ou Gerente
                if (dto.getRole() == LoginRole.ADM || dto.getRole() == LoginRole.GerenteNegocios) {
                    roleToAssign = dto.getRole().name();
                } else {
                    throw new IllegalArgumentException("ADM só pode criar usuários com role ADM ou Gerente");
                }
            } else {
                // Qualquer outro usuário autenticado → cria UserPlataforma
                roleToAssign = "UserPlataforma";
            }
        }

        User novoUsuario = new User();
        novoUsuario.setNome(dto.getNome());
        novoUsuario.setCpf(dto.getCpf());
        novoUsuario.setEmail(dto.getEmail());
        novoUsuario.setSenha(passwordEncoder.encode(dto.getSenha()));
        novoUsuario.setRole(dto.getRole());

        userRepository.save(novoUsuario);

        return new UserResponseDTO(novoUsuario);
    }

    // 📥 Listar todos os usuários
    public List<UserResponseDTO> listarUser(UserResponseDTO dto,Authentication auth) {

        try{
            if(auth.getAuthorities().stream()
                    .noneMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_ADM"))){
                throw new RuntimeException("Acesso negado: apenas administradores podem listar usuários.");
            }
        return userRepository.findAll().stream()
                .map(UserResponseDTO::new)
                .collect(Collectors.toList());

        } catch (RuntimeException e) {
        throw e; // Repassa a exceção com a mensagem definida
    } catch (Exception e) {
        throw new RuntimeException("Erro ao listar usuários", e);
    }

}

    // 🔍 Buscar usuário por ID
    public UserResponseDTO buscarPorId(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        return new UserResponseDTO(user);
    }

    // 🔍 Buscar usuário por EMAIL
    public UserResponseDTO buscarPorEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Email não encontrado"));
        return new UserResponseDTO(user);
    }

    // ✏️ Atualizar usuário
    public UserResponseDTO atualizarUsuario(Long id, UserRequestDTO dto) {
        User userExistente = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        userExistente.setNome(dto.getNome());
        userExistente.setCpf(dto.getCpf());
        userExistente.setEmail(dto.getEmail());
        userExistente.setRole(dto.getRole());

        // Só atualiza a senha se vier preenchida
        if (dto.getSenha() != null && !dto.getSenha().isBlank()) {
            userExistente.setSenha(passwordEncoder.encode(dto.getSenha()));
        }

        return new UserResponseDTO(userRepository.save(userExistente));
    }

    // ❌ Deletar usuário
    public void deletarUsuario(Long id) {
        userRepository.deleteById(id);
    }

    // 👤 Buscar perfil do usuário logado
    public UserResponseDTO buscarMeuPerfil(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado com email: " + email));
        return new UserResponseDTO(user);
    }
}