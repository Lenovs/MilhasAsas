package com.Milhas.controller;

import com.Milhas.dto.OfertaRequestDTO;
import com.Milhas.dto.OfertaResponseDTO;
import com.Milhas.model.OfertaTipo;
import com.Milhas.security.JwtFilter;
import com.Milhas.security.JwtUtil;
import com.Milhas.service.OfertaService;
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
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = OfertaAdminController.class)
@AutoConfigureMockMvc(addFilters = false)
class OfertaAdminControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private OfertaService ofertaService;

    @Autowired
    private ObjectMapper objectMapper;

    // 🔑 Nova abordagem: configuração de mocks
    @TestConfiguration
    static class MockConfig {
        @Bean
        OfertaService ofertaService() {
            return Mockito.mock(OfertaService.class);
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

    private OfertaResponseDTO sampleDto() {
        return new OfertaResponseDTO(
                1L,
                OfertaTipo.PROMO,
                "Pacote Família",
                1000.0,
                5000,
                LocalDate.now().plusMonths(3),
                true
        );
    }

    @Test
    @DisplayName("GET /admin/ofertas - retorna lista de ofertas")
    void getAllOffers_returnsOk() throws Exception {
        OfertaResponseDTO dto = sampleDto();
        when(ofertaService.findAll()).thenReturn(List.of(dto));

        mockMvc.perform(get("/admin/ofertas")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].descricao").value("Pacote Família"));
    }

    @Test
    @DisplayName("POST /admin/ofertas - cria oferta e retorna 201")
    void createOffer_returnsCreated() throws Exception {
        OfertaRequestDTO request = new OfertaRequestDTO(
                OfertaTipo.PROMO,
                "Pacote Família",
                1000.0,
                5000,
                LocalDate.now().plusMonths(3),
                true
        );

        OfertaResponseDTO saved = sampleDto();
        when(ofertaService.createRoot(any(OfertaRequestDTO.class))).thenReturn(saved);

        mockMvc.perform(post("/admin/ofertas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", "/admin/ofertas/1"))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.descricao").value("Pacote Família"));
    }

    @Test
    @DisplayName("DELETE /admin/ofertas/{id} - remove oferta e retorna 204")
    void deleteOffer_returnsNoContent() throws Exception {
        doNothing().when(ofertaService).deleteRoot(1L);

        mockMvc.perform(delete("/admin/ofertas/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    @DisplayName("GET /admin/ofertas/{id} - quando não existe retorna 404")
    void getById_notFound_returns404() throws Exception {
        when(ofertaService.findById(999L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/admin/ofertas/999"))
                .andExpect(status().isNotFound());
    }
}