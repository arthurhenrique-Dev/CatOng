package com.arthurhenrique_Dev.CatOng.Application.DTOs.Usuarios.Atualizacao;

import com.arthurhenrique_Dev.CatOng.Domain.Usuarios.UComum.Endereco;

public record DTOAtualizacaoUComum(

        Endereco endereco,
        String telefone
) {
}
