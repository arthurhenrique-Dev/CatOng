package com.arthurhenrique_Dev.CatOng.Application.UseCaseAnimal.UCachorroUseCase;

import com.arthurhenrique_Dev.CatOng.DadosParaTeste.DadosAnimal.DadosParaTesteValidoAnimal;
import com.arthurhenrique_Dev.CatOng.Domain.Animal.Repositorys.CachorroRepo.CachorroRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.assertThat;

class CachorroUseCaseTest {

    @Mock
    CachorroRepo repository;

    @InjectMocks
    CachorroUseCase cachorroUseCase;

    private DadosParaTesteValidoAnimal dtv;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        dtv = new DadosParaTesteValidoAnimal();
    }

    @Test
    void salvarCachorro() {

        var propostaDeCadastro = dtv.cadastroAnimalValidoGato();

        cachorroUseCase.salvarCachorro(propostaDeCadastro);
        verify(repository, times(1)).salvarCachorro(propostaDeCadastro);
    }

    @Test
    void alterarCachorro() {

        var id = 1L;
        var dto = dtv.atualizacaoAnimaislValida();

        cachorroUseCase.alterarCachorro(id, dto);
        verify(repository, times(1)).alterarCachorro(id, dto);
    }

    @Test
    void deletarCachorro() {

        var id = 1L;

        cachorroUseCase.deletarCachorro(id);
        verify(repository, times(1)).deletarCachorro(id);
    }

    @Test
    void adotarCachorro() {

        var id = 1L;

        cachorroUseCase.adotarCachorro(id);
        verify(repository, times(1)).adotarCachorro(id);
    }

    @Test
    void getCachorros() {

        var cachorroRecebido = dtv.cachorroValido();

        when(repository.getCachorros(0, 1)).thenReturn(List.of(cachorroRecebido));

        var retorno = cachorroUseCase.getCachorros(0, 1);
        assertThat(retorno.get(0)).isEqualTo(cachorroRecebido);
        verify(repository, times(1)).getCachorros(0, 1);
    }

    @Test
    void getCachorrosByName() {

        var cachorroRecebido = dtv.cachorroValido();
        var nome = cachorroRecebido.getNome();

        when(repository.getCachorrosByName(0, 1, nome)).thenReturn(List.of(cachorroRecebido));

        var retorno = cachorroUseCase.getCachorrosByName(0, 1, nome);

        assertThat(retorno.get(0)).isEqualTo(cachorroRecebido);
        verify(repository, times(1)).getCachorrosByName(0, 1, nome);
    }

    @Test
    void getCachorroById() {

        var cachorroRecebido = dtv.cachorroValido();
        var id = 1L;

        when(repository.getCachorroById(id)).thenReturn(Optional.of(cachorroRecebido));

        var retorno = cachorroUseCase.getCachorroById(id);

        assertThat(retorno.get()).isEqualTo(cachorroRecebido);
        verify(repository, times(1)).getCachorroById(id);
    }

    @Test
    void getCachorrosInativos() {

        var cachorroRecebido = dtv.cachorroValido();

        when(repository.getCachorrosInativos(0, 1)).thenReturn(List.of(cachorroRecebido));

        var retorno = cachorroUseCase.getCachorrosInativos(0, 1);
        assertThat(retorno.get(0)).isEqualTo(cachorroRecebido);
        verify(repository, times(1)).getCachorrosInativos(0, 1);
    }

    @Test
    void getCachorrosAdotados() {

        var cachorroRecebido = dtv.cachorroValido();

        when(repository.getCachorrosAdotados(0, 1)).thenReturn(List.of(cachorroRecebido));

        var retorno = cachorroUseCase.getCachorrosAdotados(0, 1);
        assertThat(retorno.get(0)).isEqualTo(cachorroRecebido);
        verify(repository, times(1)).getCachorrosAdotados(0, 1);
    }
}