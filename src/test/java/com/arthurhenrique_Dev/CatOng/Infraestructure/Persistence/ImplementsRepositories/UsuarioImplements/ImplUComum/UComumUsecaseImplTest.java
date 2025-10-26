package com.arthurhenrique_Dev.CatOng.Infraestructure.Persistence.ImplementsRepositories.UsuarioImplements.ImplUComum;

import com.arthurhenrique_Dev.CatOng.Application.DTOs.Usuarios.Retorno.DTORetornoUComum;
import com.arthurhenrique_Dev.CatOng.DadosParaTeste.DadosPessoa.DadosParaTesteValido;
import com.arthurhenrique_Dev.CatOng.Domain.Usuarios.Base.Atividade;
import com.arthurhenrique_Dev.CatOng.Infraestructure.InfraMappers.UserMappers.UComumMappers.UComumMapper;
import com.arthurhenrique_Dev.CatOng.Infraestructure.Persistence.Entities.UsuarioEntities.EUComum.EUComum;
import com.arthurhenrique_Dev.CatOng.Infraestructure.Persistence.FrameworkRepository.UsuarioISpring.RepositorioEstrangeiroUComum.ISpringUComum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.assertThat;

import static org.junit.jupiter.api.Assertions.*;

class UComumUsecaseImplTest {

    ISpringUComum fRepository;
    UComumMapper mapper;
    UComumUsecaseImpl uComumUsecaseImpl;
    DadosParaTesteValido dtv;

    @BeforeEach
    void setUp() {

        fRepository = Mockito.mock(ISpringUComum.class);
        mapper = new UComumMapper();
        uComumUsecaseImpl = new UComumUsecaseImpl(fRepository, mapper);
        dtv = new DadosParaTesteValido();
    }


    @Test
    void salvarUComum() {

        var cadastro = dtv.registroValidoUComum();

        ArgumentCaptor<EUComum> captor = ArgumentCaptor.forClass(EUComum.class);

        uComumUsecaseImpl.salvarUComum(cadastro);

        verify(fRepository, times(1)).save(captor.capture());

        EUComum salvo = captor.getValue();

        assertEquals(cadastro.cpf(), salvo.getCpf());
    }

    @Test
    void removerUComum() {

        var id = dtv.euComumValido().getCpf();
        EUComum retornoEsperado = dtv.euComumValido();

        when(fRepository.findById(id)).thenReturn(Optional.of(retornoEsperado));

        uComumUsecaseImpl.removerUComum(id);

        verify(fRepository, times(1)).findById(id);

        EUComum salvo = retornoEsperado;

        assertEquals(Atividade.INATIVO, salvo.getAtividade());
    }

    @Test
    void atualizarUComum() {

        var id = dtv.euComumValido().getCpf();
        EUComum entidadeRetornada = dtv.euComumValido();
        var atualizacao = dtv.atualizacaoValidoUComum();

        ArgumentCaptor<EUComum> captor = ArgumentCaptor.forClass(EUComum.class);

        when(fRepository.findById(id)).thenReturn(Optional.of(entidadeRetornada));

        uComumUsecaseImpl.atualizarUComum(id, atualizacao);

        verify(fRepository, times(1)).findById(id);
        verify(fRepository, times(1)).save(captor.capture());
    }

    @Test
    void getUComum() {

        var id = dtv.euComumValido().getCpf();
        Optional<EUComum> retorno = Optional.of(dtv.euComumValido());

        when(fRepository.findById(id)).thenReturn(retorno);

        Optional<DTORetornoUComum> resultado = uComumUsecaseImpl.getUComum(id);

        verify(fRepository, times(1)).findById(id);
        assertEquals(resultado.get().nome(), retorno.get().getNome());
    }

    @Test
    void getUComuns() {

        List<EUComum> retorno = List.of(dtv.euComumValido());

        when(fRepository.findAllByAtividade(Atividade.ATIVO, PageRequest.of(0, 10)))
                .thenReturn(retorno);

        uComumUsecaseImpl.getUComuns(0, 10);

        verify(fRepository, times(1)).findAllByAtividade(Atividade.ATIVO, PageRequest.of(0, 10));
    }

    @Test
    void getUComunsInativos() {

        List<EUComum> retorno = List.of(dtv.euComumValido());

        when(fRepository.findAllByAtividade(Atividade.INATIVO, PageRequest.of(0, 10)))
                .thenReturn(retorno);

        uComumUsecaseImpl.getUComunsInativos(0, 10);

        verify(fRepository, times(1)).findAllByAtividade(Atividade.INATIVO, PageRequest.of(0, 10));
    }

    @Test
    void getUComumsByName() {

        var nome = dtv.euComumValido().getNome();
        List<EUComum> retorno = List.of(dtv.euComumValido());

        when(fRepository.getEuComumsByNome(nome, PageRequest.of(0, 10))).thenReturn(retorno);

        uComumUsecaseImpl.getUComumsByName(0, 10, nome);

        verify(fRepository, times(1)).getEuComumsByNome(nome, PageRequest.of(0, 10));
    }
}