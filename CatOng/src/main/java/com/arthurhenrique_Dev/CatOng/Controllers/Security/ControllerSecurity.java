package com.arthurhenrique_Dev.CatOng.Controllers.Security;

import com.arthurhenrique_Dev.CatOng.Controllers.Security.DTO.DTOAutenticacao;
import com.arthurhenrique_Dev.CatOng.Controllers.Security.DTO.DTORegistro;
import com.arthurhenrique_Dev.CatOng.Infraestructure.Security.ConfigSegurança.TokenService;
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

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid DTOAutenticacao dados){
        var usernamePassword = new UsernamePasswordAuthenticationToken(dados.login(),dados.senha());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        return ResponseEntity.ok().build();
    }
    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid DTORegistro dados){

    }
}
