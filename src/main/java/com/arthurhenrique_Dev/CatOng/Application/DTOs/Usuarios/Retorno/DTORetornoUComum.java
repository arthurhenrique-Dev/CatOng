package com.arthurhenrique_Dev.CatOng.Application.DTOs.Usuarios.Retorno;

import com.arthurhenrique_Dev.CatOng.Domain.Usuarios.Base.Atividade;
import com.arthurhenrique_Dev.CatOng.Domain.Usuarios.Base.Permissao;
import com.arthurhenrique_Dev.CatOng.Domain.Usuarios.UComum.Endereco;


public record DTORetornoUComum(

        String nome,
        String cpf,
        String rg,
        Atividade atividade,
        String email,
        String telefone,
        int idade,
        Endereco endereco
) {
}
