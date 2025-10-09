package com.arthurhenrique_Dev.CatOng.Infraestructure.Persistence.Entities.UsuarioEntities.EUComum;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor
public class PersistenceEndereco {
    @Getter
    private String cep;
    @Getter
    private String logradouro;
    @Getter
    private String complemento;
    @Getter
    private String bairro;
    @Getter
    private String cidade;
    @Getter
    private String numero;

    public PersistenceEndereco(String cep, String logradouro, String complemento, String bairro, String cidade, String numero) {
        this.cep = cep;
        this.logradouro = logradouro;
        this.complemento = complemento;
        this.bairro = bairro;
        this.cidade = cidade;
        this.numero = numero;
    }
}
