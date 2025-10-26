package com.arthurhenrique_Dev.CatOng.Controllers.TratamentoDeExcecoes.Excecoes.Usuarios;

public class UsuarioExistenteException extends RuntimeException {

    public UsuarioExistenteException() {
        super("Já existe um usuário com esse CPF");
    }

}
