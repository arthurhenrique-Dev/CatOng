package com.arthurhenrique_Dev.CatOng.Domain.Usuarios.Repositorys.UComumRepository;

import com.arthurhenrique_Dev.CatOng.Application.DTOs.Usuarios.Atualizacao.DTOAtualizacaoUComum;
import com.arthurhenrique_Dev.CatOng.Application.DTOs.Usuarios.Cadastro.DTORegistroUComum;
import com.arthurhenrique_Dev.CatOng.Domain.Usuarios.UComum.UComum;

import java.util.List;
import java.util.Optional;

public interface UComumRepository {

    void salvarUComum(DTORegistroUComum dto);
    void removerUComum(String cpf);
    void atualizarUComum(String cpf, DTOAtualizacaoUComum dto);
    Optional<UComum> getUComum(String cpf);
    List<UComum> getUComuns(Integer page, Integer size);
    List<UComum> getUComumsByName(Integer page, Integer size, String nome);
}
