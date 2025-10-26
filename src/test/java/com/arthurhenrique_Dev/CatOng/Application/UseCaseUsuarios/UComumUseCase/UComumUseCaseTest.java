package com.arthurhenrique_Dev.CatOng.Application.UseCaseUsuarios.UComumUseCase;

import com.arthurhenrique_Dev.CatOng.Application.DTOs.Usuarios.Retorno.DTORetornoUComum;
import com.arthurhenrique_Dev.CatOng.Domain.Usuarios.Repositorys.UComumRepository.UComumRepository;
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


class UComumUseCaseTest {

    @Mock
    UComumRepository repository;

    @InjectMocks
    UComumUseCase uComumUseCase;

    private DadosParaTesteValido dtv;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        dtv = new DadosParaTesteValido();
    }

    @Test
    void salvarComum() {

        var dto = dtv.registroValidoUComum();

        uComumUseCase.salvarComum(dto);
        verify(repository, times(1)).salvarUComum(dto);
    }

    @Test
    void atualizarComum() {

        var dto = dtv.atualizacaoValidoUComum();
        var cpf = dtv.registroValidoUComum().cpf();

        uComumUseCase.atualizarComum(cpf, dto);
        verify(repository, times(1)).atualizarUComum(cpf, dto);
    }

    @Test
    void removerComum() {
        var cpf = dtv.registroValidoUComum().cpf();

        uComumUseCase.removerComum(cpf);
        verify(repository, times(1)).removerUComum(cpf);
    }

    @Test
    void getUComum() {
        var retorno = dtv.retornoValidoUComum();
        var cpf = dtv.registroValidoUComum().cpf();

        when(repository.getUComum(cpf)).thenReturn(Optional.of(retorno));

        var verificacao = uComumUseCase.getUComum(cpf);

        assertThat(verificacao.get()).isEqualTo(retorno);
        verify(repository, times(1)).getUComum(cpf);
    }

    @Test
    void getUComuns() {
        var retorno = dtv.retornoValidoUComum();

        when(repository.getUComuns(0, 1)).thenReturn(List.of(retorno));

        List<DTORetornoUComum> lista = uComumUseCase.getUComuns(0, 1);

        verify(repository, times(1)).getUComuns(0, 1);
        assertThat(lista.get(0)).isEqualTo(retorno);
    }

    @Test
    void getUComunsByName() {
        var retorno = dtv.retornoValidoUComum();

        when(repository.getUComumsByName(0, 1, retorno.nome())).thenReturn(List.of(retorno));

        List<DTORetornoUComum> lista = uComumUseCase.getUComunsByName(0, 1, retorno.nome());

        verify(repository, times(1)).getUComumsByName(0, 1, retorno.nome());
        assertThat(lista.get(0)).isEqualTo(retorno);
    }

    @Test
    void getUComunsInativos() {

        var retorno = dtv.retornoValidoUComum();

        when(repository.getUComunsInativos(0, 1)).thenReturn(List.of(retorno));

        List<DTORetornoUComum> lista = uComumUseCase.getUComunsInativos(0, 1);

        verify(repository, times(1)).getUComunsInativos(0, 1);
        assertThat(lista.get(0)).isEqualTo(retorno);
    }
}