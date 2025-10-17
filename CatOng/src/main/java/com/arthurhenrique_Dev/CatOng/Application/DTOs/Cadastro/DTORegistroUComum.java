package com.arthurhenrique_Dev.CatOng.Application.DTOs.Cadastro;

import com.arthurhenrique_Dev.CatOng.Domain.Usuarios.UComum.Endereco;

import java.time.LocalDate;

public record DTORegistroUComum(

        String nome,
        String cpf,
        String rg,
        String senha,
        String email,
        String telefone,
        LocalDate dataDeNascimento,
        Endereco endereco

) {
}
