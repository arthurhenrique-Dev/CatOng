package com.arthurhenrique_Dev.CatOng.UsoPessoal.ProcessosIniciais;

import com.arthurhenrique_Dev.CatOng.Domain.Usuarios.Base.Permissao;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "admins")
@NoArgsConstructor
@Getter
public class ADMIN implements UserDetails {
    @Id
    private Long id;
    private String nome;
    private String senha;
    @Enumerated(EnumType.STRING)
    private Permissao permissao;

    public ADMIN(Long id, String nome, String senha) {
        this.id = id;
        this.nome = nome;
        this.senha = senha;
        this.permissao = Permissao.ADMIN;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ADMIN"), new SimpleGrantedAuthority("GERENCIAMENTO"), new SimpleGrantedAuthority("COMUM"));
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return nome;
    }
}
