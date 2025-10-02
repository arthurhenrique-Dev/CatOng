package com.arthurhenrique_Dev.CatOng.Controllers;

import com.arthurhenrique_Dev.CatOng.Application.UseCaseAnimal.UCachorroUseCase.UCachorroUseCase;
import com.arthurhenrique_Dev.CatOng.Domain.Animal.Cachorros.Cachorro;
import com.arthurhenrique_Dev.CatOng.Domain.Animal.Repositorys.CachorroRepo.CachorroRepo;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cachorros")
public class ControllerCachorros {

    private final UCachorroUseCase useCase;

    public ControllerCachorros(UCachorroUseCase useCase) {
        this.useCase = useCase;
    }

    @PostMapping
    public void CadastrarCachorro(@RequestParam Cachorro cachorro){
        useCase.CadastrarCachorro(cachorro);
    }
    @PostMapping
    public void AdotarCachorro(Long id){
        useCase.adotarCachorro(id);
    }
    @DeleteMapping
    public void DeletarCachorro(Long id){
        useCase.deletarCachorro(id);
    }
    @GetMapping
    public List<Cachorro> getCachorros(int pages, int size){
        return useCase.getCachorros(pages, size);
    }
    @GetMapping("busca={nome}")
    public List<Cachorro> busca(int pages, int size, @PathVariable String nome){
        return useCase.getCachorrosByName(pages, size, nome);
    }
    @GetMapping("busca_Id={id)")
    public Optional<Cachorro> getCachorrosById(@PathVariable Long id){
        return useCase.getCachorroById(id);
    }

}
