package com.arthurhenrique_Dev.CatOng.Domain.Usuarios.Repositorys.UGerenciamentoRepository;

import com.arthurhenrique_Dev.CatOng.Application.DTOs.Usuarios.Atualizacao.DTOAtualizacaoUGerenciamento;
import com.arthurhenrique_Dev.CatOng.Application.DTOs.Usuarios.Cadastro.DTORegistroUGerenciamento;
import com.arthurhenrique_Dev.CatOng.Domain.Usuarios.UGerenciamento.UGerenciamento;

import java.util.List;
import java.util.Optional;

public interface UGerenciamentoRepository {

    void salvarUGerenciamento(DTORegistroUGerenciamento ugerenciamento);
    void removerUGerenciamento(Long NR);
    void atualizarUGerenciamento(Long NR, DTOAtualizacaoUGerenciamento dto);
    Optional<UGerenciamento> getUGerenciamentoByNR(Long NR);
    List<UGerenciamento> getUGerenciamentos(int page, int size);
    Optional<UGerenciamento> getUGerenciamento(String cpf);
}
