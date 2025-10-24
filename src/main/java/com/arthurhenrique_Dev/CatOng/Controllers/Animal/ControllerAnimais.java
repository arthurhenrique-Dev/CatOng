package com.arthurhenrique_Dev.CatOng.Controllers.Animal;

import com.arthurhenrique_Dev.CatOng.Application.DTOs.Animais.DTOAtualizacaoAnimais;
import com.arthurhenrique_Dev.CatOng.Application.UseCaseAnimal.UCachorroUseCase.CachorroUseCase;
import com.arthurhenrique_Dev.CatOng.Application.UseCaseAnimal.UGatoUseCase.GatoUseCase;
import com.arthurhenrique_Dev.CatOng.Domain.Animal.Cachorros.Cachorro;
import com.arthurhenrique_Dev.CatOng.Domain.Animal.Gatos.Gato;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("pets")
@Tag(name = "controller de animais", description = "Controller responsavel por administrar os bichinhos")
public class ControllerAnimais {

    private final CachorroUseCase cachorroUseCase;
    private final GatoUseCase gatoUseCase;

    public ControllerAnimais(CachorroUseCase cachorroUseCase, GatoUseCase gatoUseCase) {
        this.cachorroUseCase = cachorroUseCase;
        this.gatoUseCase = gatoUseCase;
    }

    @GetMapping("/gatos")
    @Operation(summary = "listar os gatos", description = "requisição responsável por listar os gatos")
    @ApiResponse(responseCode = "200", description = "busca pelos gatos efetuada")
    @ApiResponse(responseCode = "400", description = "erro ao efetuar busca")
    @ApiResponse(responseCode = "500", description = "erro de servidor")
    public List<Gato> getGatos(@RequestParam(defaultValue = "0") Integer pages, @RequestParam(defaultValue = "10") Integer size){
        return gatoUseCase.getGato(pages, size);
    }

    @GetMapping("/gatos/nome/{nome}")
    @Operation(summary = "listar os gatos pelo nome", description = "requisição responsável por listar os gatos por um nome")
    @ApiResponse(responseCode = "200", description = "busca de gatos pelo nome efetuada")
    @ApiResponse(responseCode = "400", description = "erro ao efetuar busca")
    @ApiResponse(responseCode = "500", description = "erro de servidor")
    public List<Gato> getGatosByNome(@RequestParam(defaultValue = "0") Integer pages, @RequestParam(defaultValue = "10") Integer size, @PathVariable String nome){
        return gatoUseCase.getGatoByName(pages, size, nome);
    }

    @GetMapping("/gatos/id/{id}")
    @Operation(summary = "listar o gato pelo id", description = "requisição responsável por lista o gato pelo seu id")
    @ApiResponse(responseCode = "200", description = "busca de gatos pelo id efetuada")
    @ApiResponse(responseCode = "400", description = "erro ao efetuar busca")
    @ApiResponse(responseCode = "500", description = "erro de servidor")
    public Optional<Gato> getGatosById(@RequestParam Long id){
        return gatoUseCase.getGatoById(id);
    }

    @GetMapping("/cachorros")
    @Operation(summary = "listar os cachorros", description = "requisição responsável por listar os cachorros")
    @ApiResponse(responseCode = "200", description = "busca pelos cachorros efetuada")
    @ApiResponse(responseCode = "400", description = "erro ao efetuar busca")
    @ApiResponse(responseCode = "500", description = "erro de servidor")
    public List<Cachorro> getCachorros(@RequestParam(defaultValue = "0") Integer pages, @RequestParam(defaultValue = "10") Integer size){
        return cachorroUseCase.getCachorros(pages, size);
    }

    @GetMapping("/cachorros/nome/{nome}")
    @Operation(summary = "listar os cachorros pelo nome", description = "requisição responsável por listar os cachorros por um nome")
    @ApiResponse(responseCode = "200", description = "busca de cachorros pelo nome efetuada")
    @ApiResponse(responseCode = "400", description = "erro ao efetuar busca")
    @ApiResponse(responseCode = "500", description = "erro de servidor")
    public List<Cachorro> getCachorrosByNome(@RequestParam(defaultValue = "0") Integer pages, @RequestParam(defaultValue = "10") Integer size, @RequestParam String nome){
        return cachorroUseCase.getCachorrosByName(pages, size, nome);
    }

