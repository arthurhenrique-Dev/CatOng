package com.arthurhenrique_Dev.CatOng.Domain.Usuarios.Base;

public enum Permissao {
    COMUM("COMUM"),
    GERENCIAMENTO("GERENCIAMENTO"),
    ADMIN("ADMIN");

    private String permissão;

    Permissao(String permissão) {
        this.permissão = permissão;
    }


}
