package com.arthurhenrique_Dev.CatOng.Controllers;

import com.arthurhenrique_Dev.CatOng.Domain.Animal.Gatos.Gato;
import com.arthurhenrique_Dev.CatOng.Domain.Animal.Repositorys.GatoRepo.GatoRepo;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/gatos")
public class ControllerGatos {

    private final GatoRepo repository;

    public ControllerGatos(GatoRepo repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Gato> getGato(@RequestParam int page, @RequestParam int size){
        return repository.getGato(page, size);
    }

    @GetMapping("/busca={nome}")
    public List<Gato> busca(@RequestParam int page, int size, @PathVariable String nome){
        return repository.getGatoByName(page, size, nome);
    }

    @PostMapping
    public void addGato(@RequestBody Gato gato){
        repository.salvarGato(gato);
    }
    @PostMapping("/adocao")
    public void adotarGato(@RequestBody Long id){
        repository.adotarGato(id);
    }
    @DeleteMapping("/gerenciamento/gatos")
    public void deleteGato(@RequestParam Long id){
        repository.deletarGato(id);
    }
}
