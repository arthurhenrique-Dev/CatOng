package com.arthurhenrique_Dev.CatOng.Domain.Usuarios.Repositorys.UGerenciamentoRepository;

import com.arthurhenrique_Dev.CatOng.Application.DTOs.Usuarios.Atualizacao.DTOAtualizacaoUGerenciamento;
import com.arthurhenrique_Dev.CatOng.Application.DTOs.Usuarios.Cadastro.DTORegistroUGerenciamento;
import com.arthurhenrique_Dev.CatOng.Application.DTOs.Usuarios.Retorno.DTORetornoUGerenciamento;
import com.arthurhenrique_Dev.CatOng.Domain.Usuarios.UGerenciamento.UGerenciamento;

import java.util.List;
import java.util.Optional;

public interface UGerenciamentoRepository {

    void salvarUGerenciamento(DTORegistroUGerenciamento ugerenciamento);
    void removerUGerenciamento(Long NR);
    void atualizarUGerenciamento(Long NR, DTOAtualizacaoUGerenciamento dto);
    Optional<DTORetornoUGerenciamento> getUGerenciamentoByNR(Long NR);
    List<DTORetornoUGerenciamento> getUGerenciamentos(Integer page, Integer size);
    List<DTORetornoUGerenciamento> getUGerenciamentosInativos(Integer page, Integer size);
    Optional<DTORetornoUGerenciamento> getUGerenciamento(String cpf);
}
