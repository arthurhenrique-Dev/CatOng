package com.arthurhenrique_Dev.CatOng.Application.UseCaseAnimal.UCachorroUseCase;

import com.arthurhenrique_Dev.CatOng.Domain.Animal.Cachorros.Cachorro;
import com.arthurhenrique_Dev.CatOng.Domain.Animal.Repositorys.CachorroRepo.CachorroRepo;

import java.util.List;
import java.util.Optional;

public class CachorroUseCase {

    private final CachorroRepo cachorroRepo;

    public CachorroUseCase(CachorroRepo cachorroRepo) {
        this.cachorroRepo = cachorroRepo;
    }

    public void salvarCachorro(Cachorro cachorro) {
        cachorroRepo.salvarCachorro(cachorro);
    }
    public void alterarCachorro(Long id, Cachorro cachorro) {
        cachorroRepo.alterarCachorro(id, cachorro);
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
    public List<Cachorro> getCachorrosByName(int page, int size, String nome) {
        return cachorroRepo.getCachorrosByName(page, size, nome);
    }
    public Optional<Cachorro> getCachorroById(Long id) {
        return cachorroRepo.getCachorroById(id);
    }
}
