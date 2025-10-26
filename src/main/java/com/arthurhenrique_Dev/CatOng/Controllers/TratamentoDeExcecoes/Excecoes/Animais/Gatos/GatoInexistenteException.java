package com.arthurhenrique_Dev.CatOng.Controllers.TratamentoDeExcecoes.Excecoes.Animais.Gatos;

public class GatoInexistenteException extends RuntimeException {

    public GatoInexistenteException() { super("Gato não encontrado"); }

    public GatoInexistenteException(String message) { super(message); }
}
