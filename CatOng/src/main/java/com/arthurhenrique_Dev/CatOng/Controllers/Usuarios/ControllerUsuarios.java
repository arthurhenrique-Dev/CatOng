package com.arthurhenrique_Dev.CatOng.Controllers.Usuarios;

import com.arthurhenrique_Dev.CatOng.Application.DTOs.Usuarios.Atualizacao.DTOAtualizacaoUComum;
import com.arthurhenrique_Dev.CatOng.Application.DTOs.Usuarios.Atualizacao.DTOAtualizacaoUGerenciamento;
import com.arthurhenrique_Dev.CatOng.Application.DTOs.Usuarios.Cadastro.DTORegistroUGerenciamento;
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

    @PutMapping("/atualizar")
    public void atualizar(String cpf, DTOAtualizacaoUComum dto) {
        comumUseCase.atualizarComum(cpf, dto);
    }
    @GetMapping("/gerenciamento/comum")
    public List<UComum> getUsers(int pages, int size){
        return comumUseCase.getUComuns(pages, size);
    }
    @GetMapping("/gerenciamento/comum/nome?={nome}")
    public List<UComum> getUsersByName(int pages, int size, @RequestParam String nome){
        return comumUseCase.getUComunsByName(pages, size, nome);
    }
    @GetMapping("/gerenciamento/comum/cpf?={cpf}")
    public Optional<UComum> getUserById(@RequestParam String cpf){
        return comumUseCase.getUComum(cpf);
    }
    @DeleteMapping("/gerenciamento/comum/remover?={cpf}")
    public void remover(@RequestParam String cpf){
        comumUseCase.removerComum(cpf);
    }
    @PostMapping
    public void adicionarFuncionario(DTORegistroUGerenciamento dto){
        gerenciamentoUseCase.salvarUGerenciamento(dto);
    }
    @PutMapping("/gerenciamento/funcionario/atualizar=?{nr}")
    public void atualizar(Long nr, @RequestParam DTOAtualizacaoUGerenciamento dto){
        gerenciamentoUseCase.atualizarUGerenciamento(nr, dto);
    }
    @GetMapping("/gerenciamento/funcionario")
    public List<UGerenciamento> getUGerenciamentos(int pages, int size){
        return gerenciamentoUseCase.getUGerenciamentos(pages, size);
    }
    @GetMapping("/gerenciamento/funcionario/nr?={nr}")
    public Optional<UGerenciamento> getUGerenciamentosByNr(@RequestParam Long nr){
        return gerenciamentoUseCase.getUGerenciamentoByNR(nr);
    }
    @DeleteMapping("/gerenciamento/funcionario/remover?={nr}")
    public void removerFuncionario(@RequestParam Long nr){
        gerenciamentoUseCase.removerUGerenciamento(nr);
    }
}
