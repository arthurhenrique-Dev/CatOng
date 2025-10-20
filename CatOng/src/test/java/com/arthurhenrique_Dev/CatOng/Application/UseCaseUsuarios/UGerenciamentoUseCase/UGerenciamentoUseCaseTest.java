package com.arthurhenrique_Dev.CatOng.Application.UseCaseUsuarios.UGerenciamentoUseCase;


import com.arthurhenrique_Dev.CatOng.Application.DTOs.Usuarios.Retorno.DTORetornoUGerenciamento;
import com.arthurhenrique_Dev.CatOng.Domain.Usuarios.Repositorys.UGerenciamentoRepository.UGerenciamentoRepository;
import com.arthurhenrique_Dev.CatOng.Infraestructure.InfraMappers.UserMappers.UGerenciamentoMapper.UGerenciamentoMapper;
import com.arthurhenrique_Dev.CatOng.Secreto.DadosParaTesteValido;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;


class UGerenciamentoUseCaseTest {

    @Mock
    UGerenciamentoRepository repository;

    @Mock
    UGerenciamentoMapper mapper;

    @InjectMocks
    UGerenciamentoUseCase ugerenciamentoUseCase;

    private DadosParaTesteValido dtv;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        dtv = new DadosParaTesteValido();
    }

    @Test
    void salvarUGerenciamento() {

        var dtoSalvo = dtv.registroValidoUgerenciamento();
        ugerenciamentoUseCase.salvarUGerenciamento(dtoSalvo);

        verify(repository, times(1)).salvarUGerenciamento(dtoSalvo);
    }

    @Test
    void removerUGerenciamento() {

        Long nr = 1L;

        ugerenciamentoUseCase.removerUGerenciamento(nr);

        verify(repository, times(1)).removerUGerenciamento(nr);
    }

    @Test
    void atualizarUGerenciamento() {

        var dto = dtv.atualizacaoValidoUgerenciamento();

        ugerenciamentoUseCase.atualizarUGerenciamento(1L, dto);
    }

    @Test
    void getUGerenciamento() {

        var dto = dtv.RetornoValidoUgerenciamento();

        when(repository.getUGerenciamento(dto.cpf())).thenReturn(Optional.of(dto));
        Optional<DTORetornoUGerenciamento> resultado = ugerenciamentoUseCase.getUGerenciamento(dto.cpf());

        assertThat(resultado).isNotEmpty();
        assertThat(resultado.get().cpf()).isEqualTo(dto.cpf());
    }

    @Test
    void getUGerenciamentoByNR() {

        var dto = dtv.RetornoValidoUgerenciamento();

        when(repository.getUGerenciamentoByNR(1L)).thenReturn(Optional.of(dto));

        Optional<DTORetornoUGerenciamento> retorno = ugerenciamentoUseCase.getUGerenciamentoByNR(1L);

        assertThat(retorno).isNotEmpty();
        assertThat(retorno.get().cpf()).isEqualTo(dto.cpf());
    }

    @Test
    void getUGerenciamentos() {

        var dto = dtv.RetornoValidoUgerenciamento();

        when(repository.getUGerenciamentos(0, 1)).thenReturn(List.of(dto));

        List<DTORetornoUGerenciamento> resultado = ugerenciamentoUseCase.getUGerenciamentos(0, 1);

        assertThat(resultado).isNotEmpty();

        System.out.println(resultado);
    }
}