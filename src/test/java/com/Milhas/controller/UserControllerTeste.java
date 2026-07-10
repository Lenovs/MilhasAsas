
package com.Milhas.controller;

import com.Milhas.security.JwtFilter;
import com.Milhas.security.JwtUtil;
import com.Milhas.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Testes para UserController com filtros e permissões habilitados
 */
@WebMvcTest(controllers = UserController.class)
@AutoConfigureMockMvc(addFilters = true) // 🔑 agora os filtros de segurança estão ativos
class UserControllerTeste {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserService userService;

    @Autowired
    private ObjectMapper objectMapper;

    @TestConfiguration
    static class MockConfig {
        @Bean
        UserService userService() {
            return Mockito.mock(UserService.class);
        }

        @Bean
        JwtUtil jwtUtil() {
            return Mockito.mock(JwtUtil.class);
        }

        @Bean
        JwtFilter jwtFilter() {
            return Mockito.mock(JwtFilter.class);
        }
    }

    @Test
    @DisplayName("GET /users - deve retornar 200 para usuário autenticado com ROLE_ADMIN")
    @WithMockUser(username = "admin", roles = {"ADMIN"}) // 🔑 simula usuário autenticado
    void getUsers_withAdminRole_returnsOk() throws Exception {
        when(userService.findAll()).thenReturn(java.util.List.of("User1", "User2"));

        mockMvc.perform(get("/users")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    @DisplayName("GET /users - deve retornar 403 para usuário sem permissões")
    @WithMockUser(username = "user", roles = {"USER"}) // 🔑 simula usuário sem permissão
    void getUsers_withUserRole_returnsForbidden() throws Exception {
        mockMvc.perform(get("/users")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden());
    }

    @Test
    @DisplayName("GET /users - deve retornar 401 quando não autenticado")
    void getUsers_withoutAuth_returnsUnauthorized() throws Exception {
        mockMvc.perform(get("/users")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized());
    }
}