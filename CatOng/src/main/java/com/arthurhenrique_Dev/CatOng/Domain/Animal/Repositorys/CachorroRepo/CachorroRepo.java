package com.arthurhenrique_Dev.CatOng.Domain.Animal.Repositorys.CachorroRepo;

import com.arthurhenrique_Dev.CatOng.Domain.Animal.Cachorros.Cachorro;
import com.arthurhenrique_Dev.CatOng.Infraestructure.Persistence.Entities.AnimalEntities.ECachorro;

import java.util.List;
import java.util.Optional;

public interface CachorroRepo {

    void salvarCachorro(Cachorro cachorro);
    void deletarCachorro(Long id);
    void adotarCachorro(Long id);
    List<Cachorro> getCachorros(int page, int size);
    List<Cachorro> getCachorrosByName(int page, int size, String nome);
    Optional<Cachorro> getCachorroById(Long id);

}
