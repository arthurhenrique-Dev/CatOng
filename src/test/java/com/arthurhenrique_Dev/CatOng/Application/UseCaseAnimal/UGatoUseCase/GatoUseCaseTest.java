package com.arthurhenrique_Dev.CatOng.Application.UseCaseAnimal.UGatoUseCase;

import com.arthurhenrique_Dev.CatOng.DadosParaTeste.DadosAnimal.DadosParaTesteValidoAnimal;
import com.arthurhenrique_Dev.CatOng.Domain.Animal.Repositorys.GatoRepo.GatoRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.assertThat;

class GatoUseCaseTest {

    @Mock
    GatoRepo repository;

    @InjectMocks
    GatoUseCase gatoUseCase;

    private DadosParaTesteValidoAnimal dtv;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        dtv = new DadosParaTesteValidoAnimal();
    }

    @Test
    void salvarGato() {

        var gatoRecebido = dtv.cadastroAnimalValidoGato();

        gatoUseCase.salvarGato(gatoRecebido);
        verify(repository, times(1)).salvarGato(gatoRecebido);
    }

    @Test
    void deletarGato() {

        var id = 1L;
        gatoUseCase.deletarGato(id);

        verify(repository, times(1)).deletarGato(id);
    }

    @Test
    void adotarGato() {

        var id = 1L;

        gatoUseCase.adotarGato(id);
        verify(repository, times(1)).adotarGato(id);
    }

    @Test
    void alterarGato() {

        var id = 1L;
        var dto = dtv.atualizacaoAnimaislValida();

        gatoUseCase.alterarGato(id, dto);
        verify(repository, times(1)).alterarGato(id, dto);
    }

    @Test
    void getGatos() {

        var gatoRecebido = dtv.gatoValido();

        when(repository.getGatos(0, 1)).thenReturn(List.of(gatoRecebido));

        var retorno = gatoUseCase.getGatos(0, 1);

        verify(repository, times(1)).getGatos(0, 1);
        assertThat(List.of(gatoRecebido)).isEqualTo(retorno);
    }

    @Test
    void getGatoByName() {

        var nome = dtv.gatoValido().getNome();
        var gatosRetornados = dtv.gatoValido();

        when(repository.getGatoByName(0, 1, nome)).thenReturn(List.of(gatosRetornados));

        var retorno = gatoUseCase.getGatoByName(0, 1, nome);

        verify(repository, times(1)).getGatoByName(0, 1, nome);
        assertThat(List.of(gatosRetornados)).isEqualTo(retorno);

    }

    @Test
    void getGatoById() {

        var id = 1L;
        var gatoRecebido = dtv.gatoValido();

        when(repository.getGatoById(id)).thenReturn(Optional.of(gatoRecebido));

        var retorno = gatoUseCase.getGatoById(id);

        verify(repository, times(1)).getGatoById(id);
        assertThat(gatoRecebido).isEqualTo(retorno.get());
    }

    @Test
    void getGatosAdotados() {

        var gatoRecebido = dtv.gatoValido();

        when(repository.getGatosAdotados(0, 1)).thenReturn(List.of(gatoRecebido));

        var retorno = gatoUseCase.getGatosAdotados(0, 1);

        verify(repository, times(1)).getGatosAdotados(0, 1);
        assertThat(List.of(gatoRecebido)).isEqualTo(retorno);
    }

    @Test
    void getGatosInativos() {

        var gatoRecebido = dtv.gatoValido();

        when(repository.getGatosInativos(0, 1)).thenReturn(List.of(gatoRecebido));

        var retorno = gatoUseCase.getGatosInativos(0, 1);

        verify(repository, times(1)).getGatosInativos(0, 1);
        assertThat(List.of(gatoRecebido)).isEqualTo(retorno);
    }
}