package com.arthurhenrique_Dev.CatOng.Domain.Animal.Gatos;

import com.arthurhenrique_Dev.CatOng.Domain.Animal.BaseAnimal.Animal;
import com.arthurhenrique_Dev.CatOng.Domain.Animal.BaseAnimal.Atividade;
import com.arthurhenrique_Dev.CatOng.Domain.Animal.BaseAnimal.Sexo;
import com.arthurhenrique_Dev.CatOng.Domain.Animal.BaseAnimal.TipoDeAnimal;

import java.util.List;

public class Gato extends Animal {
    public Gato(String nome, Integer idade, String raça, Atividade atividade, String descrição, Sexo sexo, Double peso, List<String> fotos) {
        super(nome, idade, raça, atividade, descrição, sexo, peso, fotos);
        this.tipoDeAnimal = TipoDeAnimal.GATO;
    }

}
