package com.arthurhenrique_Dev.CatOng.Application.UseCaseUsuarios.UGerenciamentoUseCase;

import com.arthurhenrique_Dev.CatOng.Domain.Usuarios.Repositorys.UGerenciamentoRepository.UGerenciamentoRepository;
import com.arthurhenrique_Dev.CatOng.Domain.Usuarios.UGerenciamento.UGerenciamento;

import java.util.List;
import java.util.Optional;

public class UGerenciamentoUseCase {

    private final UGerenciamentoRepository uGRepo;


    public UGerenciamentoUseCase(UGerenciamentoRepository uGRepo) {
        this.uGRepo = uGRepo;
    }

    public void salvarUGerenciamento(UGerenciamento uGerenciamento){
        uGRepo.salvarUGerenciamento(uGerenciamento);
    }
    public void removerUGerenciamento(Long NR){
        uGRepo.removerUGerenciamento(NR);
    }
    public Optional<UGerenciamento> getUGerenciamentoByNR(Long NR){
        return uGRepo.getUGerenciamentoByNR(NR);
    }
    public List<UGerenciamento> getUGerenciamentos(int page, int size){
        return uGRepo.getUGerenciamentos(page, size);
    }
}
