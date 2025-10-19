package com.arthurhenrique_Dev.CatOng.Application.DTOs.Usuarios.Retorno;

import com.arthurhenrique_Dev.CatOng.Domain.Usuarios.Base.Atividade;
import com.arthurhenrique_Dev.CatOng.Domain.Usuarios.Base.Permissao;

public record DTORetornoUGerenciamento(

        Long nr,
        String nome,
        String cpf,
        String rg,
        Atividade atividade,
        Permissao permissao,
        String email,
        String telefone
) {
}
