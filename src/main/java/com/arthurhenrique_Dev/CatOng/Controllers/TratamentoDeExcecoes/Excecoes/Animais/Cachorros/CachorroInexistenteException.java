package com.arthurhenrique_Dev.CatOng.Controllers.TratamentoDeExcecoes.Excecoes.Animais.Cachorros;

public class CachorroInexistenteException extends RuntimeException {

    public CachorroInexistenteException() {
        super("Cachorro não encontrado");
    }

    public CachorroInexistenteException(String message) {
        super(message);
    }
}
