package com.arthurhenrique_Dev.CatOng.Domain.Usuarios.Base;

public enum Permissao {
    COMUM("comum"),
    GERENCIAMENTO("gerenciamento");

    private String permissão;

    Permissao(String permissão) {
        this.permissão = permissão;
    }


}
