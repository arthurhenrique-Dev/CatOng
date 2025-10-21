package com.arthurhenrique_Dev.CatOng.Infraestructure.Persistence.ImplementsRepositories.AnimalImplements.ImplGato;

import com.arthurhenrique_Dev.CatOng.Application.DTOs.Animais.DTOAtualizacaoAnimais;
import com.arthurhenrique_Dev.CatOng.Domain.Animal.BaseAnimal.Atividade;
import com.arthurhenrique_Dev.CatOng.Domain.Animal.Gatos.Gato;
import com.arthurhenrique_Dev.CatOng.Domain.Animal.Repositorys.GatoRepo.GatoRepo;
import com.arthurhenrique_Dev.CatOng.Infraestructure.InfraMappers.AnimalMappers.GatoMapper.GatoMapper;
import com.arthurhenrique_Dev.CatOng.Infraestructure.Persistence.Entities.AnimalEntities.EGato;
import com.arthurhenrique_Dev.CatOng.Infraestructure.Persistence.FrameworkRepository.AnimalISpring.RepositoriEstrangeiroGato.ISpringGato;
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
    public void alterarGato(Long id, DTOAtualizacaoAnimais dto) {
        EGato gatoAlterado = fRepository.findById(id).orElse(null);
        if (gatoAlterado != null) {
            if (dto != null) {
                if (dto.fotos() != null) {
                    gatoAlterado.setFotos(dto.fotos());
                }
                if (dto.descricao() != null) {
                    gatoAlterado.setDescrição(dto.descricao());
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
