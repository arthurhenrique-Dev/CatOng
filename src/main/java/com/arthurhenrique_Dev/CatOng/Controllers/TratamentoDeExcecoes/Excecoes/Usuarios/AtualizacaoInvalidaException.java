package com.arthurhenrique_Dev.CatOng.Controllers.TratamentoDeExcecoes.Excecoes.Usuarios;

public class AtualizacaoInvalidaException extends RuntimeException {

    public AtualizacaoInvalidaException() {
        super("Insira uma atualização válida");
    }

    public AtualizacaoInvalidaException(String message) {
        super(message);
    }
}
