package com.Milhas.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.http.HttpMethod;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    // 🔐 Encoder para senhas
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // 🔐 Configuração de segurança HTTP
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        // Endpoints públicos
                        .requestMatchers("/h2-console/**").permitAll()
                        .requestMatchers("/auth/**").permitAll() // login, registro
                        .requestMatchers(HttpMethod.POST, "/usuarios").permitAll() // registro público

                        // Endpoints de usuários → apenas ADM
                        .requestMatchers(HttpMethod.GET, "/usuarios/**").hasRole("ADM")
                        .requestMatchers(HttpMethod.PUT, "/usuarios/**").hasRole("ADM")
                        .requestMatchers(HttpMethod.DELETE, "/usuarios/**").hasRole("ADM")

                        // Endpoints de ofertas → ADM e Gerente
                        .requestMatchers("/ofertas/**").hasAnyRole("ADM", "GerenteNegocios")

                        // Endpoints de contas e milhas → apenas usuários da plataforma
                        .requestMatchers("/contas/**").hasRole("UserPlataforma")
                        .requestMatchers("/milhas/**").hasRole("UserPlataforma")

                        // Endpoints de transações → todos os usuários autenticados
                        .requestMatchers("/transacoes/**").authenticated()

                        // Qualquer outro endpoint exige autenticação
                        .anyRequest().authenticated()
                )
                .httpBasic(Customizer.withDefaults());

        // Permitir frames para o H2 console
        http.headers(headers -> headers.frameOptions(frame -> frame.sameOrigin()));

        return http.build();
    }

    // 🔐 AuthenticationManager obtido via AuthenticationConfiguration
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }
}