package com.arthurhenrique_Dev.CatOng.Infraestructure.Persistence.ImplementsRepositories.AnimalImplements.ImplCachorro;

import com.arthurhenrique_Dev.CatOng.Application.DTOs.Animais.DTOAtualizacaoAnimais;
import com.arthurhenrique_Dev.CatOng.Application.DTOs.Animais.DTOCadastroAnimal;
import com.arthurhenrique_Dev.CatOng.Controllers.TratamentoDeExcecoes.Excecoes.Animais.AnimaisAtualizacaoInvalidaException;
import com.arthurhenrique_Dev.CatOng.Controllers.TratamentoDeExcecoes.Excecoes.Animais.Cachorros.CachorroInexistenteException;
import com.arthurhenrique_Dev.CatOng.Controllers.TratamentoDeExcecoes.Excecoes.DadoIncorretoException;
import com.arthurhenrique_Dev.CatOng.Domain.Animal.BaseAnimal.Atividade;
import com.arthurhenrique_Dev.CatOng.Domain.Animal.Cachorros.Cachorro;
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
    public void salvarCachorro(DTOCadastroAnimal dto) {
        if (dto == null) throw new DadoIncorretoException("insira os dados");
        var estruturadoPorDomain = mapper.DtoToDomain(dto);
        fRepository.save(mapper.toEntity(estruturadoPorDomain));
    }

    @Override
    public void deletarCachorro(Long id) {
        if (id == null) throw new DadoIncorretoException("insira o id");
        ECachorro retorno = fRepository
                .findById(id).orElseThrow(() -> new CachorroInexistenteException());
        retorno.setAtividade(Atividade.INATIVO);
        fRepository.save(retorno);
    }

    @Override
    public void adotarCachorro(Long id) {
        if (id == null) throw new DadoIncorretoException("insira o id");
        ECachorro eCachorro = fRepository
                .findById(id).orElseThrow(() -> new CachorroInexistenteException());
        eCachorro.setAtividade(Atividade.ADOTADO);
        fRepository.save(eCachorro);
    }

    @Override
    public void alterarCachorro(Long id, DTOAtualizacaoAnimais dto) {
        ECachorro cachorroAlterado = fRepository.findById(id)
                .orElseThrow(() -> new CachorroInexistenteException());
        Cachorro moldeDeManipulacao = mapper.toDomain(cachorroAlterado);
        if (dto != null) {
            if (dto.fotos().isEmpty()) {
                moldeDeManipulacao.setFotos(dto.fotos());
            }
            if (dto.descricao().isEmpty()) {
                moldeDeManipulacao.setDescricao(dto.descricao());
            }
            if (dto.peso() != 0 && dto.peso() > 0) {
                moldeDeManipulacao.setPeso(dto.peso());
            }
            if (dto.idade() != 0 && dto.idade() > 0 && dto.idade() > moldeDeManipulacao.getIdade()) {
                moldeDeManipulacao.setIdade(dto.idade());
            }
            fRepository.save(mapper.toEntity(moldeDeManipulacao));
        } else {
            throw new AnimaisAtualizacaoInvalidaException();
        }
    }

    @Override
    public List<Cachorro> getCachorros(Integer page, Integer size) {
        if (page < 0 || page == null && size < 0 || size == null) {
            throw new DadoIncorretoException("Insira a paginação e o tamanho");
        }
        Pageable pageable = PageRequest.of(page, size);
        var retorno = fRepository.findAllByAtividade(Atividade.ATIVO, pageable)
                .stream()
                .map(mapper::toDomain)
                .toList();
        if (retorno.isEmpty()) throw new CachorroInexistenteException("Nenhum cachorro disponivel para adoção");
        return retorno;
    }

    @Override
    public List<Cachorro> getCachorrosInativos(Integer page, Integer size) {
        if (page < 0 || page == null && size < 0 || size == null) {
            throw new DadoIncorretoException("Insira a paginação e o tamanho");
        }
        Pageable pageable = PageRequest.of(page, size);
        var retorno = fRepository.findAllByAtividade(Atividade.INATIVO, pageable)
                .stream()
                .map(mapper::toDomain)
                .toList();
        if (retorno.isEmpty()) throw new CachorroInexistenteException("Nenhum cachorro deletado");
        return retorno;
    }

    @Override
    public List<Cachorro> getCachorrosAdotados(Integer page, Integer size) {
        if (page < 0 || page == null && size < 0 || size == null) {
            throw new DadoIncorretoException("Insira a paginação e o tamanho");
        }
        Pageable pageable = PageRequest.of(page, size);
        var retorno = fRepository.findAllByAtividade(Atividade.ADOTADO, pageable)
                .stream()
                .map(mapper::toDomain)
                .toList();
        if (retorno.isEmpty()) throw new CachorroInexistenteException("Nenhum cachorro foi adotado");
        return retorno;
    }

    @Override
    public List<Cachorro> getCachorrosByName(Integer page, Integer size, String nome) {
        if (page < 0 || page == null && size < 0 || size == null) {
            throw new DadoIncorretoException("Insira a paginação e o tamanho");
        }

        if (nome.isEmpty()) throw new DadoIncorretoException("Insira um nome");

        var retorno = fRepository.findByNome(nome, PageRequest.of(page, size))
                .stream()
                .map(mapper::toDomain)
                .toList();

        if (retorno.isEmpty()) throw new CachorroInexistenteException();

        return retorno;
    }

    @Override
    public Optional<Cachorro> getCachorroById(Long id) {
        if (id <= 0 || id == null) {
            throw new DadoIncorretoException("Insira um id válido");
        }
        return fRepository.findById(id)
                .map(mapper::toDomain)
                .or(() -> {
                    throw new CachorroInexistenteException();
                });
    }
}
