package com.arthurhenrique_Dev.CatOng.Application.UseCaseAnimal.UCachorroUseCase;

import com.arthurhenrique_Dev.CatOng.DadosParaTeste.DadosAnimal.DadosParaTesteValidoAnimal;
import com.arthurhenrique_Dev.CatOng.Domain.Animal.Cachorros.Cachorro;
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

import static org.junit.jupiter.api.Assertions.*;

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

        var cachorroValido = dtv.cachorroValido();

        cachorroUseCase.salvarCachorro(cachorroValido);
        verify(repository, times(1)).salvarCachorro(cachorroValido);
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

        when(repository.getCachorros(0,1)).thenReturn(List.of(cachorroRecebido));

        var retorno = cachorroUseCase.getCachorros(0,1);
        assertThat(retorno.get(0)).isEqualTo(cachorroRecebido);
        verify(repository, times(1)).getCachorros(0,1);
    }

    @Test
    void getCachorrosByName() {

        var cachorroRecebido = dtv.cachorroValido();
        var nome = cachorroRecebido.getNome();

        when(repository.getCachorrosByName(0,1,nome)).thenReturn(List.of(cachorroRecebido));

        var retorno = cachorroUseCase.getCachorrosByName(0,1,nome);

        assertThat(retorno.get(0)).isEqualTo(cachorroRecebido);
        verify(repository, times(1)).getCachorrosByName(0,1,nome);
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
}