package com.arthurhenrique_Dev.CatOng.DadosParaTeste.DadosPessoa;

import com.arthurhenrique_Dev.CatOng.Application.DTOs.Usuarios.Atualizacao.DTOAtualizacaoUComum;
import com.arthurhenrique_Dev.CatOng.Application.DTOs.Usuarios.Atualizacao.DTOAtualizacaoUGerenciamento;
import com.arthurhenrique_Dev.CatOng.Application.DTOs.Usuarios.Cadastro.DTORegistroUComum;
import com.arthurhenrique_Dev.CatOng.Application.DTOs.Usuarios.Cadastro.DTORegistroUGerenciamento;
import com.arthurhenrique_Dev.CatOng.Application.DTOs.Usuarios.Retorno.DTORetornoUComum;
import com.arthurhenrique_Dev.CatOng.Application.DTOs.Usuarios.Retorno.DTORetornoUGerenciamento;
import com.arthurhenrique_Dev.CatOng.Domain.Usuarios.Base.Atividade;
import com.arthurhenrique_Dev.CatOng.Domain.Usuarios.Base.Permissao;
import com.arthurhenrique_Dev.CatOng.Domain.Usuarios.UComum.Endereco;

import java.time.LocalDate;

public class DadosParaTesteValido {

    //TODOS OS CPFS, CEPS, E RGS GERADOS NESSA CLASSE FORAM GERADOS PELO SITE 4DEVS, NÃO SE TRATAM DE DADOS PARTICULARES.

    public Endereco primeiroEndereco() {
        return new Endereco(

                "08345355", //gereado pelo 4devs
                "Rua Exemplo de Souza",
                "fundos",
                "Sé",
                "São Paulo",
                12
        );
    }
    public Endereco segundoEndereco(){

        return new Endereco(

                "04884380", //gerado pelo 4devs
                "Rua Nova da Silva",
                "bloco b",
                "Guaianases",
                "São Paulo",
                677
        );
    }

    public DTORegistroUComum registroValidoUComum(){

        DTORegistroUComum dto = new DTORegistroUComum(

                "Nome exemplo",
                "81101120053",//gerado pelo 4devs,
                "187574777",
                "Aa@12345",
                "emailexemplo@gmail.com",
                "11999999999",
                LocalDate.of(2000,01,01),
                primeiroEndereco()
        );
        return dto;
    }
    public DTOAtualizacaoUComum atualizacaoValidoUComum(){

        DTOAtualizacaoUComum dto = new DTOAtualizacaoUComum(

                segundoEndereco(),
                "11988888888"
        );
        return dto;
    }
    public DTORetornoUComum retornoValidoUComum(){

        DTORetornoUComum dto = new DTORetornoUComum(

                "Exemplo",
                "23175024009",
                "454967603",
                Atividade.ATIVO,
                "exemplo@gmail.com",
                "11999998888",
                15,
                primeiroEndereco()
                );
        return dto;
    }
    public DTORegistroUGerenciamento registroValidoUgerenciamento(){

        DTORegistroUGerenciamento dto = new DTORegistroUGerenciamento(

                "exemplo",
                "15533565000",
                "401201673",
                "Aa@12345",
                "exemplo@gmail.com",
                "11999998888"
        );
        return dto;
    }
    public DTOAtualizacaoUGerenciamento atualizacaoValidoUgerenciamento(){

        DTOAtualizacaoUGerenciamento dto = new DTOAtualizacaoUGerenciamento(

                "11999998888"
        );
        return dto;
    }
    public DTORetornoUGerenciamento retornoValidoUgerenciamento(){

        DTORetornoUGerenciamento dto = new DTORetornoUGerenciamento(

                1L,
                "exemplo",
                "68965159024",
                "211158276",
                Atividade.ATIVO,
                Permissao.GERENCIAMENTO,
                "exemplo@gmail.com",
                "11999998888"
        );
        return dto;
    }
}
