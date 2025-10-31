package com.arthurhenrique_Dev.CatOng.Controllers.TratamentoDeExcecoes;

public class UsuarioBanidoException extends RuntimeException {
    public UsuarioBanidoException() {
        super("Usuário banido");
    }
}
