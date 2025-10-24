package com.arthurhenrique_Dev.CatOng.Application.DTOs.Animais;

import com.arthurhenrique_Dev.CatOng.Domain.Animal.BaseAnimal.Sexo;

import java.util.List;

public record DTOCadastroAnimal(

        String nome,
        int idade,
        String raca,
        String descricao,
        Sexo sexo,
        double peso,
        List<String>fotos
) {
}
