package com.arthurhenrique_Dev.CatOng.Infraestructure.Persistence.ImplementsRepositories.AnimalImplements.ImplCachorro;

import com.arthurhenrique_Dev.CatOng.DadosParaTeste.DadosAnimal.DadosParaTesteValidoAnimal;
import com.arthurhenrique_Dev.CatOng.Domain.Animal.BaseAnimal.Atividade;
import com.arthurhenrique_Dev.CatOng.Domain.Animal.Cachorros.Cachorro;
import com.arthurhenrique_Dev.CatOng.Infraestructure.InfraMappers.AnimalMappers.CachorroMapper.CachorroMapper;
import com.arthurhenrique_Dev.CatOng.Infraestructure.Persistence.Entities.AnimalEntities.ECachorro;
import com.arthurhenrique_Dev.CatOng.Infraestructure.Persistence.FrameworkRepository.AnimalISpring.RepositorioEstrangeiroCachorro.ISpringCachorro;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.PageRequest;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.assertThat;

import static org.junit.jupiter.api.Assertions.*;

class CachorroUseCaseImplTest {

    ISpringCachorro fRepository;
    CachorroMapper mapper;
    CachorroUseCaseImpl cachorroUseCase;
    DadosParaTesteValidoAnimal dtv;

    @BeforeEach
    void setUp() {
        mapper = new CachorroMapper();
        fRepository = Mockito.mock(ISpringCachorro.class);
        cachorroUseCase = new CachorroUseCaseImpl(fRepository, mapper);
        dtv = new DadosParaTesteValidoAnimal();
    }
    @Test
    void salvarCachorro() {
        var cachorroValido = dtv.cadastroAnimalValidoCachorro();

        ArgumentCaptor<ECachorro> captor = ArgumentCaptor.forClass(ECachorro.class);

        cachorroUseCase.salvarCachorro(cachorroValido);

        verify(fRepository, times(1)).save(captor.capture());

        ECachorro salvo = captor.getValue();

        assertEquals(cachorroValido.nome(), salvo.getNome());
        assertEquals(cachorroValido.idade(), salvo.getIdade());
        assertEquals(cachorroValido.raca(), salvo.getRaca());
    }

    @Test
    void deletarCachorro() {

        var id = 1L;
        var retorno = mapper.toEntity(dtv.cachorroValido());

        when(fRepository.findById(id)).thenReturn(Optional.of(retorno));

        cachorroUseCase.deletarCachorro(id);

        verify(fRepository, times(1)).save(retorno);
    }

    @Test
    void adotarCachorro() {

        var id = 1L;
        var retorno = mapper.toEntity(dtv.cachorroValido());

        when(fRepository.findById(id)).thenReturn(Optional.of(retorno));

        cachorroUseCase.adotarCachorro(id);

        verify(fRepository, times(1)).save(retorno);
    }

    @Test
    void alterarCachorro() {

        var id = 1L;
        var atualizacao = dtv.atualizacaoAnimaislValida();
        var retorno = mapper.toEntity(dtv.cachorroValido());

        when(fRepository.findById(id)).thenReturn(Optional.of(retorno));

        cachorroUseCase.alterarCachorro(id, atualizacao);

        ArgumentCaptor<ECachorro> captor = ArgumentCaptor.forClass(ECachorro.class);

        verify(fRepository, times(1)).save(captor.capture());

        ECachorro salvo = captor.getValue();

        verify(fRepository).save(salvo);
    }

    @Test
    void getCachorros() {

        var retornoEsperado = dtv.cachorroValido();
        List<ECachorro> lista = List.of(mapper.toEntity(retornoEsperado));

        when(fRepository.findAllByAtividade(Atividade.ATIVO, PageRequest.of(0, 10)))
                .thenReturn(lista);

        List<Cachorro> retorno = cachorroUseCase.getCachorros(0, 10);

        verify(fRepository, times(1)).findAllByAtividade(Atividade.ATIVO, PageRequest.of(0, 10));
        assertThat(lista).isNotEmpty();
        assertThat(retorno.get(0).getNome()).isEqualTo(retornoEsperado.getNome());
    }

    @Test
    void getCachorrosInativos() {

        var retorno = dtv.cachorroValido();
        List<ECachorro> lista = List.of(mapper.toEntity(retorno));

        when(fRepository.findAllByAtividade(Atividade.INATIVO, PageRequest.of(0, 10)))
                .thenReturn(lista);

        List<Cachorro> retornoEsperado = cachorroUseCase.getCachorrosInativos(0, 10);

        verify(fRepository, times(1)).findAllByAtividade(Atividade.INATIVO, PageRequest.of(0, 10));
        assertThat(lista).isNotEmpty();
        assertThat(retornoEsperado.get(0).getNome()).isEqualTo(retorno.getNome());
    }

    @Test
    void getCachorrosAdotados() {

        var retorno = dtv.cachorroValido();
        List<ECachorro> lista = List.of(mapper.toEntity(retorno));

        when(fRepository.findAllByAtividade(Atividade.ADOTADO, PageRequest.of(0, 10)))
                .thenReturn(lista);

        List<Cachorro> retornoEsperado = cachorroUseCase.getCachorrosAdotados(0, 10);

        verify(fRepository, times(1)).findAllByAtividade(Atividade.ADOTADO, PageRequest.of(0, 10));
        assertThat(lista).isNotEmpty();
        assertThat(retornoEsperado.get(0).getNome()).isEqualTo(retorno.getNome());
    }

    @Test
    void getCachorrosByName() {

        var nome = dtv.cachorroValido().getNome();
        var cachorro = dtv.cachorroValido();
        List<ECachorro> lista = List.of(mapper.toEntity(cachorro));

        when(fRepository.findByNome(nome, PageRequest.of(0, 10)))
                .thenReturn(lista);

        List<Cachorro> retorno = cachorroUseCase.getCachorrosByName(0,10,nome);

        verify(fRepository, times(1)).findByNome(nome, PageRequest.of(0, 10));
        assertThat(retorno).isNotEmpty();
        assertThat(lista.get(0).getNome()).isEqualTo(retorno.get(0).getNome());
    }

    @Test
    void getCachorroById() {

        var id = 1L;
        var cachorro = dtv.cachorroValido();
        var entity = mapper.toEntity(cachorro);

        when(fRepository.findById(id)).thenReturn(Optional.of(entity));

        var retorno = cachorroUseCase.getCachorroById(id);

        verify(fRepository, times(1)).findById(id);
        assertThat(retorno.get().getNome()).isEqualTo(cachorro.getNome());
    }
}