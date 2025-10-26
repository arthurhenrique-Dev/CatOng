package com.arthurhenrique_Dev.CatOng.Controllers.TratamentoDeExcecoes.Excecoes.Animais;

public class AnimaisAtualizacaoInvalidaException extends RuntimeException {

    public AnimaisAtualizacaoInvalidaException() {
        super("Insira dados válidos");
    }
}
