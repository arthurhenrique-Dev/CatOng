package com.arthurhenrique_Dev.CatOng.Controllers;

import com.arthurhenrique_Dev.CatOng.Domain.Animal.Cachorros.Cachorro;
import com.arthurhenrique_Dev.CatOng.Domain.Animal.Repositorys.CachorroRepo.CachorroRepo;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cachorros")
public class ControllerCachorros {

    private final CachorroRepo repository;

    public ControllerCachorros(CachorroRepo repository) {
        this.repository = repository;
    }

    @PostMapping
    public void SalvarCachorro(@RequestParam Cachorro cachorro){
        repository.salvarCachorro(cachorro);
    }
    @PostMapping
    public void AdotarCachorro(Long id){
        repository.adotarCachorro(id);
    }
    @DeleteMapping
    public void DeletarCachorro(Long id){
        repository.deletarCachorro(id);
    }
    @GetMapping
    public List<Cachorro> getCachorros(int pages, int size){
        return repository.getCachorros(pages, size);
    }
    @GetMapping("busca={nome}")
    public List<Cachorro> getCachorrosByNome(int pages, int size, @PathVariable String nome){
        return repository.getCachorrosByName(pages, size, nome);
    }
    @GetMapping("busca_Id={id)")
    public Optional<Cachorro> getCachorrosById(@PathVariable Long id){
        return repository.getCachorroById(id);
    }

}
