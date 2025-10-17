package com.arthurhenrique_Dev.CatOng.Domain.Animal.Repositorys.GatoRepo;

import com.arthurhenrique_Dev.CatOng.Application.DTOs.Animais.DTOAtualizacaoAnimais;
import com.arthurhenrique_Dev.CatOng.Domain.Animal.Gatos.Gato;

import java.util.List;
import java.util.Optional;

public interface GatoRepo {

    void salvarGato(Gato gato);
    void deletarGato(Long id);
    void adotarGato(Long id);
    void alterarGato(Long id, DTOAtualizacaoAnimais dto);
    List<Gato> getGato(int page, int size);
    List<Gato> getGatoByName(int page, int size, String nome);
    Optional<Gato> getGatoById(Long id);
}
