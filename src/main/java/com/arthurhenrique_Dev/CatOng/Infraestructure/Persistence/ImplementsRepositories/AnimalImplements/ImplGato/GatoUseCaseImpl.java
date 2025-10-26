package com.arthurhenrique_Dev.CatOng.Infraestructure.Persistence.ImplementsRepositories.AnimalImplements.ImplGato;

import com.arthurhenrique_Dev.CatOng.Application.DTOs.Animais.DTOAtualizacaoAnimais;
import com.arthurhenrique_Dev.CatOng.Application.DTOs.Animais.DTOCadastroAnimal;
import com.arthurhenrique_Dev.CatOng.Controllers.TratamentoDeExcecoes.Excecoes.Animais.Gatos.GatoInexistenteException;
import com.arthurhenrique_Dev.CatOng.Controllers.TratamentoDeExcecoes.Excecoes.DadoIncorretoException;
import com.arthurhenrique_Dev.CatOng.Controllers.TratamentoDeExcecoes.Excecoes.Usuarios.AtualizacaoInvalidaException;
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
    public void salvarGato(DTOCadastroAnimal dto) {
        if (dto == null) throw new DadoIncorretoException("insira os dados para cadastro");
        var estruturadoPorDomain = mapper.DtoToDomain(dto);
        fRepository.save(mapper.toEntity(estruturadoPorDomain));
    }

    @Override
    public void deletarGato(Long id) {
        if (id <= 0 || id == null) {
            throw new DadoIncorretoException("Insira um id válido");
        }
        EGato gatoDeletado = fRepository
                .findById(id).orElseThrow(() -> new GatoInexistenteException());
        gatoDeletado.setAtividade(Atividade.INATIVO);
        fRepository.save(gatoDeletado);
    }


    @Override
    public void adotarGato(Long id) {
        if (id <= 0 || id == null) {
            throw new DadoIncorretoException("Insira um id válido");
        }
        EGato gatoAdotado = fRepository.findById(id)
                .orElseThrow(() -> new GatoInexistenteException());
        gatoAdotado.setAtividade(Atividade.ADOTADO);
        fRepository.save(gatoAdotado);
    }

    @Override
    public void alterarGato(Long id, DTOAtualizacaoAnimais dto) {
        if (id <= 0 || id == null) {
            throw new DadoIncorretoException("Insira um id válido");
        }
        EGato gatoAlterado = fRepository.findById(id)
                .orElse(null);
        if (gatoAlterado != null) {
            Gato moldeDeManipulacao = mapper.toDomain(gatoAlterado);
            if (dto != null) {
                if (!(dto.fotos().isEmpty())) {
                    moldeDeManipulacao.setFotos(dto.fotos());
                }
                if (!(dto.descricao().isEmpty())) {
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
                throw new AtualizacaoInvalidaException();
            }
        } else {
            throw new GatoInexistenteException();
        }
    }

    @Override
    public List<Gato> getGatos(Integer page, Integer size) {
        if (page < 0 || page == null && size < 0 || size == null) {
            throw new DadoIncorretoException("Insira a paginação e o tamanho");
        }
        Pageable pageable = PageRequest.of(page, size);
        var retorno = fRepository.findAllByAtividade(Atividade.ATIVO, pageable)
                .stream()
                .map(mapper::toDomain)
                .toList();
        if (retorno.isEmpty()) throw new GatoInexistenteException("Nenhum gato disponivel para adoção no momento");
        return retorno;
    }

    @Override
    public List<Gato> getGatosAdotados(Integer page, Integer size) {
        if (page < 0 || page == null && size < 0 || size == null) {
            throw new DadoIncorretoException("Insira a paginação e o tamanho");
        }
        Pageable pageable = PageRequest.of(page, size);
        var retorno = fRepository.findAllByAtividade(Atividade.ADOTADO, pageable)
                .stream()
                .map(mapper::toDomain)
                .toList();
        if (retorno.isEmpty()) throw new GatoInexistenteException("Nenhum gato encontrado");
        return retorno;
    }

    @Override
    public List<Gato> getGatosInativos(Integer page, Integer size) {
        if (page < 0 || page == null && size < 0 || size == null) {
            throw new DadoIncorretoException("Insira a paginação e o tamanho");
        }
        Pageable pageable = PageRequest.of(page, size);
        var retorno = fRepository.findAllByAtividade(Atividade.INATIVO, pageable)
                .stream()
                .map(mapper::toDomain)
                .toList();
        if (retorno.isEmpty()) throw new GatoInexistenteException("Nenhum gato encontrado");
        return retorno;
    }

    @Override
    public List<Gato> getGatoByName(Integer page, Integer size, String nome) {
        if (page < 0 || page == null && size < 0 || size == null) {
            throw new DadoIncorretoException("Insira a paginação e o tamanho");
        }
        if (nome.isEmpty()) throw new DadoIncorretoException("Insira o nome");
        var retorno = fRepository.getGatoByNome(nome, PageRequest.of(page, size))
                .stream()
                .map(mapper::toDomain)
                .toList();
        if (retorno.isEmpty()) throw new GatoInexistenteException("Nenhum gato encontrado");
        return retorno;
    }

    @Override
    public Optional<Gato> getGatoById(Long id) {
        if (id == null) throw new GatoInexistenteException("insira o id");
        Optional<Gato> retorno = fRepository.findById(id)
                .map(mapper::toDomain);
        if (retorno.isPresent()) {
            return retorno;
        } else {
            throw new GatoInexistenteException();
        }
    }

    ;

}
