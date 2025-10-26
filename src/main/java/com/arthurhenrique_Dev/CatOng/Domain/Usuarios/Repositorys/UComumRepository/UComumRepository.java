package com.arthurhenrique_Dev.CatOng.Domain.Usuarios.Repositorys.UComumRepository;

import com.arthurhenrique_Dev.CatOng.Application.DTOs.Usuarios.Atualizacao.DTOAtualizacaoUComum;
import com.arthurhenrique_Dev.CatOng.Application.DTOs.Usuarios.Cadastro.DTORegistroUComum;
import com.arthurhenrique_Dev.CatOng.Application.DTOs.Usuarios.Retorno.DTORetornoUComum;

import java.util.List;
import java.util.Optional;

public interface UComumRepository {

    void salvarUComum(DTORegistroUComum dto);

    void removerUComum(String cpf);

    void atualizarUComum(String cpf, DTOAtualizacaoUComum dto);

    Optional<DTORetornoUComum> getUComum(String cpf);

    List<DTORetornoUComum> getUComuns(Integer page, Integer size);

    List<DTORetornoUComum> getUComunsInativos(Integer page, Integer size);

    List<DTORetornoUComum> getUComumsByName(Integer page, Integer size, String nome);
}
