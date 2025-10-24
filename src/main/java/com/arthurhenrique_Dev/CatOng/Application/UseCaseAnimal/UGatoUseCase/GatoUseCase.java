package com.arthurhenrique_Dev.CatOng.Application.UseCaseAnimal.UGatoUseCase;

import com.arthurhenrique_Dev.CatOng.Application.DTOs.Animais.DTOAtualizacaoAnimais;
import com.arthurhenrique_Dev.CatOng.Domain.Animal.Gatos.Gato;
import com.arthurhenrique_Dev.CatOng.Domain.Animal.Repositorys.GatoRepo.GatoRepo;

import java.util.List;
import java.util.Optional;

public class GatoUseCase {

    private final GatoRepo gatoRepo;

    public GatoUseCase(GatoRepo gatoRepo) {
        this.gatoRepo = gatoRepo;
    }

    public void salvarGato(Gato gato) {
        gatoRepo.salvarGato(gato);
    }
    public void deletarGato(Long id) {
        gatoRepo.deletarGato(id);
    }
    public void adotarGato(Long id) {
        gatoRepo.adotarGato(id);
    }
    public void alterarGato(Long id, DTOAtualizacaoAnimais dto){
        gatoRepo.alterarGato(id, dto);
    }
    public List<Gato> getGato(int page, int size) {
        return gatoRepo.getGato(page, size);
    }
    public List<Gato> getGatoByName(int page, int size, String nome) {
        return gatoRepo.getGatoByName(page, size, nome);
    }
    public Optional<Gato> getGatoById(Long id) {
        return gatoRepo.getGatoById(id);
    }
}
