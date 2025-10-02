
package com.Milhas.controller;

import com.Milhas.model.User;
import com.Milhas.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    // 🔁 POST: realiza a criação de um novo usuario
    @PostMapping
   public String createUser(
           @RequestParam long id,
            @RequestParam String nome,
            @RequestParam String cpf,
            @RequestParam String email,
            @RequestParam String senha
            ){
        userService.createUser(id, nome,cpf,email,senha);
        return "Usuario criado com sucesso!";

    }

    // 📥 GET: lista todas as Usuarios
    @GetMapping
    public List<User> listarUser() {return userService.listarUser();}
}

