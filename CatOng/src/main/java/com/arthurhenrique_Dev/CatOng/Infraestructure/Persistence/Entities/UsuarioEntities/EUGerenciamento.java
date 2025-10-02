package com.arthurhenrique_Dev.CatOng.Infraestructure.Persistence.Entities.UsuarioEntities;

import com.arthurhenrique_Dev.CatOng.Domain.Usuarios.Base.Atividade;
import com.arthurhenrique_Dev.CatOng.Domain.Usuarios.Base.Permissao;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class EUGerenciamento implements UserDetails {

    @Id
    @Getter
    private Long NR;
    @Getter
    private String nome;
    @Getter
    private String cpf;
    @Getter
    private String RG;
    @Getter @Setter
    private Atividade atividade;
    @Getter @Setter
    private Permissao permissao;
    @Getter
    private String email;
    @Getter
    private String senha;
    @Getter @Setter
    private String telefone;
    @GeneratedValue(strategy = GenerationType.UUID)
    private Long addNumber;

    public EUGerenciamento(String nome, String cpf, String RG, Atividade atividade, Permissao permissao, String senha, String email, String telefone) {
        this.nome = nome;
        this.cpf = cpf;
        this.RG = RG;
        this.atividade = atividade;
        this.permissao = permissao;
        this.senha = senha;
        this.email = email;
        this.telefone = telefone;
        this.NR = Long.parseLong("300" + (((Integer.parseInt(this.RG.substring(this.RG.length() - 3)) * 9) /4) + 37)) + this.addNumber;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("GERENCIAMENTO"), new SimpleGrantedAuthority("COMUM"));
    }

    @Override
    public String getPassword() {
        return "";
    }

    @Override
    public String getUsername() {
        return "";
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }
}

