package com.arthurhenrique_Dev.CatOng.Application.UseCaseUsuarios.UGerenciamentoUseCase;

import com.arthurhenrique_Dev.CatOng.Application.DTOs.Usuarios.Atualizacao.DTOAtualizacaoUGerenciamento;
import com.arthurhenrique_Dev.CatOng.Application.DTOs.Usuarios.Cadastro.DTORegistroUGerenciamento;
import com.arthurhenrique_Dev.CatOng.Application.DTOs.Usuarios.Retorno.DTORetornoUComum;
import com.arthurhenrique_Dev.CatOng.Application.DTOs.Usuarios.Retorno.DTORetornoUGerenciamento;
import com.arthurhenrique_Dev.CatOng.Domain.Usuarios.Repositorys.UGerenciamentoRepository.UGerenciamentoRepository;
import com.arthurhenrique_Dev.CatOng.Domain.Usuarios.UGerenciamento.UGerenciamento;

import java.util.List;
import java.util.Optional;

public class UGerenciamentoUseCase {

    private final UGerenciamentoRepository uGRepo;


    public UGerenciamentoUseCase(UGerenciamentoRepository uGRepo) {
        this.uGRepo = uGRepo;
    }

    public void salvarUGerenciamento(DTORegistroUGerenciamento registroUGerenciamento){
        uGRepo.salvarUGerenciamento(registroUGerenciamento);
    }
    public void removerUGerenciamento(Long NR){
        uGRepo.removerUGerenciamento(NR);
    }
    public void atualizarUGerenciamento(Long NR, DTOAtualizacaoUGerenciamento dto){
        uGRepo.atualizarUGerenciamento(NR, dto);
    }
    public Optional<DTORetornoUGerenciamento> getUGerenciamento(String cpf){return uGRepo.getUGerenciamento(cpf);}
    public Optional<DTORetornoUGerenciamento> getUGerenciamentoByNR(Long nr){
        return uGRepo.getUGerenciamentoByNR(nr);
    }
    public List<DTORetornoUGerenciamento> getUGerenciamentos(int page, int size){
        return uGRepo.getUGerenciamentos(page, size);
    }
    public List<DTORetornoUGerenciamento> getUGerenciamentosInativos(int page, int size){return uGRepo.getUGerenciamentosInativos(page, size);}
}
