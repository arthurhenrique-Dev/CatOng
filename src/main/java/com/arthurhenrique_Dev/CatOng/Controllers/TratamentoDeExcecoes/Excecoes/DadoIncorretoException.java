package com.arthurhenrique_Dev.CatOng.Controllers.TratamentoDeExcecoes.Excecoes;

public class DadoIncorretoException extends RuntimeException {

    public DadoIncorretoException(String message) {
        super(message);
    }

    public DadoIncorretoException() {
        super("Dado inserido incorretamente");
    }
}
