package com.arthurhenrique_Dev.CatOng.UsoPessoal.ProcessosIniciais;

import com.arthurhenrique_Dev.CatOng.Domain.Usuarios.Base.Permissao;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "admins")
@NoArgsConstructor
@Getter
public class ADMIN {
    @Id
    private Long id;
    private String nome;
    private String senha;
    private Permissao permissao;

    public ADMIN(Long id, String nome, String senha) {
        this.id = id;
        this.nome = nome;
        this.senha = senha;
        this.permissao = Permissao.ADMIN;
    }
}
