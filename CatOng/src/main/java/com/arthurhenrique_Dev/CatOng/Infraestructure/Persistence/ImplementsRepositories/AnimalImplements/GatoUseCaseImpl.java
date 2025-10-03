package com.arthurhenrique_Dev.CatOng.Infraestructure.Persistence.ImplementsRepositories.AnimalImplements;

import com.arthurhenrique_Dev.CatOng.Domain.Animal.BaseAnimal.Atividade;
import com.arthurhenrique_Dev.CatOng.Domain.Animal.Gatos.Gato;
import com.arthurhenrique_Dev.CatOng.Domain.Animal.Repositorys.GatoRepo.GatoRepo;
import com.arthurhenrique_Dev.CatOng.Infraestructure.InfraMappers.AnimalMappers.GatoMapper;
import com.arthurhenrique_Dev.CatOng.Infraestructure.Persistence.Entities.AnimalEntities.EGato;
import com.arthurhenrique_Dev.CatOng.Infraestructure.Persistence.FrameworkRepository.AnimalISpring.ISpringGato;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

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
        EGato gatoDeletado = fRepository.findById(id).orElse(null);
        gatoDeletado.setAtividade(Atividade.INATIVO);
    }


    @Override
    public void adotarGato(Long id) {
        EGato gatoAdotado = fRepository.findById(id).orElse(null);
        gatoAdotado.setAtividade(Atividade.ADOTADO);
    }

    @Override
    public void alterarGato(Long id, Gato gato) {

    }

    @Override
    public List<Gato> getGato(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return fRepository.findAll(pageable)
                .stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public List<Gato> getGatoByName(int page, int size, String nome) {
        return fRepository.getGatoByNome(nome, PageRequest.of(page, size))
                .stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public Optional<Gato> getGatoById(Long id) {
        return fRepository.findById(id)
                .map(mapper::toDomain);
    }
}
