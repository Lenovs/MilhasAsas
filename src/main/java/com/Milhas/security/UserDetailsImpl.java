package com.Milhas.security;

import com.Milhas.model.User;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@AllArgsConstructor
public class UserDetailsImpl implements UserDetails {

    private final User user;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Prefixo "ROLE_" é exigido pelo Spring Security
        return List.of(new SimpleGrantedAuthority("ROLE_" + user.getRole().name()));
    }

    @Override
    public String getPassword() {
        return user.getSenha();
    }

    @Override
    public String getUsername() {
        return user.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // Pode ser ajustado para lógica de expiração real
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // Pode ser ajustado para lógica de bloqueio
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Pode ser ajustado para expiração de senha
    }

    @Override
    public boolean isEnabled() {
        return true; // Pode ser ajustado para ativação/desativação de conta
    }

    public User getUser() {
        return user;
    }
}