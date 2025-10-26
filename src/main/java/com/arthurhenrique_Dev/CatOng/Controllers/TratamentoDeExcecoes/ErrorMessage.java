package com.arthurhenrique_Dev.CatOng.Controllers.TratamentoDeExcecoes;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ErrorMessage {
    private LocalDateTime momento_do_erro;
    private String mensagem;

    public ErrorMessage(String mensagem) {
        this.momento_do_erro = LocalDateTime.now();
        this.mensagem = mensagem;
    }
}
