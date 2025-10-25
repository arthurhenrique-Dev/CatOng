package com.arthurhenrique_Dev.CatOng.Controllers.Autenticacao;

import com.arthurhenrique_Dev.CatOng.Application.UseCaseUsuarios.UComumUseCase.UComumUseCase;
import com.arthurhenrique_Dev.CatOng.Application.UseCaseUsuarios.UGerenciamentoUseCase.UGerenciamentoUseCase;
import com.arthurhenrique_Dev.CatOng.DadosParaTeste.DadosPessoa.DadosParaTesteValido;
import com.arthurhenrique_Dev.CatOng.Domain.Usuarios.Base.Permissao;
import com.arthurhenrique_Dev.CatOng.Security.SecurityService.TokenService;
import com.arthurhenrique_Dev.CatOng.UsoPessoal.ProcessosIniciais.DTOAdminLogin;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.assertThat;

class AutenticacaoControllerTest {

    @Mock
    UComumUseCase uComumUseCase;

    @Mock
    UGerenciamentoUseCase uGerenciamentoUseCase;

    @Mock
    AuthenticationManager authenticationManager;

    @Mock
    TokenService tokenService;

    @InjectMocks
    AutenticacaoController autenticacaoController;

    DadosParaTesteValido dtv;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        dtv = new DadosParaTesteValido();
    }

    @Test
    void login() {
        var login = dtv.loginValidoULogin();

        Authentication authentication = mock(Authentication.class);

        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenReturn(authentication);
        when(tokenService.GerarTokenGenerico(login.cpf(), Permissao.COMUM))
                .thenReturn("token legal");

        ResponseEntity<String> response = autenticacaoController.login(login);

        verify(authenticationManager, times(1)).authenticate(any(UsernamePasswordAuthenticationToken.class));
        verify(tokenService).GerarTokenGenerico(login.cpf(), Permissao.COMUM);
    }

    @Test
    void loginFunc() {
        var login = dtv.loginValidoULogin();

        Authentication authentication = mock(Authentication.class);

        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenReturn(authentication);
        when(tokenService.GerarTokenGenerico(login.cpf(), Permissao.GERENCIAMENTO))
                .thenReturn("token legal");

        ResponseEntity<String> response = autenticacaoController.loginFunc(login);

        verify(authenticationManager, times(1)).authenticate(any(UsernamePasswordAuthenticationToken.class));
        verify(tokenService).GerarTokenGenerico(login.cpf(), Permissao.GERENCIAMENTO);
    }

    @Test
    void signUp() {
        var registro = dtv.registroValidoUComum();

        uComumUseCase.salvarComum(registro);

        verify(uComumUseCase, times(1)).salvarComum(registro);
    }

    @Test
    void loginAdmin() {

        var adminLogin = new DTOAdminLogin(

                "rogerin",
                "senhaSuperMisteriosa123@"
        );

        Authentication authentication = mock(Authentication.class);

        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenReturn(authentication);
        when(tokenService.GerarTokenGenerico(adminLogin.nome(), Permissao.ADMIN))
                .thenReturn("token legal");

        ResponseEntity<String> response = autenticacaoController.loginAdmin(adminLogin);

        verify(authenticationManager, times(1)).authenticate(any(UsernamePasswordAuthenticationToken.class));
    }

    @Test
    void signUpFunc() {
        var registro = dtv.registroValidoUComum();

        uComumUseCase.salvarComum(registro);

        verify(uComumUseCase, times(1)).salvarComum(registro);
    }
}