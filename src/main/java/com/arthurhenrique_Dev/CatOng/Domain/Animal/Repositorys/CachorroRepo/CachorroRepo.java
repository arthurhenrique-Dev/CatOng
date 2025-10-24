package com.arthurhenrique_Dev.CatOng.Domain.Animal.Repositorys.CachorroRepo;

import com.arthurhenrique_Dev.CatOng.Application.DTOs.Animais.DTOAtualizacaoAnimais;
import com.arthurhenrique_Dev.CatOng.Application.DTOs.Animais.DTOCadastroAnimal;
import com.arthurhenrique_Dev.CatOng.Domain.Animal.Cachorros.Cachorro;
import com.arthurhenrique_Dev.CatOng.Infraestructure.Persistence.Entities.AnimalEntities.ECachorro;

import java.util.List;
import java.util.Optional;

public interface CachorroRepo {

    void salvarCachorro(DTOCadastroAnimal dto);
    void deletarCachorro(Long id);
    void adotarCachorro(Long id);
    void alterarCachorro(Long id, DTOAtualizacaoAnimais dto);
    List<Cachorro> getCachorros(Integer page, Integer size);
    List<Cachorro> getCachorrosInativos(Integer page, Integer size);
    List<Cachorro> getCachorrosAdotados(Integer page, Integer size);
    List<Cachorro> getCachorrosByName(Integer page, Integer size, String nome);
    Optional<Cachorro> getCachorroById(Long id);

}
