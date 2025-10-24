package com.arthurhenrique_Dev.CatOng.DadosParaTeste.DadosAnimal;

import com.arthurhenrique_Dev.CatOng.Application.DTOs.Animais.DTOAtualizacaoAnimais;
import com.arthurhenrique_Dev.CatOng.Application.DTOs.Animais.DTOCadastroAnimal;
import com.arthurhenrique_Dev.CatOng.Domain.Animal.BaseAnimal.Animal;
import com.arthurhenrique_Dev.CatOng.Domain.Animal.BaseAnimal.Atividade;
import com.arthurhenrique_Dev.CatOng.Domain.Animal.BaseAnimal.Sexo;
import com.arthurhenrique_Dev.CatOng.Domain.Animal.Cachorros.Cachorro;
import com.arthurhenrique_Dev.CatOng.Domain.Animal.Gatos.Gato;
import com.arthurhenrique_Dev.CatOng.Infraestructure.Persistence.Entities.AnimalEntities.EGato;

import java.util.ArrayList;
import java.util.List;

public class DadosParaTesteValidoAnimal {

    public Gato gatoValido(){

        List<String> fotos = new ArrayList<>();
        fotos.add("(link de foto 1)");
        fotos.add("(link de foto 2)");

        Gato gato = new Gato(

                "Destruidora de cidades",
                1,
                "Siamês",
                Atividade.ATIVO,
                "Agitada, carinhosa e muito brincalhona, ama carinho",
                Sexo.FEMEA,
                1.3,
                fotos

        );
        return gato;
    }
    public DTOAtualizacaoAnimais atualizacaoAnimaislValida(){

        List<String> fotos = new ArrayList<>();
        fotos.add("(link de foto nova 1)");

        DTOAtualizacaoAnimais dto = new DTOAtualizacaoAnimais(

                2,
                "",
                2.0,
                fotos
        );
        return dto;
    }
    public Cachorro cachorroValido(){
        List<String> fotos = new ArrayList<>();
        fotos.add("(link de foto 1)");
        fotos.add("(link de foto 2)");

        Cachorro cachorro = new Cachorro(

                "Princesa",
                4,
                "pitbull",
                Atividade.ATIVO,
                "Pitbull muito carinhosinha e adestrada",
                Sexo.FEMEA,
                20,
                fotos
        );
        return cachorro;
    }
    public DTOCadastroAnimal cadastroAnimalValido(){
        List<String> fotos = new ArrayList<>();
        fotos.add("(link de foto 1)");

        DTOCadastroAnimal dto = new DTOCadastroAnimal(
                "Assassino imparavel",
                1,
                "Frajola",
                "Animado",
                Sexo.MACHO,
                1.1,
                fotos
        );
        return dto;
    }
}
