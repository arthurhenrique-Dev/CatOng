package com.arthurhenrique_Dev.CatOng.Controllers.Usuarios;

import com.arthurhenrique_Dev.CatOng.Application.UseCaseUsuarios.UComumUseCase.UComumUseCase;
import com.arthurhenrique_Dev.CatOng.Application.UseCaseUsuarios.UGerenciamentoUseCase.UGerenciamentoUseCase;
import com.arthurhenrique_Dev.CatOng.Domain.Usuarios.UComum.UComum;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public void cadastrar(@RequestBody UComum uComum){
        comumUseCase.salvarComum(uComum);
    }
    @PutMapping("/atualizar=?{id}")
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



}
