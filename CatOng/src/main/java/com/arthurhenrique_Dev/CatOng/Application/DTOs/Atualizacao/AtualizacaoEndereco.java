package com.arthurhenrique_Dev.CatOng.Application.DTOs.Atualizacao;

public record AtualizacaoEndereco(

        String cep,
        String logradouro,
        String complemento,
        String bairro,
        String cidade,
        String numero
) {
}
