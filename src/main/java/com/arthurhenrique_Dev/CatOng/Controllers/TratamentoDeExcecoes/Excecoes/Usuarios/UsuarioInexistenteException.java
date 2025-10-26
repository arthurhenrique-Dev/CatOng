package com.arthurhenrique_Dev.CatOng.Controllers.TratamentoDeExcecoes.Excecoes.Usuarios;

public class UsuarioInexistenteException extends RuntimeException {

    public UsuarioInexistenteException() {
        super("Usuario não encontrado");
    }

    public UsuarioInexistenteException(String message) {
        super(message);
    }
}
