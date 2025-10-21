package com.arthurhenrique_Dev.CatOng.Domain.Animal.Cachorros;

import com.arthurhenrique_Dev.CatOng.Domain.Animal.BaseAnimal.Animal;
import com.arthurhenrique_Dev.CatOng.Domain.Animal.BaseAnimal.Atividade;
import com.arthurhenrique_Dev.CatOng.Domain.Animal.BaseAnimal.Sexo;
import com.arthurhenrique_Dev.CatOng.Domain.Animal.BaseAnimal.TipoDeAnimal;

import java.util.List;

public class Cachorro extends Animal {
    public Cachorro(String nome, int idade, String raça, Atividade atividade, String descrição, Sexo sexo, double peso, List<String> fotos) {
        super(nome, idade, raça, atividade, descrição, sexo, peso, fotos);
        this.tipoDeAnimal = TipoDeAnimal.CACHORRO;
    }

}
