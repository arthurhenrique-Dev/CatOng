package com.arthurhenrique_Dev.CatOng.Domain.Usuarios.UGerenciamento;

import com.arthurhenrique_Dev.CatOng.Domain.Usuarios.Base.Atividade;
import com.arthurhenrique_Dev.CatOng.Domain.Usuarios.Base.BaseDeUsuários;
import com.arthurhenrique_Dev.CatOng.Domain.Usuarios.Base.Permissao;

public class UGerenciamento extends BaseDeUsuários {


    public UGerenciamento(String nome, String cpf, String RG, Atividade atividade, String email, String senha, String telefone) {
        super(nome, cpf, RG, atividade, email, senha, telefone);
        this.permissao = Permissao.GERENCIAMENTO;
        this.atividade = Atividade.ATIVO;
    }
}
