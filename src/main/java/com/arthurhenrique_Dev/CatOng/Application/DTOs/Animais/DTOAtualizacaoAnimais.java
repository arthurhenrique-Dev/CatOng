package com.arthurhenrique_Dev.CatOng.Application.DTOs.Animais;

import java.util.List;

public record DTOAtualizacaoAnimais(

        Integer idade,
        String descricao,
        double peso,
        List<String> fotos

) {
}
