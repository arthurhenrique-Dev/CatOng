package com.arthurhenrique_Dev.CatOng.Controllers.Usuarios;

import com.arthurhenrique_Dev.CatOng.Application.DTOs.Usuarios.Atualizacao.DTOAtualizacaoUComum;
import com.arthurhenrique_Dev.CatOng.Application.DTOs.Usuarios.Atualizacao.DTOAtualizacaoUGerenciamento;
import com.arthurhenrique_Dev.CatOng.Application.DTOs.Usuarios.Cadastro.DTORegistroUGerenciamento;
import com.arthurhenrique_Dev.CatOng.Application.DTOs.Usuarios.Retorno.DTORetornoUComum;
import com.arthurhenrique_Dev.CatOng.Application.DTOs.Usuarios.Retorno.DTORetornoUGerenciamento;
import com.arthurhenrique_Dev.CatOng.Application.UseCaseUsuarios.UComumUseCase.UComumUseCase;
import com.arthurhenrique_Dev.CatOng.Application.UseCaseUsuarios.UGerenciamentoUseCase.UGerenciamentoUseCase;
import com.arthurhenrique_Dev.CatOng.Domain.Usuarios.UComum.UComum;
import com.arthurhenrique_Dev.CatOng.Domain.Usuarios.UGerenciamento.UGerenciamento;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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

    @PutMapping("/atualizar/{cpf}")
    @Operation(summary = "atualizar usuários", description = "requisição responsável por atualizar os usuários")
    @ApiResponse(responseCode = "200", description = "atualização do usuário efetuada")
    @ApiResponse(responseCode = "400", description = "erro ao atualizar")
    @ApiResponse(responseCode = "500", description = "erro de servidor")
    public void atualizar(@PathVariable String cpf, @RequestBody DTOAtualizacaoUComum dto) {
        comumUseCase.atualizarComum(cpf, dto);
    }

    @GetMapping("/gerenciamento/comum")
    @Operation(summary = "buscar usuários", description = "requisição responsável por buscar os usuários")
    @ApiResponse(responseCode = "200", description = "busca pelos usuários efetuada")
    @ApiResponse(responseCode = "400", description = "erro ao efetuar busca")
    @ApiResponse(responseCode = "500", description = "erro de servidor")
    public List<DTORetornoUComum> getUsers(@RequestParam(defaultValue = "0") Integer pages, @RequestParam(defaultValue = "10") Integer size){
        return comumUseCase.getUComuns(pages, size);
    }

    @GetMapping("/gerenciamento/comum/off")
    @Operation(summary = "buscar usuários inativos", description = "requisição responsável por buscar os usuários inativos")
    @ApiResponse(responseCode = "200", description = "busca pelos usuários efetuada")
    @ApiResponse(responseCode = "400", description = "erro ao efetuar busca")
    @ApiResponse(responseCode = "500", description = "erro de servidor")
    public List<DTORetornoUComum> getUsersOff(@RequestParam(defaultValue = "0") Integer pages, @RequestParam(defaultValue = "10") Integer size){
        return comumUseCase.getUComunsInativos(pages, size);
    }

    @GetMapping("/gerenciamento/comum/nome/{nome}")
    @Operation(summary = "buscar usuários pelo nome", description = "requisição responsável por buscar os usuários pelo nome")
    @ApiResponse(responseCode = "200", description = "busca dos usuários pelo nome efetuada")
    @ApiResponse(responseCode = "400", description = "erro ao efetuar busca")
    @ApiResponse(responseCode = "500", description = "erro de servidor")
    public List<DTORetornoUComum> getUsersByName(@RequestParam(defaultValue = "0") Integer pages, @RequestParam(defaultValue = "10") Integer size, @RequestParam String nome){
        return comumUseCase.getUComunsByName(pages, size, nome);
    }

    @GetMapping("/gerenciamento/comum/cpf/{cpf}")
    @Operation(summary = "buscar um usuário pelo cpf", description = "requisição responsável por buscar o usuário pelo cpf")
    @ApiResponse(responseCode = "200", description = "busca do usuário por cpf efetuada")
    @ApiResponse(responseCode = "400", description = "erro ao efetuar busca")
    @ApiResponse(responseCode = "500", description = "erro de servidor")
    public Optional<DTORetornoUComum> getUserByCpf(@PathVariable String cpf){
        return comumUseCase.getUComum(cpf);
    }

    @DeleteMapping("/gerenciamento/comum/remover_cpf/{cpf}")
    @Operation(summary = "remover usuários", description = "requisição responsável por remover os usuários")
    @ApiResponse(responseCode = "200", description = "remoção do usuário efetuada")
    @ApiResponse(responseCode = "400", description = "erro ao efetuar busca")
    @ApiResponse(responseCode = "500", description = "erro de servidor")
    public void remover(@PathVariable String cpf){
        comumUseCase.removerComum(cpf);
    }

    @PutMapping("/gerenciamento/funcionario/atualizar_nr/{nr}")
    @Operation(summary = "atualizar funcionários", description = "requisição responsável por atualizar os funcionários")
    @ApiResponse(responseCode = "200", description = "atualização do funcionário efetuada")
    @ApiResponse(responseCode = "400", description = "erro ao atualizar")
    @ApiResponse(responseCode = "500", description = "erro de servidor")
    public void atualizar(@PathVariable Long nr, @RequestBody DTOAtualizacaoUGerenciamento dto){
        gerenciamentoUseCase.atualizarUGerenciamento(nr, dto);
    }

    @GetMapping("/gerenciamento/funcionario")
    @Operation(summary = "buscar funcionários", description = "requisição responsável por buscar os funcionários")
    @ApiResponse(responseCode = "200", description = "busca pelos funcionários efetuada")
    @ApiResponse(responseCode = "400", description = "erro ao efetuar busca")
    @ApiResponse(responseCode = "500", description = "erro de servidor")
    public List<DTORetornoUGerenciamento> getUGerenciamentos(@RequestParam(defaultValue = "0") Integer pages, @RequestParam(defaultValue = "10") Integer size){
        return gerenciamentoUseCase.getUGerenciamentos(pages, size);
    }

    @GetMapping("/gerenciamento/funcionario/off")
    @Operation(summary = "buscar funcionários", description = "requisição responsável por buscar os funcionários")
    @ApiResponse(responseCode = "200", description = "busca pelos funcionários efetuada")
    @ApiResponse(responseCode = "400", description = "erro ao efetuar busca")
    @ApiResponse(responseCode = "500", description = "erro de servidor")
    public List<DTORetornoUGerenciamento> getUGerenciamentosOff(@RequestParam(defaultValue = "0") Integer pages, @RequestParam(defaultValue = "10") Integer size){
        return gerenciamentoUseCase.getUGerenciamentosInativos(pages, size);
    }

    @GetMapping("/gerenciamento/funcionario/nr/{nr}")
    @Operation(summary = "buscar o funcionário", description = "requisição responsável por buscar o funcionário pelo nr")
    @ApiResponse(responseCode = "200", description = "busca do funcionário pelo nr efetuada")
    @ApiResponse(responseCode = "400", description = "erro ao efetuar busca")
    @ApiResponse(responseCode = "500", description = "erro de servidor")
    public Optional<DTORetornoUGerenciamento> getUGerenciamentosByNr(@PathVariable Long nr){
        return gerenciamentoUseCase.getUGerenciamentoByNR(nr);
    }

    @GetMapping("/gerenciamento/funcionario/cpf/{cpf}")
    @Operation(summary = "buscar o funcionário", description = "requisição responsável por buscar o funcionário pelo cpf")
    @ApiResponse(responseCode = "200", description = "busca do funcionário pelo cpf efetuada")
    @ApiResponse(responseCode = "400", description = "erro ao efetuar busca")
    @ApiResponse(responseCode = "500", description = "erro de servidor")
    public Optional<DTORetornoUGerenciamento> getUGerenciamentosByCpf(@PathVariable String cpf){
        return gerenciamentoUseCase.getUGerenciamento(cpf);
    }

    @DeleteMapping("/gerenciamento/funcionario/remover_nr/{nr}")
    @Operation(summary = "remover funcionários", description = "requisição responsável por remover os funcionários")
    @ApiResponse(responseCode = "200", description = "remoção do funcionário efetuada")
    @ApiResponse(responseCode = "400", description = "erro ao efetuar busca")
    @ApiResponse(responseCode = "500", description = "erro de servidor")
    public void removerFuncionario(@PathVariable Long nr){
        gerenciamentoUseCase.removerUGerenciamento(nr);
    }
}
