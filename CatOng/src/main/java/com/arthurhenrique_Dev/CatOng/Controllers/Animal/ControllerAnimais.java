package com.arthurhenrique_Dev.CatOng.Controllers.Animal;

import com.arthurhenrique_Dev.CatOng.Application.DTOs.Animais.DTOAtualizacaoAnimais;
import com.arthurhenrique_Dev.CatOng.Application.UseCaseAnimal.UCachorroUseCase.CachorroUseCase;
import com.arthurhenrique_Dev.CatOng.Application.UseCaseAnimal.UGatoUseCase.GatoUseCase;
import com.arthurhenrique_Dev.CatOng.Domain.Animal.Cachorros.Cachorro;
import com.arthurhenrique_Dev.CatOng.Domain.Animal.Gatos.Gato;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("pets")
public class ControllerAnimais {

    private final CachorroUseCase cachorroUseCase;
    private final GatoUseCase gatoUseCase;

    public ControllerAnimais(CachorroUseCase cachorroUseCase, GatoUseCase gatoUseCase) {
        this.cachorroUseCase = cachorroUseCase;
        this.gatoUseCase = gatoUseCase;
    }

    @GetMapping("/gatos")
    public List<Gato> getGatos(int pages, int size){
        return gatoUseCase.getGato(pages, size);
    }
    @GetMapping("/gatos/nome?={nome}")
    public List<Gato> getGatosByNome(int pages, int size, @RequestParam String nome){
        return gatoUseCase.getGatoByName(pages, size, nome);
    }
    @GetMapping("/gatos/id?={id}")
    public Optional<Gato> getGatosById(@RequestParam Long id){
        return gatoUseCase.getGatoById(id);
    }
    @GetMapping("/cachorros")
    public List<Cachorro> getCachorros(int pages, int size){
        return cachorroUseCase.getCachorros(pages, size);
    }
    @GetMapping("/cachorros/nome?={nome}")
    public List<Cachorro> getCachorrosByNome(int pages, int size, @RequestParam String nome){
        return cachorroUseCase.getCachorrosByName(pages, size, nome);
    }
    @GetMapping("/cachorros/id?={id}")
    public Optional<Cachorro> getCachorroById(Long id){
        return cachorroUseCase.getCachorroById(id);
    }
    @PostMapping("/gerenciamento/salvar/gatos")
    public void registrarGato(@RequestBody Gato gato){
        gatoUseCase.salvarGato(gato);
    }
    @PostMapping("gerenciamento/salvar/cachorros")
    public void registrarCachorros(@RequestBody Cachorro cachorro){
        cachorroUseCase.salvarCachorro(cachorro);
    }
    @PutMapping("gerenciamento/atualizar=?{id}/gatos")
    public void atualizarGatos(@RequestParam Long id, @RequestBody DTOAtualizacaoAnimais dto){
        gatoUseCase.alterarGato(id, dto);
    }
    @PutMapping("gerenciamento/atualizar=?{id}/cachorros")
    public void atualizarCachorros(@RequestParam Long id, @RequestBody DTOAtualizacaoAnimais dto){
        cachorroUseCase.alterarCachorro(id, dto);
    }
    @DeleteMapping("gerenciamento/deletar=?{id}/gatos")
    public void deletarGatos(@RequestParam Long id){
        gatoUseCase.deletarGato(id);
    }
    @DeleteMapping("gerenciamento/deletar=?{id}/cachorros")
    public void deletarCachorros(@RequestParam Long id){
        cachorroUseCase.deletarCachorro(id);
    }
    @DeleteMapping("gerenciamento/adoção=?{id}/gatos")
    public void adotarGato(@RequestParam Long id){
        gatoUseCase.adotarGato(id);
    }
    @DeleteMapping("gerenciamento/adoção=?{id}/cachorros")
    public void adotarCachorro(@RequestParam Long id){
        cachorroUseCase.adotarCachorro(id);
    }
}
