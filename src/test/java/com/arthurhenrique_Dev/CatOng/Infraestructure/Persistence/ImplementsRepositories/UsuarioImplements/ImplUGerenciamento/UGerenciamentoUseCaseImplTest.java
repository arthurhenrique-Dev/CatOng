package com.arthurhenrique_Dev.CatOng.Infraestructure.Persistence.ImplementsRepositories.UsuarioImplements.ImplUGerenciamento;

import com.arthurhenrique_Dev.CatOng.DadosParaTeste.DadosPessoa.DadosParaTesteValido;
import com.arthurhenrique_Dev.CatOng.Domain.Usuarios.Base.Atividade;
import com.arthurhenrique_Dev.CatOng.Infraestructure.InfraMappers.UserMappers.UGerenciamentoMapper.UGerenciamentoMapper;
import com.arthurhenrique_Dev.CatOng.Infraestructure.Persistence.Entities.UsuarioEntities.EUComum.EUComum;
import com.arthurhenrique_Dev.CatOng.Infraestructure.Persistence.Entities.UsuarioEntities.EUGerenciamento.EUGerenciamento;
import com.arthurhenrique_Dev.CatOng.Infraestructure.Persistence.FrameworkRepository.UsuarioISpring.RepositorioEstrangeiroUComum.ISpringUComum;
import com.arthurhenrique_Dev.CatOng.Infraestructure.Persistence.FrameworkRepository.UsuarioISpring.RepositorioEstrangeiroUGerenciamento.ISpringUGerenciamento;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class UGerenciamentoUseCaseImplTest {

    ISpringUGerenciamento fRepository;
    UGerenciamentoMapper mapper;
    UGerenciamentoUseCaseImpl uGerenciamentoUseCaseImpl;
    DadosParaTesteValido dtv;

    @BeforeEach
    void setUp() {
        fRepository = Mockito.mock(ISpringUGerenciamento.class);
        mapper = new UGerenciamentoMapper();
        uGerenciamentoUseCaseImpl = new UGerenciamentoUseCaseImpl(fRepository, mapper);
        dtv = new DadosParaTesteValido();
    }

    @Test
    void salvarUGerenciamento() {

        var cadastro = dtv.registroValidoUgerenciamento();

        ArgumentCaptor<EUGerenciamento> captor = ArgumentCaptor.forClass(EUGerenciamento.class);

        uGerenciamentoUseCaseImpl.salvarUGerenciamento(cadastro);

        verify(fRepository, times(1)).save(captor.capture());

        EUGerenciamento salvo = captor.getValue();

        assertEquals(cadastro.cpf(), salvo.getCpf());
    }

    @Test
    void removerUGerenciamento() {

        var nr = dtv.euGerenciamentoValido().getNR();
        EUGerenciamento retornoEsperado = dtv.euGerenciamentoValido();

        when(fRepository.findByNR(nr)).thenReturn(retornoEsperado);

        uGerenciamentoUseCaseImpl.removerUGerenciamento(nr);

        verify(fRepository, times(1)).findByNR(nr);

        EUGerenciamento salvo = retornoEsperado;

        assertEquals(Atividade.INATIVO, salvo.getAtividade());
    }

    @Test
    void atualizarUGerenciamento() {

        var nr = dtv.euGerenciamentoValido().getNR();
        EUGerenciamento entidadeRetornada = dtv.euGerenciamentoValido();
        var atualizacao = dtv.atualizacaoValidoUgerenciamento();

        ArgumentCaptor<EUGerenciamento> captor = ArgumentCaptor.forClass(EUGerenciamento.class);

        when(fRepository.findByNR(nr)).thenReturn(entidadeRetornada);

        uGerenciamentoUseCaseImpl.atualizarUGerenciamento(nr, atualizacao);

        verify(fRepository, times(1)).findByNR(nr);
        verify(fRepository, times(1)).save(captor.capture());
    }

    @Test
    void getUGerenciamentoByNR() {

        var nr = dtv.euGerenciamentoValido().getNR();
        Optional<EUGerenciamento> retorno = Optional.of(dtv.euGerenciamentoValido());

        when(fRepository.findById(nr)).thenReturn(retorno);

        uGerenciamentoUseCaseImpl.getUGerenciamentoByNR(nr);

        verify(fRepository, times(1)).findByNR(nr);
    }

    @Test
    void getUGerenciamentos() {

        var cpf = dtv.euGerenciamentoValido().getCpf();
        EUGerenciamento retorno = dtv.euGerenciamentoValido();

        when(fRepository.findByCpf(cpf)).thenReturn(retorno);

        uGerenciamentoUseCaseImpl.getUGerenciamento(cpf);

        verify(fRepository, times(1)).findByCpf(cpf);
    }

    @Test
    void getUGerenciamentosInativos() {

        List<EUGerenciamento> retorno = List.of(dtv.euGerenciamentoValido());

        when(fRepository.findAllByAtividade(Atividade.INATIVO, PageRequest.of(0,10)))
                .thenReturn(retorno);

        uGerenciamentoUseCaseImpl.getUGerenciamentosInativos(0,10);

        verify(fRepository, times(1)).findAllByAtividade(Atividade.INATIVO, PageRequest.of(0,10));
    }

    @Test
    void getUGerenciamento() {

        List<EUGerenciamento> retorno = List.of(dtv.euGerenciamentoValido());

        when(fRepository.findAllByAtividade(Atividade.ATIVO, PageRequest.of(0,10)))
                .thenReturn(retorno);

        uGerenciamentoUseCaseImpl.getUGerenciamentos(0,10);

        verify(fRepository, times(1)).findAllByAtividade(Atividade.ATIVO, PageRequest.of(0,10));
    }
}