package com.arthurhenrique_Dev.CatOng.Application.UseCaseAnimal.UCachorroUseCase;

import com.arthurhenrique_Dev.CatOng.Application.DTOs.Animais.DTOAtualizacaoAnimais;
import com.arthurhenrique_Dev.CatOng.Application.DTOs.Animais.DTOCadastroAnimal;
import com.arthurhenrique_Dev.CatOng.Domain.Animal.Cachorros.Cachorro;
import com.arthurhenrique_Dev.CatOng.Domain.Animal.Repositorys.CachorroRepo.CachorroRepo;

import java.util.List;
import java.util.Optional;

public class CachorroUseCase {

    private final CachorroRepo cachorroRepo;

    public CachorroUseCase(CachorroRepo cachorroRepo) {
        this.cachorroRepo = cachorroRepo;
    }

    public void salvarCachorro(DTOCadastroAnimal dto) {
        cachorroRepo.salvarCachorro(dto);
    }
    public void alterarCachorro(Long id, DTOAtualizacaoAnimais dto) {
        cachorroRepo.alterarCachorro(id, dto);
    }
    public void deletarCachorro(Long id) {
        cachorroRepo.deletarCachorro(id);
    }
    public void adotarCachorro(Long id) {
        cachorroRepo.adotarCachorro(id);
    }
    public List<Cachorro> getCachorros(int page, int size) {
        return cachorroRepo.getCachorros(page, size);
    }
    public List<Cachorro> getCachorrosInativos(int page, int size) {return cachorroRepo.getCachorrosInativos(page, size);}
    public List<Cachorro> getCachorrosAdotados(int page, int size) {return cachorroRepo.getCachorrosAdotados(page, size);}
    public List<Cachorro> getCachorrosByName(int page, int size, String nome) {
        return cachorroRepo.getCachorrosByName(page, size, nome);
    }
    public Optional<Cachorro> getCachorroById(Long id) {
        return cachorroRepo.getCachorroById(id);
    }
}
