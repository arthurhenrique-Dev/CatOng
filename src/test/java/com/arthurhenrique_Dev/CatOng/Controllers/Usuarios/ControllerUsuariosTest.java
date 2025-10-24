package com.arthurhenrique_Dev.CatOng.Controllers.Usuarios;

import com.arthurhenrique_Dev.CatOng.Application.UseCaseUsuarios.UComumUseCase.UComumUseCase;
import com.arthurhenrique_Dev.CatOng.Application.UseCaseUsuarios.UGerenciamentoUseCase.UGerenciamentoUseCase;
import com.arthurhenrique_Dev.CatOng.DadosParaTeste.DadosPessoa.DadosParaTesteValido;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.assertThat;

import static org.junit.jupiter.api.Assertions.*;

class ControllerUsuariosTest {

    @Mock
    UComumUseCase comumUseCase;

    @Mock
    UGerenciamentoUseCase gerenciamentoUseCase;

    private DadosParaTesteValido dtv;

    @InjectMocks
    ControllerUsuarios controllerUsuarios;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        dtv = new DadosParaTesteValido();
    }

    @Test
    void atualizar() {

        var cpf = dtv.registroValidoUComum().cpf();
        var dto = dtv.atualizacaoValidoUComum();

        controllerUsuarios.atualizar(cpf, dto);

        verify(comumUseCase, times(1)).atualizarComum(cpf, dto);
    }

    @Test
    void getUsers() {

        var retornoEsperado = dtv.retornoValidoUComum();

        when(comumUseCase.getUComuns(0,1)).thenReturn(List.of(retornoEsperado));

        var retorno = controllerUsuarios.getUsers(0,1);

        verify(comumUseCase, times(1)).getUComuns(0,1);
        assertThat(retorno.get(0)).isEqualTo(retornoEsperado);
    }

    @Test
    void getUsersByName() {

        var retornoEsperado = dtv.retornoValidoUComum();
        var nome = dtv.registroValidoUComum().nome();

        when(comumUseCase.getUComunsByName(0,1,nome)).thenReturn(List.of(retornoEsperado));

        var retorno = controllerUsuarios.getUsersByName(0,1,nome);

        verify(comumUseCase, times(1)).getUComunsByName(0,1,nome);
        assertThat(retorno.get(0)).isEqualTo(retornoEsperado);
    }

    @Test
    void getUserByCpf() {

        var retornoEsperado = dtv.retornoValidoUComum();
        var cpf = dtv.registroValidoUComum().cpf();

        when(comumUseCase.getUComum(cpf)).thenReturn(Optional.of(retornoEsperado));

        var retorno = controllerUsuarios.getUserByCpf(cpf);

        verify(comumUseCase, times(1)).getUComum(cpf);
        assertThat(retorno.get()).isEqualTo(retornoEsperado);
    }

    @Test
    void remover() {

        var cpf = dtv.registroValidoUComum().cpf();

        controllerUsuarios.remover(cpf);
        verify(comumUseCase, times(1)).removerComum(cpf);
    }

    @Test
    void testAtualizar() {

        var cpf = dtv.registroValidoUComum().cpf();
        var dto = dtv.atualizacaoValidoUComum();

        controllerUsuarios.atualizar(cpf, dto);
        verify(comumUseCase, times(1)).atualizarComum(cpf, dto);
    }

    @Test
    void getUGerenciamentos() {

        var retornoEsperado = dtv.retornoValidoUgerenciamento();

        when(gerenciamentoUseCase.getUGerenciamentos(0,1)).thenReturn(List.of(retornoEsperado));

        var retorno = controllerUsuarios.getUGerenciamentos(0,1);

        verify(gerenciamentoUseCase, times(1)).getUGerenciamentos(0,1);
        assertThat(retorno.get(0)).isEqualTo(retornoEsperado);
    }

    @Test
    void getUGerenciamentosByNr() {

        var nr =  1L;
        var retornoEsperado = dtv.retornoValidoUgerenciamento();

        when(gerenciamentoUseCase.getUGerenciamentoByNR(nr)).thenReturn(Optional.of(retornoEsperado));

        var retorno = controllerUsuarios.getUGerenciamentosByNr(nr);

        verify(gerenciamentoUseCase, times(1)).getUGerenciamentoByNR(nr);
        assertThat(retorno.get()).isEqualTo(retornoEsperado);
    }

    @Test
    void removerFuncionario() {

        var nr = 1L;

        controllerUsuarios.removerFuncionario(nr);
        verify(gerenciamentoUseCase, times(1)).removerUGerenciamento(nr);
    }
}