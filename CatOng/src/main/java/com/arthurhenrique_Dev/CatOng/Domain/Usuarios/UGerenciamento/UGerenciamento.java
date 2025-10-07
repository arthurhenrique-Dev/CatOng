package com.arthurhenrique_Dev.CatOng.Domain.Usuarios.UGerenciamento;

import com.arthurhenrique_Dev.CatOng.Domain.Usuarios.Base.Atividade;
import com.arthurhenrique_Dev.CatOng.Domain.Usuarios.Base.BaseDeUsuários;
import com.arthurhenrique_Dev.CatOng.Domain.Usuarios.Base.Permissao;

public class UGerenciamento extends BaseDeUsuários {

    private Long NR;
    private GerarNr geradorEstruturaDer;

    public UGerenciamento(String nome, String cpf, String RG, String email, String senha, String telefone) {
        super(nome, cpf, RG, email, senha, telefone);
        this.NR = geradorEstruturaDer.NrGerado(this.cpf);
        this.permissao = Permissao.GERENCIAMENTO;
        this.atividade = Atividade.ATIVO;
    }

    public Long getNR() {
        return NR;
    }
}
