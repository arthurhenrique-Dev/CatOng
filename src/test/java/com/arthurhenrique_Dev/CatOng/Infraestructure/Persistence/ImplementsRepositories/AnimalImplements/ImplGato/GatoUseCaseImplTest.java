package com.arthurhenrique_Dev.CatOng.Infraestructure.Persistence.ImplementsRepositories.AnimalImplements.ImplGato;

import com.arthurhenrique_Dev.CatOng.DadosParaTeste.DadosAnimal.DadosParaTesteValidoAnimal;
import com.arthurhenrique_Dev.CatOng.Domain.Animal.BaseAnimal.Atividade;
import com.arthurhenrique_Dev.CatOng.Domain.Animal.Cachorros.Cachorro;
import com.arthurhenrique_Dev.CatOng.Domain.Animal.Gatos.Gato;
import com.arthurhenrique_Dev.CatOng.Infraestructure.InfraMappers.AnimalMappers.GatoMapper.GatoMapper;
import com.arthurhenrique_Dev.CatOng.Infraestructure.Persistence.Entities.AnimalEntities.ECachorro;
import com.arthurhenrique_Dev.CatOng.Infraestructure.Persistence.Entities.AnimalEntities.EGato;
import com.arthurhenrique_Dev.CatOng.Infraestructure.Persistence.FrameworkRepository.AnimalISpring.RepositoriEstrangeiroGato.ISpringGato;
import com.arthurhenrique_Dev.CatOng.Infraestructure.Persistence.ImplementsRepositories.AnimalImplements.ImplCachorro.CachorroUseCaseImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import com.arthurhenrique_Dev.CatOng.Infraestructure.Persistence.Entities.AnimalEntities.EGato;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GatoUseCaseImplTest {

    @Mock
    ISpringGato fRepository;

    @Mock
    GatoMapper mapper;

    @InjectMocks
    GatoUseCaseImpl gatoUseCaseImpl;

    DadosParaTesteValidoAnimal dtv;

    @BeforeEach
    void setUp() {
        fRepository = Mockito.mock(ISpringGato.class);
        mapper = new GatoMapper();
        gatoUseCaseImpl = new GatoUseCaseImpl(fRepository, mapper);
        dtv = new DadosParaTesteValidoAnimal();
    }

    @Test
    void salvarGato() {

        var gatoValido = dtv.cadastroAnimalValidoGato();

        ArgumentCaptor<EGato> captor = ArgumentCaptor.forClass(EGato.class);

        gatoUseCaseImpl.salvarGato(gatoValido);

        verify(fRepository, times(1)).save(captor.capture());

        EGato salvo = captor.getValue();

        assertEquals(gatoValido.nome(), salvo.getNome());
        assertEquals(gatoValido.idade(), salvo.getIdade());
        assertEquals(gatoValido.raca(), salvo.getRaca());
    }

    @Test
    void deletarGato() {

        var id = 1L;
        var retorno = mapper.toEntity(dtv.gatoValido());

        when(fRepository.findById(id)).thenReturn(Optional.of(retorno));

        gatoUseCaseImpl.deletarGato(id);

        verify(fRepository, times(1)).save(retorno);
    }

    @Test
    void adotarGato() {

        var id = 1L;
        var retorno = mapper.toEntity(dtv.gatoValido());

        when(fRepository.findById(id)).thenReturn(Optional.of(retorno));

        gatoUseCaseImpl.adotarGato(id);

        verify(fRepository, times(1)).save(retorno);
    }

    @Test
    void alterarGato() {

        var id = 1L;
        var alterando = mapper.toEntity(dtv.gatoValido());
        var alteracao = dtv.atualizacaoAnimaislValida();

        ArgumentCaptor<EGato> captor = ArgumentCaptor.forClass(EGato.class);

        when(fRepository.findById(id)).thenReturn(Optional.of(alterando));

        gatoUseCaseImpl.alterarGato(id, alteracao);

        verify(fRepository, times(1)).save(captor.capture());
    }

    @Test
    void getGatos() {

        var retornoEsperado = dtv.gatoValido();
        List<EGato> lista = List.of(mapper.toEntity(retornoEsperado));

        when(fRepository.findAllByAtividade(Atividade.ATIVO, PageRequest.of(0, 10)))
                .thenReturn(lista);

        List<Gato> retorno = gatoUseCaseImpl.getGatos(0, 10);

        verify(fRepository, times(1)).findAllByAtividade(Atividade.ATIVO, PageRequest.of(0, 10));
        assertThat(lista).isNotEmpty();
        assertThat(retorno.get(0).getNome()).isEqualTo(retornoEsperado.getNome());
    }

    @Test
    void getGatosAdotados() {

        var retornoEsperado = dtv.gatoValido();
        List<EGato> lista = List.of(mapper.toEntity(retornoEsperado));

        when(fRepository.findAllByAtividade(Atividade.ADOTADO, PageRequest.of(0, 10)))
                .thenReturn(lista);

        List<Gato> retorno = gatoUseCaseImpl.getGatosAdotados(0, 10);

        verify(fRepository, times(1)).findAllByAtividade(Atividade.ADOTADO, PageRequest.of(0, 10));
        assertThat(lista).isNotEmpty();
        assertThat(retorno.get(0).getNome()).isEqualTo(retornoEsperado.getNome());
    }

    @Test
    void getGatosInativos() {

        var retornoEsperado = dtv.gatoValido();
        List<EGato> lista = List.of(mapper.toEntity(retornoEsperado));

        when(fRepository.findAllByAtividade(Atividade.INATIVO, PageRequest.of(0, 10)))
                .thenReturn(lista);

        List<Gato> retorno = gatoUseCaseImpl.getGatosInativos(0, 10);

        verify(fRepository, times(1)).findAllByAtividade(Atividade.INATIVO, PageRequest.of(0, 10));
        assertThat(lista).isNotEmpty();
        assertThat(retorno.get(0).getNome()).isEqualTo(retornoEsperado.getNome());
    }

    @Test
    void getGatoByName() {

        var nome = dtv.gatoValido().getNome();
        var gato = dtv.gatoValido();
        List<EGato> lista = List.of(mapper.toEntity(gato));

        when(fRepository.getGatoByNome(nome, PageRequest.of(0, 10)))
                .thenReturn(lista);

        List<Gato> retorno = gatoUseCaseImpl.getGatoByName(0,10,nome);

        verify(fRepository, times(1)).getGatoByNome(nome, PageRequest.of(0, 10));
        assertThat(retorno).isNotEmpty();
        assertThat(lista.get(0).getNome()).isEqualTo(retorno.get(0).getNome());
    }

    @Test
    void getGatoById() {

        var id = 1L;
        var gato = dtv.gatoValido();
        var entity = mapper.toEntity(gato);

        when(fRepository.findById(id)).thenReturn(Optional.of(entity));

        var retorno = gatoUseCaseImpl.getGatoById(id);

        verify(fRepository, times(1)).findById(id);
        assertThat(retorno.get().getNome()).isEqualTo(gato.getNome());
    }
}