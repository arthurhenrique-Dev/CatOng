package com.arthurhenrique_Dev.CatOng.Infraestructure.Persistence.Entities.UsuarioEntities;

import com.arthurhenrique_Dev.CatOng.Domain.Usuarios.Base.Atividade;
import com.arthurhenrique_Dev.CatOng.Domain.Usuarios.Base.Permissao;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "usuario_comum")
@NoArgsConstructor
public class EUComum {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
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
    @Embedded @Getter
    private PersistenceEndereco endereco;
    @Getter
    private LocalDate dataNascimento;

    public EUComum(String nome, String cpf, String RG, String email, String telefone, PersistenceEndereco endereco, LocalDate dataNascimento) {
        this.nome = nome;
        this.cpf = cpf;
        this.RG = RG;
        this.email = email;
        this.telefone = telefone;
        this.endereco = endereco;
        this.dataNascimento = dataNascimento;
    }
}
