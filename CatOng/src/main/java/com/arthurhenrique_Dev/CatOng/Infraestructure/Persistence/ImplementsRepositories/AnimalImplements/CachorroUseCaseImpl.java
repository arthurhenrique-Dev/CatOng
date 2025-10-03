package com.arthurhenrique_Dev.CatOng.Infraestructure.Persistence.ImplementsRepositories.AnimalImplements;

import com.arthurhenrique_Dev.CatOng.Domain.Animal.BaseAnimal.Atividade;
import com.arthurhenrique_Dev.CatOng.Domain.Animal.Cachorros.Cachorro;
import com.arthurhenrique_Dev.CatOng.Domain.Animal.Repositorys.CachorroRepo.CachorroRepo;
import com.arthurhenrique_Dev.CatOng.Infraestructure.InfraMappers.AnimalMappers.CachorroMapper;
import com.arthurhenrique_Dev.CatOng.Infraestructure.Persistence.Entities.AnimalEntities.ECachorro;
import com.arthurhenrique_Dev.CatOng.Infraestructure.Persistence.FrameworkRepository.AnimalISpring.ISpringCachorro;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CachorroUseCaseImpl implements CachorroRepo {

    private final ISpringCachorro fRepository;
    private final CachorroMapper mapper;

    public CachorroUseCaseImpl(ISpringCachorro fRepository, CachorroMapper mapper) {
        this.fRepository = fRepository;
        this.mapper = mapper;
    }


    @Override
    public void salvarCachorro(Cachorro cachorro) {
        cachorro.setAtividade(Atividade.ATIVO);
        fRepository.save(mapper.toEntity(cachorro));
    }

    @Override
    public void deletarCachorro(Long id) {
        ECachorro cachorroDeletado = fRepository.findById(id).orElse(null);
        cachorroDeletado.setAtividade(Atividade.INATIVO);
    }
    @Override
    public void adotarCachorro(Long id) {
        ECachorro eCachorro = fRepository.findById(id).orElse(null);
        eCachorro.setAtividade(Atividade.ADOTADO);
    }

    @Override
    public void alterarCachorro(Long id, Cachorro cachorroAlterado) {
    }

    @Override
    public List<Cachorro> getCachorros(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return fRepository.findAll(pageable)
                .stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public List<Cachorro> getCachorrosByName(int page, int size, String nome) {
        return fRepository.findByNome(nome, PageRequest.of(page, size))
                .stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public Optional<Cachorro> getCachorroById(Long id) {
        return fRepository.findById(id)
                .map(mapper::toDomain);
    }
}
