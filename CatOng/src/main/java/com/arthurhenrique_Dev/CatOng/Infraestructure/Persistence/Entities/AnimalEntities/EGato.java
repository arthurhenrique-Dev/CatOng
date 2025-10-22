package com.arthurhenrique_Dev.CatOng.Infraestructure.Persistence.Entities.AnimalEntities;

import com.arthurhenrique_Dev.CatOng.Domain.Animal.BaseAnimal.Atividade;
import com.arthurhenrique_Dev.CatOng.Domain.Animal.BaseAnimal.Sexo;
import com.arthurhenrique_Dev.CatOng.Domain.Animal.BaseAnimal.TipoDeAnimal;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
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
    @Getter @Setter @Enumerated(EnumType.STRING)
    private Atividade atividade;
    @Getter @Setter
    private String descricao;
    @Getter @Enumerated(EnumType.STRING)
    private Sexo sexo;
    @Getter @Setter
    private double peso;
    @Getter @Setter @ElementCollection  @CollectionTable(
            name = "gato_fotos",
            joinColumns = @JoinColumn(name = "gato_id")
    )
    @Column(name = "foto")
    private List<String> fotos = new ArrayList<>();
    @Getter @Enumerated(EnumType.STRING)
    private TipoDeAnimal tipoDeAnimal;

    public EGato(String nome, int idade, String raca, Atividade atividade, String descricao, Sexo sexo, double peso, TipoDeAnimal tipoDeAnimal, @JsonProperty("fotos") List<String> fotos) {
        this.nome = nome;
        this.idade = idade;
        this.raca = raca;
        this.atividade = atividade;
        this.descricao = descricao;
        this.sexo = sexo;
        this.peso = peso;
        this.tipoDeAnimal = tipoDeAnimal;
        this.fotos = fotos;
    }
}
