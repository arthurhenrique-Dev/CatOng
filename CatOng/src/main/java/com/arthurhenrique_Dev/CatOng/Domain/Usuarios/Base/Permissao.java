package com.arthurhenrique_Dev.CatOng.Domain.Usuarios.Base;

public enum Permissao {
    COMUM("comum"),
    GERENCIAMENTO("gerenciamento"),
    ADMIN("admin");

    private String permissão;

    Permissao(String permissão) {
        this.permissão = permissão;
    }


}
