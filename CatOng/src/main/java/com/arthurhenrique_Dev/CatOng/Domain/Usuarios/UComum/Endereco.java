package com.arthurhenrique_Dev.CatOng.Domain.Usuarios.UComum;

public class Endereco {

    private String cep;
    private String logradouro;
    private String complemento;
    private String bairro;
    private String cidade;
    private String numero;

    public Endereco(String cep, String logradouro, String complemento, String bairro, String cidade, String numero) {
        if (!cep.matches("^[0-9]{8}$")){
            throw new IllegalArgumentException("CEP invalido");
        }
        this.cep = cep;
        this.logradouro = logradouro;
        this.complemento = complemento;
        this.bairro = bairro;
        this.cidade = cidade;
        this.numero = numero;
    }

    public String getCep() {
        return cep;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public String getComplemento() {
        return complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public String getNumero() {
        return numero;
    }
}
