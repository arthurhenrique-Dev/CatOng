package com.arthurhenrique_Dev.CatOng.Domain.Usuarios.UComum;

import com.arthurhenrique_Dev.CatOng.Domain.Usuarios.Base.Atividade;
import com.arthurhenrique_Dev.CatOng.Domain.Usuarios.Base.BaseDeUsuários;
import com.arthurhenrique_Dev.CatOng.Domain.Usuarios.Base.Permissao;

import java.time.LocalDate;

public class UComum extends BaseDeUsuários {

    private Endereco endereco;
    private LocalDate dataNascimento;

    public UComum(String nome, String cpf, String RG, Atividade atividade, String email, String senha, String telefone, Endereco endereco, LocalDate dataNascimento) {
        super(nome, cpf, RG, atividade, email, senha, telefone);
        this.endereco = endereco;
        if (dataNascimento.isBefore(LocalDate.now().minusYears(18)) && dataNascimento.isAfter(dataNascimento.minusYears(100))) {
            this.dataNascimento = dataNascimento;
        }
        this.atividade = atividade;
        this.permissao = Permissao.COMUM;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public Endereco getEndereco() {
        return endereco;
    }

}
