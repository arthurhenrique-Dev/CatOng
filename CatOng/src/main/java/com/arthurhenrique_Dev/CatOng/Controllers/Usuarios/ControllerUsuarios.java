package com.arthurhenrique_Dev.CatOng.Controllers.Usuarios;

import com.arthurhenrique_Dev.CatOng.Application.DTOs.DTORegistroUComum;
import com.arthurhenrique_Dev.CatOng.Application.UseCaseUsuarios.UComumUseCase.UComumUseCase;
import com.arthurhenrique_Dev.CatOng.Application.UseCaseUsuarios.UGerenciamentoUseCase.UGerenciamentoUseCase;
import com.arthurhenrique_Dev.CatOng.Domain.Usuarios.UComum.UComum;
import com.arthurhenrique_Dev.CatOng.Domain.Usuarios.UGerenciamento.UGerenciamento;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("user")
public class ControllerUsuarios {

    private final UGerenciamentoUseCase gerenciamentoUseCase;
    private final UComumUseCase comumUseCase;

    public ControllerUsuarios(UGerenciamentoUseCase gerenciamentoUseCase, UComumUseCase comumUseCase) {
        this.gerenciamentoUseCase = gerenciamentoUseCase;
        this.comumUseCase = comumUseCase;
    }

    @PostMapping("/cadastrar")
    public void cadastrar(@RequestBody DTORegistroUComum registroUComum){
        comumUseCase.salvarComum(registroUComum);
    }
    @PutMapping("/atualizar")
    public void atualizar(Long id, UComum uComum){
        comumUseCase.atualizarComum(id, uComum);
    }
    @GetMapping("/gerenciamento")
    public List<UComum> getUsers(int pages, int size){
        return comumUseCase.getUComuns(pages, size);
    }
    @GetMapping("/gerenciamento/nome?={nome}")
    public List<UComum> getUsersByName(int pages, int size, @RequestParam String nome){
        return comumUseCase.getUComunsByName(pages, size, nome);
    }
    @GetMapping("/gerenciamento/nome?={id}")
    public Optional<UComum> getUserByEmail(@RequestParam Long id){
        return comumUseCase.getUComumById(id);
    }
    @DeleteMapping("/gerenciamento/remover?={id}")
    public void remover(@RequestParam Long id){
        comumUseCase.removerComum(id);
    }

    @PostMapping("/gerenciamento/cadastrar")
    public void cadastrarFuncionario(@RequestBody UGerenciamento ugerenciamento){
        gerenciamentoUseCase.salvarUGerenciamento(ugerenciamento);
    }
    @PutMapping("/gerenciamento/atualizar=?{nr}")
    public void atualizar(Long nr, @RequestParam UGerenciamento uGerenciamento){
        gerenciamentoUseCase.atualizarUGerenciamento(nr, uGerenciamento);
    }
    @GetMapping("/gerenciamento")
    public List<UGerenciamento> getUGerenciamentos(int pages, int size){
        return gerenciamentoUseCase.getUGerenciamentos(pages, size);
    }
    @GetMapping("/gerenciamento/nome?={nr}")
    public Optional<UGerenciamento> getUGerenciamentosByNr(@RequestParam Long nr){
        return gerenciamentoUseCase.getUGerenciamentoByNR(nr);
    }
    @DeleteMapping("/gerenciamento/func/remover?={id}")
    public void removerFuncionario(@RequestParam Long id){
        gerenciamentoUseCase.removerUGerenciamento(id);
    }
}
