package com.arthurhenrique_Dev.CatOng.Controllers.Security;

import com.arthurhenrique_Dev.CatOng.Controllers.Security.DTO.DTOAutenticacao;
import org.springframework.http.ResponseEntity;
import jakarta.validation.Valid;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class ControllerSecurity {

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid DTOAutenticacao dados){
        var usernamePassword = new UsernamePasswordAuthenticationToken(dados)
    }
}
