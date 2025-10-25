package com.arthurhenrique_Dev.CatOng.Controllers.Autenticacao;

import com.arthurhenrique_Dev.CatOng.Application.DTOs.Usuarios.Cadastro.DTORegistroUComum;
import com.arthurhenrique_Dev.CatOng.Application.DTOs.Usuarios.Cadastro.DTORegistroUGerenciamento;
import com.arthurhenrique_Dev.CatOng.Application.DTOs.Usuarios.Login.DTOLogin;
import com.arthurhenrique_Dev.CatOng.Application.UseCaseUsuarios.UComumUseCase.UComumUseCase;
import com.arthurhenrique_Dev.CatOng.Application.UseCaseUsuarios.UGerenciamentoUseCase.UGerenciamentoUseCase;
import com.arthurhenrique_Dev.CatOng.Domain.Usuarios.Base.Permissao;
import com.arthurhenrique_Dev.CatOng.Infraestructure.Persistence.Entities.UsuarioEntities.EUComum.EUComum;
import com.arthurhenrique_Dev.CatOng.Infraestructure.Persistence.Entities.UsuarioEntities.EUGerenciamento.EUGerenciamento;
import com.arthurhenrique_Dev.CatOng.Security.SecurityService.TokenService;
import com.arthurhenrique_Dev.CatOng.UsoPessoal.ProcessosIniciais.ADMIN;
import com.arthurhenrique_Dev.CatOng.UsoPessoal.ProcessosIniciais.DTOAdminLogin;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
@Tag(name = "porta de entrada", description = "Controller de login/cadastro, para validar e prosseguir")
public class AutenticacaoController {

    private final UComumUseCase uComumUseCase;
    private final UGerenciamentoUseCase uGerenciamentoUseCase;
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    public AutenticacaoController(UComumUseCase uComumUseCase, UGerenciamentoUseCase uGerenciamentoUseCase, AuthenticationManager authenticationManager, TokenService tokenService) {
        this.uComumUseCase = uComumUseCase;
        this.uGerenciamentoUseCase = uGerenciamentoUseCase;
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    @PostMapping("/login")
    @Operation(summary = "responsável pelo login do usuário comum")
    @ApiResponse(responseCode = "200", description = "login feito com sucesso")
    @ApiResponse(responseCode = "400", description = "erro ao efetuar login")
    @ApiResponse(responseCode = "500", description = "erro de servidor")
    public ResponseEntity login (@RequestBody @Valid DTOLogin dto){
        var autenticacao = new UsernamePasswordAuthenticationToken(dto.cpf(), dto.senha());
        this.authenticationManager.authenticate(autenticacao);
        var token = tokenService.GerarTokenGenerico(dto.cpf(), Permissao.COMUM);
        return ResponseEntity.ok(token);
    }

    @PostMapping("/func/login")
    @Operation(summary = "responsável pelo login do usuário de gerenciamento")
    @ApiResponse(responseCode = "200", description = "login feito com sucesso")
    @ApiResponse(responseCode = "400", description = "erro ao efetuar login")
    @ApiResponse(responseCode = "500", description = "erro de servidor")
    public ResponseEntity loginFunc (@RequestBody @Valid DTOLogin dto){
        var autenticacao = new UsernamePasswordAuthenticationToken(dto.cpf(), dto.senha());
        this.authenticationManager.authenticate(autenticacao);
        var token = tokenService.GerarTokenGenerico(dto.cpf(), Permissao.GERENCIAMENTO);
        return ResponseEntity.ok(token);
    }

    @PostMapping("/sign_up")
    @Operation(summary = "responsável por cadastrar o usuário")
    @ApiResponse(responseCode = "200", description = "cadastro feito com sucesso")
    @ApiResponse(responseCode = "400", description = "erro ao cadastrar")
    @ApiResponse(responseCode = "500", description = "erro de servidor")
    public ResponseEntity signUp (@RequestBody @Valid DTORegistroUComum dto){
        if (this.uComumUseCase.getUComum(dto.cpf()).isPresent()){
            return ResponseEntity.badRequest().build();
        }
        String senhaCriptografada = new BCryptPasswordEncoder().encode(dto.senha());
            DTORegistroUComum dtoEncriptografado = new DTORegistroUComum(
                    dto.nome(),
                    dto.cpf(),
                    dto.rg(),
                    senhaCriptografada,
                    dto.email(),
                    dto.telefone(),
                    dto.dataDeNascimento(),
                    dto.endereco()
            );
            this.uComumUseCase.salvarComum(dtoEncriptografado);
            return ResponseEntity.ok().build();
    }

    @PostMapping("/gerenciamento/sign_up")
    @Operation(summary = "responsável por cadastrar o usuário de gerenciamento")
    @ApiResponse(responseCode = "200", description = "cadastro feito com sucesso")
    @ApiResponse(responseCode = "400", description = "erro ao cadastrar")
    @ApiResponse(responseCode = "500", description = "erro de servidor")
    public ResponseEntity signUpFunc (@RequestBody @Valid DTORegistroUGerenciamento dto){
        if (this.uGerenciamentoUseCase.getUGerenciamento(dto.cpf()).isPresent()){
            return ResponseEntity.badRequest().build();
        }
        String senhaCriptografada = new BCryptPasswordEncoder().encode(dto.senha());
        DTORegistroUGerenciamento dtoEncriptografado = new DTORegistroUGerenciamento(
                dto.nome(),
                dto.cpf(),
                dto.rg(),
                senhaCriptografada,
                dto.email(),
                dto.telefone()
        );
        this.uGerenciamentoUseCase.salvarUGerenciamento(dtoEncriptografado);
        return ResponseEntity.ok().build();
    }

    @PostMapping("admin/login")
    @Operation(summary = "responsável pelo login do admin")
    @ApiResponse(responseCode = "200", description = "login feito com sucesso")
    @ApiResponse(responseCode = "400", description = "erro ao efetuar login")
    @ApiResponse(responseCode = "500", description = "erro de servidor")
    public ResponseEntity loginAdmin (@RequestBody @Valid DTOAdminLogin dto){
        var autenticacaoAdmin = new UsernamePasswordAuthenticationToken(dto.nome(), dto.senha());
        var authentication = this.authenticationManager.authenticate(autenticacaoAdmin);
        return ResponseEntity.ok().build();
    }
}
