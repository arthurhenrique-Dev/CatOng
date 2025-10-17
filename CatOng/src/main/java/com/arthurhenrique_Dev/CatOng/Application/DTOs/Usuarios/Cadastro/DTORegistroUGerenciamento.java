package com.arthurhenrique_Dev.CatOng.Application.DTOs.Usuarios.Cadastro;

import com.arthurhenrique_Dev.CatOng.Domain.Usuarios.UComum.Endereco;

public record DTORegistroUGerenciamento(

        String nome,
        String cpf,
        String rg,
        String senha,
        String email,
        String telefone,
        Endereco endereco
) {
}
