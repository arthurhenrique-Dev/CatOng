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
@Table(name = "cachorro")
@NoArgsConstructor
public class ECachorro {
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
    @Getter @Setter @Enumerated(EnumType.STRING)
    private Atividade atividade;
    @Getter @Setter
    private String descrição;
    @Getter @Enumerated(EnumType.STRING)
    private Sexo sexo;
    @Getter @Setter
    private double peso;
    @Getter @Setter @ElementCollection
    private List<String> fotos = new ArrayList<>();
    @Getter @Enumerated(EnumType.STRING)
    private TipoDeAnimal tipoDeAnimal;

    public ECachorro(String nome, int idade, String raca, Atividade atividade, String descrição, Sexo sexo, double peso, List<String>fotos) {
        this.nome = nome;
        this.idade = idade;
        this.raca = raca;
        this.atividade = atividade;
        this.descrição = descrição;
        this.sexo = sexo;
        this.peso = peso;
        this.fotos = fotos;
        this.tipoDeAnimal = TipoDeAnimal.CACHORRO;
    }
}
