package com.arthurhenrique_Dev.CatOng.Controllers.Animal;

import com.arthurhenrique_Dev.CatOng.Application.UseCaseAnimal.UCachorroUseCase.CachorroUseCase;
import com.arthurhenrique_Dev.CatOng.Application.UseCaseAnimal.UGatoUseCase.GatoUseCase;
import com.arthurhenrique_Dev.CatOng.DadosParaTeste.DadosAnimal.DadosParaTesteValidoAnimal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.assertThat;

class ControllerAnimaisTest {

    @Mock
    GatoUseCase gatoUseCase;

    @Mock
    CachorroUseCase cachorroUseCase;

    @InjectMocks
    ControllerAnimais controllerAnimais;

    private DadosParaTesteValidoAnimal dtv;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        dtv = new DadosParaTesteValidoAnimal();
    }
    @Test
    void getGatos() {

        var retornoEsperado = dtv.gatoValido();

        when(gatoUseCase.getGato(0,1)).thenReturn(List.of(retornoEsperado));

        var retorno = controllerAnimais.getGatos(0,1);

        verify(gatoUseCase, times(1)).getGato(0,1);
        assertThat(retorno.get(0)).isEqualTo(retornoEsperado);
    }

    @Test
    void getGatosByNome() {

        var retornoEsperado = dtv.gatoValido();
        var nome = dtv.gatoValido().getNome();

        when(gatoUseCase.getGato(0,1)).thenReturn(List.of(retornoEsperado));

        var retorno = controllerAnimais.getGatos(0,1);

        verify(gatoUseCase, times(1)).getGato(0,1);
        assertThat(retorno.get(0)).isEqualTo(retornoEsperado);
    }

    @Test
    void getGatosById() {

        var id = 1L;
        var retornoEsperado = dtv.gatoValido();

        when(gatoUseCase.getGatoById(id)).thenReturn(Optional.of(retornoEsperado));

        var retorno = controllerAnimais.getGatosById(id);

        verify(gatoUseCase, times(1)).getGatoById(id);
        assertThat(retorno.get()).isEqualTo(retornoEsperado);
    }

    @Test
    void getCachorros() {

        var retornoEsperado = dtv.cachorroValido();

        when(cachorroUseCase.getCachorros(0,1)).thenReturn(List.of(retornoEsperado));

        var retorno = controllerAnimais.getCachorros(0,1);

        verify(cachorroUseCase, times(1)).getCachorros(0,1);
        assertThat(retorno.get(0)).isEqualTo(retornoEsperado);
    }

    @Test
    void getCachorrosByNome() {

        var nome = dtv.cachorroValido().getNome();
        var retornoEsperado = dtv.cachorroValido();

        when(cachorroUseCase.getCachorrosByName(0,1,nome)).thenReturn(List.of(retornoEsperado));

        var retorno = controllerAnimais.getCachorrosByNome(0,1,nome);

        verify(cachorroUseCase, times(1)).getCachorrosByName(0,1,nome);
        assertThat(retorno.get(0)).isEqualTo(retornoEsperado);
    }

    @Test
    void getCachorroById() {

        var id = 1L;
        var retornoEsperado = dtv.cachorroValido();

        when(cachorroUseCase.getCachorroById(id)).thenReturn(Optional.of(retornoEsperado));

        var retorno = controllerAnimais.getCachorroById(id);

        verify(cachorroUseCase, times(1)).getCachorroById(id);
        assertThat(retorno.get()).isEqualTo(retornoEsperado);
    }

    @Test
    void registrarGato() {

        var gatoRegistrado = dtv.gatoValido();

        controllerAnimais.registrarGato(gatoRegistrado);
        verify(gatoUseCase, times(1)).salvarGato(gatoRegistrado);
    }

    @Test
    void registrarCachorros() {

        var cachorroRegistrado = dtv.cachorroValido();

        controllerAnimais.registrarCachorros(cachorroRegistrado);
        verify(cachorroUseCase, times(1)).salvarCachorro(cachorroRegistrado);
    }

    @Test
    void atualizarGatos() {

        var id = 1L;
        var atualizacao = dtv.atualizacaoAnimaislValida();

        controllerAnimais.atualizarGatos(id, atualizacao);
        verify(gatoUseCase, times(1)).alterarGato(id,atualizacao);
    }

    @Test
    void atualizarCachorros() {

        var id = 1L;
        var atualizacao = dtv.atualizacaoAnimaislValida();

        controllerAnimais.atualizarCachorros(id, atualizacao);
        verify(cachorroUseCase, times(1)).alterarCachorro(id,atualizacao);
    }

    @Test
    void deletarGatos() {

        var id = 1L;

        controllerAnimais.deletarGatos(id);
        verify(gatoUseCase, times(1)).deletarGato(id);
    }

    @Test
    void deletarCachorros() {

        var id = 1L;

        controllerAnimais.deletarCachorros(id);
        verify(cachorroUseCase, times(1)).deletarCachorro(id);
    }

    @Test
    void adotarGato() {

        var id = 1L;

        controllerAnimais.adotarGato(id);
        verify(gatoUseCase, times(1)).adotarGato(id);
    }

    @Test
    void adotarCachorro() {

        var id = 1L;

        controllerAnimais.adotarCachorro(id);
        verify(cachorroUseCase, times(1)).adotarCachorro(id);
    }
}