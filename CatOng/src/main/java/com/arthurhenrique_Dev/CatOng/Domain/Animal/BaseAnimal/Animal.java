package com.arthurhenrique_Dev.CatOng.Domain.Animal.BaseAnimal;


import java.util.ArrayList;
import java.util.List;

public abstract class Animal {

    protected String nome;
    protected int idade;
    protected String raca;
    protected Atividade atividade;
    protected String descricao;
    protected Sexo sexo;
    protected double peso;
    protected List<String> fotos = new ArrayList<>();
    protected TipoDeAnimal tipoDeAnimal;

    public Animal(String nome, int idade, String raca, Atividade atividade, String descricao, Sexo sexo, double peso, List<String> fotos) {
        if (!nome.matches("^[\\p{L} ]{2,100}+$")){
            throw new IllegalArgumentException("Apenas caracteres, acentos e espaços");
        }
        this.nome = nome;
        if (idade < 0 || idade > 30){
            throw new IllegalArgumentException("idade inválida");
        }
        this.idade = idade;
        //if (!raca.matches("^[\\p{L}\\p{P}\\s]{1,50}$")){
        //    throw new IllegalArgumentException("Digito inválido");
        //}
        this.raca = raca;
        if (atividade != Atividade.ADOTADO && atividade != Atividade.ATIVO && atividade != Atividade.INATIVO){
            throw new IllegalArgumentException("atividade inválida");
        }
        this.atividade = atividade;
        this.descricao = descricao;
        if (sexo == null){
            throw new IllegalArgumentException("Informe o sexo");
        }
        this.sexo = sexo;
        if (peso < 0 || Double.isNaN(peso)){
            throw new IllegalArgumentException("Peso inválido");
        }
        this.peso = peso;
        if (fotos == null){
            throw new IllegalArgumentException("Informe a foto");
        }
        this.fotos = fotos;
    }

    public String getNome() {
        return nome;
    }

    public int getIdade() {
        return idade;
    }

    public String getRaca() {
        return raca;
    }

    public Atividade getAtividade() {
        return atividade;
    }

    public String getDescricao() {
        return descricao;
    }

    public Sexo getSexo() {
        return sexo;
    }

    public double getPeso() {
        return peso;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public void setAtividade(Atividade atividade) {
        this.atividade = atividade;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public List<String> getFotos() {
        return fotos;
    }

    public void setFotos(List<String> fotos) {
        this.fotos = fotos;
    }

    public TipoDeAnimal getTipoDeAnimal() {
        return tipoDeAnimal;
    }
}
