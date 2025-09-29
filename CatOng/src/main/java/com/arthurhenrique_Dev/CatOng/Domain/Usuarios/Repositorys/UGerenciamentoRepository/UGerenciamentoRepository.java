package com.arthurhenrique_Dev.CatOng.Domain.Usuarios.Repositorys.UGerenciamentoRepository;

import com.arthurhenrique_Dev.CatOng.Domain.Animal.Cachorros.Cachorro;
import com.arthurhenrique_Dev.CatOng.Domain.Animal.Gatos.Gato;
import com.arthurhenrique_Dev.CatOng.Domain.Usuarios.UGerenciamento.UGerenciamento;

import java.util.List;
import java.util.Optional;

public interface UGerenciamentoRepository {

    void salvarUGerenciamento(UGerenciamento ugerenciamento);
    void removerUGerenciamento(Long NR);
    Optional<UGerenciamento> getUGerenciamentoByNR(Long NR);
    List<UGerenciamento> getUGerenciamentos(int page, int size);
}
