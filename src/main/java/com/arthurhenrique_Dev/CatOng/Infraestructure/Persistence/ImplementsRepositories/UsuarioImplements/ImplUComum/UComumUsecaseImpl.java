package com.arthurhenrique_Dev.CatOng.Infraestructure.Persistence.ImplementsRepositories.UsuarioImplements.ImplUComum;

import com.arthurhenrique_Dev.CatOng.Application.DTOs.Usuarios.Atualizacao.DTOAtualizacaoUComum;
import com.arthurhenrique_Dev.CatOng.Application.DTOs.Usuarios.Cadastro.DTORegistroUComum;
import com.arthurhenrique_Dev.CatOng.Application.DTOs.Usuarios.Retorno.DTORetornoUComum;
import com.arthurhenrique_Dev.CatOng.Controllers.TratamentoDeExcecoes.Excecoes.DadoIncorretoException;
import com.arthurhenrique_Dev.CatOng.Controllers.TratamentoDeExcecoes.Excecoes.Usuarios.AtualizacaoInvalidaException;
import com.arthurhenrique_Dev.CatOng.Controllers.TratamentoDeExcecoes.Excecoes.Usuarios.UsuarioExistenteException;
import com.arthurhenrique_Dev.CatOng.Controllers.TratamentoDeExcecoes.Excecoes.Usuarios.UsuarioInexistenteException;
import com.arthurhenrique_Dev.CatOng.Domain.Usuarios.Base.Atividade;
import com.arthurhenrique_Dev.CatOng.Domain.Usuarios.Repositorys.UComumRepository.UComumRepository;
import com.arthurhenrique_Dev.CatOng.Domain.Usuarios.UComum.UComum;
import com.arthurhenrique_Dev.CatOng.Infraestructure.InfraMappers.UserMappers.UComumMappers.UComumMapper;
import com.arthurhenrique_Dev.CatOng.Infraestructure.Persistence.Entities.UsuarioEntities.EUComum.EUComum;
import com.arthurhenrique_Dev.CatOng.Infraestructure.Persistence.Entities.UsuarioEntities.EUComum.PersistenceEndereco;
import com.arthurhenrique_Dev.CatOng.Infraestructure.Persistence.FrameworkRepository.UsuarioISpring.RepositorioEstrangeiroUComum.ISpringUComum;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UComumUsecaseImpl implements UComumRepository {

    private final ISpringUComum fRepository;
    private final UComumMapper mapper;


    public UComumUsecaseImpl(ISpringUComum fRepository, UComumMapper mapper) {
        this.fRepository = fRepository;
        this.mapper = mapper;
    }


    @Override
    public void salvarUComum(DTORegistroUComum registroUComum) {
        if (registroUComum == null) {
            throw new DadoIncorretoException("insira os dados para registro");
        }
        var retorno = fRepository.findById(registroUComum.cpf());
        if (retorno.isPresent()) {
            throw new UsuarioExistenteException();
        }
        UComum validacaoPorDomain = mapper.DTORegisterToDomain(registroUComum);
        EUComum usuarioValido = mapper.toEntity(validacaoPorDomain);
        mapper.validarAtividade(usuarioValido.getAtividade());
        fRepository.save(usuarioValido);
    }

    @Override
    public void removerUComum(String cpf) {
        if (cpf.isEmpty()) {
            throw new UsuarioInexistenteException("insira o cpf");
        }
        EUComum usuarioADeletar = fRepository
                .findById(cpf).orElseThrow(() -> new UsuarioInexistenteException());
        usuarioADeletar.setAtividade(Atividade.INATIVO);
        fRepository.save(usuarioADeletar);
    }

    @Override
    public void atualizarUComum(String cpf, DTOAtualizacaoUComum dto) {
        if (cpf.isEmpty()) {
            throw new DadoIncorretoException("Insira o cpf");
        }
        EUComum usuarioRecebido = fRepository
                .findById(cpf).orElseThrow(() -> new UsuarioInexistenteException());
        if (dto.endereco() != null || dto.telefone() != null) {
            if (dto.endereco() != null) {
                PersistenceEndereco enderecoAtualizado = mapper.toPersistenceEndereco(dto.endereco());
                usuarioRecebido.setEndereco(enderecoAtualizado);
            }
            if (dto.telefone() != null) usuarioRecebido.setTelefone(dto.telefone());
            fRepository.save(usuarioRecebido);
        } else throw new AtualizacaoInvalidaException("Insira o dado que você quer modificar (endereço ou telefone)");
    }

    @Override
    public Optional<DTORetornoUComum> getUComum(String cpf) {
        if (cpf.isEmpty()) throw new UsuarioInexistenteException("insira o cpf");
        EUComum usuarioRecebido = fRepository
                .findById(cpf).orElseThrow(() -> new UsuarioInexistenteException());
        return Optional.ofNullable(mapper.toDtoReturn(usuarioRecebido));
    }

    @Override
    public List<DTORetornoUComum> getUComuns(Integer page, Integer size) {
        if (page < 0 || page == null && size < 0 || size == null) {
            throw new DadoIncorretoException("Insira a paginação e o tamanho");
        }
        Pageable pageable = PageRequest.of(page, size);
        var retorno = fRepository.findAllByAtividade(Atividade.ATIVO, pageable)
                .stream()
                .map(mapper::toDtoReturn)
                .toList();
        if (retorno.isEmpty()) throw new UsuarioInexistenteException("Nenhum usuario encontrado");
        return retorno;
    }

    @Override
    public List<DTORetornoUComum> getUComunsInativos(Integer page, Integer size) {
        if (page < 0 || page == null && size < 0 || size == null) {
            throw new DadoIncorretoException("Insira a paginação e o tamanho");
        }
        Pageable pageable = PageRequest.of(page, size);
        return fRepository.findAllByAtividade(Atividade.INATIVO, pageable)
                .stream()
                .map(mapper::toDtoReturn)
                .toList();
    }

    @Override
    public List<DTORetornoUComum> getUComumsByName(Integer page, Integer size, String nome) {
        if (page < 0 || page == null && size < 0 || size == null) {
            throw new DadoIncorretoException("Insira a paginação e o tamanho");
        }
        if (nome.isEmpty()) {
            throw new DadoIncorretoException("Insira o nome do usuario");
        }
        return fRepository.getEuComumsByNome(nome, PageRequest.of(page, size))
                .stream()
                .map(mapper::toDtoReturn)
                .toList();
    }
}
