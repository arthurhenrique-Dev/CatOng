package com.arthurhenrique_Dev.CatOng.Infraestructure.Persistence.ImplementsRepositories.AnimalImplements.ImplGato;

import com.arthurhenrique_Dev.CatOng.Application.DTOs.Animais.DTOAtualizacaoAnimais;
import com.arthurhenrique_Dev.CatOng.Domain.Animal.BaseAnimal.Atividade;
import com.arthurhenrique_Dev.CatOng.Domain.Animal.Gatos.Gato;
import com.arthurhenrique_Dev.CatOng.Domain.Animal.Repositorys.GatoRepo.GatoRepo;
import com.arthurhenrique_Dev.CatOng.Infraestructure.InfraMappers.AnimalMappers.GatoMapper.GatoMapper;
import com.arthurhenrique_Dev.CatOng.Infraestructure.Persistence.Entities.AnimalEntities.EGato;
import com.arthurhenrique_Dev.CatOng.Infraestructure.Persistence.FrameworkRepository.AnimalISpring.RepositoriEstrangeiroGato.ISpringGato;
import org.hibernate.ObjectNotFoundException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public class GatoUseCaseImpl implements GatoRepo {

    private final ISpringGato fRepository;
    private final GatoMapper mapper;

    public GatoUseCaseImpl(ISpringGato fRepository, GatoMapper mapper) {
        this.fRepository = fRepository;
        this.mapper = mapper;
    }

    @Override
    public void salvarGato(Gato gato) {
        fRepository.save(mapper.toEntity(gato));
    }

    @Override
    public void deletarGato(Long id) {
            EGato gatoDeletado = fRepository
                    .findById(id).orElseThrow(() -> new IllegalArgumentException("gato não encontrado"));
        if (gatoDeletado != null) {
            gatoDeletado.setAtividade(Atividade.INATIVO);
        } else {
            throw new IllegalArgumentException("gato não encontrado");
        }
    }


    @Override
    public void adotarGato(Long id) {
        EGato gatoAdotado = fRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("gato não encontrado"));
        gatoAdotado.setAtividade(Atividade.ADOTADO);
    }

    @Override
    public void alterarGato(Long id, DTOAtualizacaoAnimais dto) {
        EGato gatoAlterado = fRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("gato não encontrado"));
        if (gatoAlterado != null) {
            if (dto != null) {
                if (!(dto.fotos().isEmpty())) {
                    gatoAlterado.setFotos(dto.fotos());
                }
                if (!(dto.descricao().isEmpty())) {
                    gatoAlterado.setDescricao(dto.descricao());
                }
                if (dto.peso() != 0 && dto.peso() > 0){
                    gatoAlterado.setPeso(dto.peso());
                }
                if (dto.idade() != 0 && dto.idade() > 0 && dto.idade() > gatoAlterado.getIdade()) {
                    gatoAlterado.setIdade(dto.idade());
                }
                fRepository.save(gatoAlterado);
            } else  {
                throw new IllegalArgumentException("Insira os dados de atualização");
            }
        }
    }

    @Override
    public List<Gato> getGato(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        return fRepository.findAll(pageable)
                .stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public List<Gato> getGatoByName(Integer page, Integer size, String nome) {
        if (nome == null || nome.isEmpty()) {
            new IllegalArgumentException("Insira o nome do gato para pesquisa");
        }
        return fRepository.getGatoByNome(nome, PageRequest.of(page, size))
                .stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public Optional<Gato> getGatoById(Long id) {
        if (id == null) {
            new IllegalArgumentException("Insira o id do gato para pesquisa");
        }
        Optional<Gato> retorno = fRepository.findById(id)
                .map(mapper::toDomain);
        if (retorno.isPresent()) {
            return retorno;
        }
        else {
            throw new IllegalArgumentException("gato não encontrado");
        }
    }
}
