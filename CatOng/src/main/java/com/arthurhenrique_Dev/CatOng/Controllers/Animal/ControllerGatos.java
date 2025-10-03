package com.arthurhenrique_Dev.CatOng.Controllers.Animal;

import com.arthurhenrique_Dev.CatOng.Application.UseCaseAnimal.UGatoUseCase.UGatoUseCase;
import com.arthurhenrique_Dev.CatOng.Domain.Animal.Gatos.Gato;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/gatos")
public class ControllerGatos {

    private final UGatoUseCase useCase;

    public ControllerGatos(UGatoUseCase useCase) {
        this.useCase = useCase;
    }

    @GetMapping
    public List<Gato> getGato(@RequestParam int page, @RequestParam int size){
        return useCase.getGato(page, size);
    }
    @GetMapping("/busca={nome}")
    public List<Gato> busca(@RequestParam int page, int size, @PathVariable String nome){
        return useCase.getGatoByName(page, size, nome);
    }
    @PostMapping
    public void CadastrarGato(@RequestBody Gato gato){
        useCase.salvarGato(gato);
    }
    @PostMapping("/adocao")
    public void adotarGato(@RequestBody Long id){
        useCase.adotarGato(id);
    }
    @DeleteMapping("/gerenciamento/gatos")
    public void deleteGato(@RequestParam Long id){
        useCase.deletarGato(id);
    }
}