    @GetMapping("/cachorros/id/{id}")
    @Operation(summary = "listar o cachorro pelo nome", description = "requisição responsável por listar os cachorros por um id")
    @ApiResponse(responseCode = "200", description = "busca de cachorros pelo id efetuada")
    @ApiResponse(responseCode = "400", description = "erro ao efetuar busca")
    @ApiResponse(responseCode = "500", description = "erro de servidor")
    public Optional<Cachorro> getCachorroById(Long id){
        return cachorroUseCase.getCachorroById(id);
    }

    @PostMapping("/gerenciamento/salvar/gatos")
    @Operation(summary = "cadastrar gatos", description = "requisição responsável por cadastrar um gato")
    @ApiResponse(responseCode = "200", description = "cadastro do gato efetuado")
    @ApiResponse(responseCode = "400", description = "erro ao cadastrar")
    @ApiResponse(responseCode = "500", description = "erro de servidor")
    public void registrarGato(@RequestBody Gato gato){
        gatoUseCase.salvarGato(gato);
    }

    @PostMapping("/gerenciamento/salvar/cachorros")
    @Operation(summary = "cadastrar cachorro", description = "requisição responsável por listar os cachorros por um nome")
    @ApiResponse(responseCode = "200", description = "cadastro do cachorro efetuado")
    @ApiResponse(responseCode = "400", description = "erro ao cadastrar")
    @ApiResponse(responseCode = "500", description = "erro de servidor")
    public void registrarCachorros(@RequestBody Cachorro cachorro){
        cachorroUseCase.salvarCachorro(cachorro);
    }

    @PutMapping("/gerenciamento/atualizar_id/{id}/gatos")
    @Operation(summary = "atualizar gatos", description = "requisição responsável por atualizar os gatos")
    @ApiResponse(responseCode = "200", description = "atualização do gato efetuada")
    @ApiResponse(responseCode = "400", description = "erro ao atualizar")
    @ApiResponse(responseCode = "500", description = "erro de servidor")
    public void atualizarGatos(@PathVariable Long id, @RequestBody DTOAtualizacaoAnimais dto){
        gatoUseCase.alterarGato(id, dto);
    }

    @PutMapping("/gerenciamento/atualizar_id/{id}/cachorros")
    @Operation(summary = "atualizar cachorros", description = "requisição responsável por atualizar os cachorros")
    @ApiResponse(responseCode = "200", description = "atualização do gato efetuada")
    @ApiResponse(responseCode = "400", description = "erro ao atualizar")
    @ApiResponse(responseCode = "500", description = "erro de servidor")
    public void atualizarCachorros(@PathVariable Long id, @RequestBody DTOAtualizacaoAnimais dto){
        cachorroUseCase.alterarCachorro(id, dto);
    }

    @DeleteMapping("/gerenciamento/deletar_id/{id}/gatos")
    @Operation(summary = "deletar gatos", description = "requisição responsável por deletar os gatos")
    @ApiResponse(responseCode = "200", description = "gato deletado com sucesso")
    @ApiResponse(responseCode = "400", description = "erro ao deletar")
    @ApiResponse(responseCode = "500", description = "erro de servidor")
    public void deletarGatos(@PathVariable Long id){
        gatoUseCase.deletarGato(id);
    }

    @DeleteMapping("/gerenciamento/deletar_id/{id}/cachorros")
    @Operation(summary = "deletar cachorros", description = "requisição responsável por deletar os cachorros")
    @ApiResponse(responseCode = "200", description = "cachorro deletado com sucesso")
    @ApiResponse(responseCode = "400", description = "erro ao deletar")
    @ApiResponse(responseCode = "500", description = "erro de servidor")
    public void deletarCachorros(@PathVariable Long id){
        cachorroUseCase.deletarCachorro(id);
    }

    @DeleteMapping("/gerenciamento/adoção_id/{id}/gatos")
    @Operation(summary = "adotar gatos", description = "requisição responsável por adotar os gatos")
    @ApiResponse(responseCode = "200", description = "gato adotado com sucesso")
    @ApiResponse(responseCode = "400", description = "erro ao adotar")
    @ApiResponse(responseCode = "500", description = "erro de servidor")
    public void adotarGato(@PathVariable Long id){
        gatoUseCase.adotarGato(id);
    }

    @DeleteMapping("/gerenciamento/adoção_id/{id}/cachorros")
    @Operation(summary = "adotar cachorros", description = "requisição responsável por adotar os cachorros")
    @ApiResponse(responseCode = "200", description = "cachorro adotado com sucesso")
    @ApiResponse(responseCode = "400", description = "erro ao adotar")
    @ApiResponse(responseCode = "500", description = "erro de servidor")
    public void adotarCachorro(@PathVariable Long id){
        cachorroUseCase.adotarCachorro(id);
    }
}
