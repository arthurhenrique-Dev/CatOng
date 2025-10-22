package com.arthurhenrique_Dev.CatOng.Infraestructure.Persistence.ImplementsRepositories.AnimalImplements.ImplCachorro;

import com.arthurhenrique_Dev.CatOng.Application.DTOs.Animais.DTOAtualizacaoAnimais;
import com.arthurhenrique_Dev.CatOng.Domain.Animal.BaseAnimal.Atividade;
import com.arthurhenrique_Dev.CatOng.Domain.Animal.Cachorros.Cachorro;
import com.arthurhenrique_Dev.CatOng.Domain.Animal.Gatos.Gato;
import com.arthurhenrique_Dev.CatOng.Domain.Animal.Repositorys.CachorroRepo.CachorroRepo;
import com.arthurhenrique_Dev.CatOng.Infraestructure.InfraMappers.AnimalMappers.CachorroMapper.CachorroMapper;
import com.arthurhenrique_Dev.CatOng.Infraestructure.Persistence.Entities.AnimalEntities.ECachorro;
import com.arthurhenrique_Dev.CatOng.Infraestructure.Persistence.FrameworkRepository.AnimalISpring.RepositorioEstrangeiroCachorro.ISpringCachorro;
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
    public void alterarCachorro(Long id, DTOAtualizacaoAnimais dto) {
        ECachorro cachorroAlterado = fRepository.findById(id).orElse(null);
        if (cachorroAlterado != null) {
            Cachorro moldeDeManipulacao = mapper.toDomain(cachorroAlterado);
            if (dto != null) {
                if (dto.fotos() != null) {
                    moldeDeManipulacao.setFotos(dto.fotos());
                }
                if (dto.descricao() != null) {
                    moldeDeManipulacao.setDescricao(dto.descricao());
                }
                if (dto.peso() != 0 && dto.peso() > 0){
                    moldeDeManipulacao.setPeso(dto.peso());
                }
                if (dto.idade() != 0 && dto.idade() > 0 && dto.idade() > moldeDeManipulacao.getIdade()) {
                    moldeDeManipulacao.setIdade(dto.idade());
                }
                fRepository.save(mapper.toEntity(moldeDeManipulacao));
            } else  {
                throw new IllegalArgumentException("Insira os dados de atualização");
            }
        }
    }

    @Override
    public List<Cachorro> getCachorros(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        return fRepository.findAll(pageable)
                .stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public List<Cachorro> getCachorrosByName(Integer page, Integer size, String nome) {
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
