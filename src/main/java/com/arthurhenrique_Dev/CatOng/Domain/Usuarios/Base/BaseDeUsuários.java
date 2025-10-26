package com.arthurhenrique_Dev.CatOng.Domain.Usuarios.Base;

import com.arthurhenrique_Dev.CatOng.Domain.Usuarios.Base.ValidacaoBaixa.ValidacaoCPF;

public abstract class BaseDeUsuários {

    protected String nome;
    protected String cpf;
    protected String RG;
    protected Atividade atividade;
    protected Permissao permissao;
    protected String email;
    protected String senha;
    protected String telefone;

    ValidacaoCPF validar = new ValidacaoCPF();

    public BaseDeUsuários(String nome, String cpf, String RG, Atividade atividade, String email, String senha, String telefone) {
        if (nome.matches("^[\\p{L} ]{2,250}+$")) {
            this.nome = nome;
        }
        if (validar.ValidarCpf(cpf)) {
            this.cpf = cpf;
        }
        if (RG.matches("^[0-9]{7,9}$")) {
            this.RG = RG;
        }
        if (atividade == Atividade.ATIVO || atividade == Atividade.INATIVO)
            this.atividade = atividade;
        if (email.matches("^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@" + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$")) {
            this.email = email;
        }
        if (senha.matches("^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[^A-Za-z0-9]).{8,}$")) {
            this.senha = senha;
        }
        if (telefone.matches("^[1-9]{2}[9][0-9]{8}$")) {
            this.telefone = telefone;
        }
    }

    public String getTelefone() {
        return telefone;
    }

    public String getSenha() {
        return senha;
    }

    public String getEmail() {
        return email;
    }

    public Permissao getPermissao() {
        return permissao;
    }

    public Atividade getAtividade() {
        return atividade;
    }

    public String getRG() {
        return RG;
    }

    public String getCpf() {
        return cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setAtividade(Atividade atividade) {
        this.atividade = atividade;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
}
