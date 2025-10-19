package com.arthurhenrique_Dev.CatOng.Infraestructure.Persistence.Entities.UsuarioEntities.EUComum;

import com.arthurhenrique_Dev.CatOng.Domain.Usuarios.Base.Atividade;
import com.arthurhenrique_Dev.CatOng.Domain.Usuarios.Base.Permissao;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "usuario_comum")
@NoArgsConstructor
public class EUComum implements UserDetails {

    @Getter
    private String nome;
    @Getter @Id
    private String cpf;
    @Getter
    private String RG;
    @Getter @Setter @Enumerated(EnumType.STRING)
    private Atividade atividade;
    @Getter @Setter @Enumerated(EnumType.STRING)
    private Permissao permissao;
    @Getter
    private String email;
    @Getter
    private String senha;
    @Getter
    private String telefone;
    @Embedded @Getter
    private PersistenceEndereco endereco;
    @Getter
    private LocalDate dataNascimento;

    public EUComum(String nome, String cpf, String RG, String email,String senha, String telefone, PersistenceEndereco endereco, LocalDate dataNascimento) {
        this.nome = nome;
        this.cpf = cpf;
        this.RG = RG;
        this.email = email;
        this.senha = senha;
        this.telefone = telefone;
        this.endereco = endereco;
        this.dataNascimento = dataNascimento;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("COMUM"));
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
