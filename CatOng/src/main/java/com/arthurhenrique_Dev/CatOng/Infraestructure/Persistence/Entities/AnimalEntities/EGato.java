package com.arthurhenrique_Dev.CatOng.Infraestructure.Persistence.Entities.AnimalEntities;

import com.arthurhenrique_Dev.CatOng.Domain.Animal.BaseAnimal.Atividade;
import com.arthurhenrique_Dev.CatOng.Domain.Animal.BaseAnimal.Sexo;
import com.arthurhenrique_Dev.CatOng.Domain.Animal.BaseAnimal.TipoDeAnimal;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "gato")
@NoArgsConstructor
public class EGato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;
    @Getter
    private String nome;
    @Getter @Setter
    private int idade;
    @Getter
    private String raca;
    @Getter @Setter
    private Atividade atividade;
    @Getter @Setter
    private String descrição;
    @Getter
    private Sexo sexo;
    @Getter  @Setter
    private double peso;
    @Getter @Setter @ElementCollection
    private List<String> fotos;
    @Getter
    private TipoDeAnimal tipoDeAnimal;

    public EGato(String nome, int idade, String raca, Atividade atividade, String descrição, Sexo sexo, double peso, TipoDeAnimal tipoDeAnimal, List<String> fotos) {
        this.nome = nome;
        this.idade = idade;
        this.raca = raca;
        this.atividade = atividade;
        this.descrição = descrição;
        this.sexo = sexo;
        this.peso = peso;
        this.tipoDeAnimal = tipoDeAnimal;
        this.fotos = fotos;
    }
}
