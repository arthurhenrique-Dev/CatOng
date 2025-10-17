package com.arthurhenrique_Dev.CatOng.Controllers.ControllerDeAcesso;

import com.arthurhenrique_Dev.CatOng.Application.DTOs.Login.DTOLogin;
import com.arthurhenrique_Dev.CatOng.Application.DTOs.Cadastro.DTORegistroUComum;
import com.arthurhenrique_Dev.CatOng.Application.DTOs.Cadastro.DTORegistroUGerenciamento;
import com.arthurhenrique_Dev.CatOng.Application.UseCaseUsuarios.UComumUseCase.UComumUseCase;
import com.arthurhenrique_Dev.CatOng.Application.UseCaseUsuarios.UGerenciamentoUseCase.UGerenciamentoUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import jakarta.validation.Valid;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class ControllerSecurity {

    private final UComumUseCase uComumUseCase;
    private final UGerenciamentoUseCase ugerenciamentoUseCase;

    public ControllerSecurity(UComumUseCase uComumUseCase, UGerenciamentoUseCase ugerenciamentoUseCase) {
        this.uComumUseCase = uComumUseCase;
        this.ugerenciamentoUseCase = ugerenciamentoUseCase;
    }

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid DTOLogin dados){
        var usernamePassword = new UsernamePasswordAuthenticationToken(dados.cpf(),dados.senha());
        var auth = this.authenticationManager.authenticate(usernamePassword);
        return ResponseEntity.ok().build();
    }
    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid DTORegistroUComum dados){
        uComumUseCase.salvarComum(dados);
        return ResponseEntity.ok().build();
    }
    @PostMapping("/gerenciamento/login")
    public ResponseEntity gerenciamentoLogin(@RequestBody @Valid DTOLogin dados){
        var usernamePassword = new UsernamePasswordAuthenticationToken(dados.cpf(), dados.senha());
        var auth = this.authenticationManager.authenticate(usernamePassword);
        return ResponseEntity.ok().build();
    }
    @PostMapping("/gerenciamento/registro")
    public ResponseEntity gerenciamentoRegister(@RequestBody @Valid DTORegistroUGerenciamento dados){
        ugerenciamentoUseCase.salvarUGerenciamento(dados);
        return ResponseEntity.ok().build();
    }
}
