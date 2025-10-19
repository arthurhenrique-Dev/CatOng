package com.arthurhenrique_Dev.CatOng.Domain.Usuarios.UComum;

import com.arthurhenrique_Dev.CatOng.Domain.Usuarios.Base.Atividade;
import com.arthurhenrique_Dev.CatOng.Domain.Usuarios.Base.BaseDeUsuários;
import com.arthurhenrique_Dev.CatOng.Domain.Usuarios.Base.Permissao;

import java.time.LocalDate;

public class UComum extends BaseDeUsuários {

    private Endereco endereco;
    private LocalDate dataNascimento;

    public UComum(String nome, String cpf, String RG, String email, String senha, String telefone, Endereco endereco, LocalDate dataNascimento) {
        super(nome, cpf, RG, email, senha, telefone);
        this.endereco = endereco;
        if (dataNascimento.isAfter(LocalDate.now().minusYears(18))){
            throw new IllegalArgumentException("Jovem demais");
        }
        if (dataNascimento.isBefore(dataNascimento.minusYears(100))){
            throw new IllegalArgumentException("Insira uma idade válida");
        }
        this.atividade = Atividade.ATIVO;
        this.dataNascimento = dataNascimento;
        this.permissao = Permissao.COMUM;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
}
