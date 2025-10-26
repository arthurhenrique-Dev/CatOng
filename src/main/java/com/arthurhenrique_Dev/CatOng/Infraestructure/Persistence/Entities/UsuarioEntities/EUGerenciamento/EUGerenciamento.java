package com.arthurhenrique_Dev.CatOng.Infraestructure.Persistence.Entities.UsuarioEntities.EUGerenciamento;

import com.arthurhenrique_Dev.CatOng.Domain.Usuarios.Base.Atividade;
import com.arthurhenrique_Dev.CatOng.Domain.Usuarios.Base.Permissao;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "usuario_gerenciamento")
@NoArgsConstructor
public class EUGerenciamento implements UserDetails {

    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long NR;
    @Getter
    private String nome;
    @Getter
    private String cpf;
    @Getter
    private String RG;
    @Getter
    @Setter
    @Enumerated(EnumType.STRING)
    private Atividade atividade;
    @Getter
    @Enumerated(EnumType.STRING)
    private Permissao permissao;
    @Getter
    private String email;
    private String senha;
    @Getter
    @Setter
    private String telefone;

    public EUGerenciamento(String nome, String cpf, String RG, Atividade atividade, Permissao permissao, String senha, String email, String telefone) {
        this.nome = nome;
        this.cpf = cpf;
        this.RG = RG;
        this.atividade = atividade;
        this.permissao = permissao;
        this.senha = senha;
        this.email = email;
        this.telefone = telefone;
    }

    //testes
    public EUGerenciamento(Long NR, String nome, String cpf, String RG, Permissao permissao, Atividade atividade, String senha, String email, String telefone) {
        this.NR = NR;
        this.nome = nome;
        this.cpf = cpf;
        this.RG = RG;
        this.permissao = permissao;
        this.atividade = atividade;
        this.senha = senha;
        this.email = email;
        this.telefone = telefone;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("GERENCIAMENTO"), new SimpleGrantedAuthority("COMUM"));
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return cpf;
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

