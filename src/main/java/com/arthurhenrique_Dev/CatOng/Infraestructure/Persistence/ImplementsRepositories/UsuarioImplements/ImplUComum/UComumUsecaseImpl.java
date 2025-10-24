package com.arthurhenrique_Dev.CatOng.Infraestructure.Persistence.ImplementsRepositories.UsuarioImplements.ImplUComum;

import com.arthurhenrique_Dev.CatOng.Application.DTOs.Usuarios.Atualizacao.DTOAtualizacaoUComum;
import com.arthurhenrique_Dev.CatOng.Application.DTOs.Usuarios.Cadastro.DTORegistroUComum;
import com.arthurhenrique_Dev.CatOng.Application.DTOs.Usuarios.Retorno.DTORetornoUComum;
import com.arthurhenrique_Dev.CatOng.Domain.Usuarios.Base.Atividade;
import com.arthurhenrique_Dev.CatOng.Domain.Usuarios.Repositorys.UComumRepository.UComumRepository;
import com.arthurhenrique_Dev.CatOng.Domain.Usuarios.UComum.UComum;
import com.arthurhenrique_Dev.CatOng.Infraestructure.InfraMappers.UserMappers.UComumMappers.UComumMapper;
import com.arthurhenrique_Dev.CatOng.Infraestructure.Persistence.Entities.UsuarioEntities.EUComum.EUComum;
import com.arthurhenrique_Dev.CatOng.Infraestructure.Persistence.Entities.UsuarioEntities.EUComum.PersistenceEndereco;
import com.arthurhenrique_Dev.CatOng.Infraestructure.Persistence.FrameworkRepository.UsuarioISpring.RepositorioEstrangeiroUComum.ISpringUComum;
import org.springframework.data.domain.Page;
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
        UComum validacaoPorDomain = mapper.DTORegisterToDomain(registroUComum);
        EUComum usuarioValido = mapper.toEntity(validacaoPorDomain);
        mapper.validarAtividade(usuarioValido.getAtividade());
        fRepository.save(usuarioValido);
    }

    @Override
    public void removerUComum(String cpf) {
        EUComum usuarioADeletar = fRepository.findById(cpf).orElse(null);
        usuarioADeletar.setAtividade(Atividade.INATIVO);
        fRepository.save(usuarioADeletar);
    }

    @Override
    public void atualizarUComum(String cpf, DTOAtualizacaoUComum dto) {
        EUComum usuarioRecebido = fRepository.findById(cpf).orElse(null);
        if (usuarioRecebido != null) {
            if (dto != null) {
                if (dto.endereco() != null) {
                    PersistenceEndereco enderecoAtualizado = mapper.toPersistenceEndereco(dto.endereco());
                    usuarioRecebido.setEndereco(enderecoAtualizado);
                }
                if (dto.telefone() != null) {
                    usuarioRecebido.setTelefone(dto.telefone());
                }
                fRepository.save(usuarioRecebido);
            }else {
                throw new IllegalArgumentException("Insira o dado que você quer modificar (endereço ou telefone)");
            }
        }
    }

    @Override
    public Optional<DTORetornoUComum> getUComum(String cpf) {
        EUComum usuarioRecebido = fRepository.findById(cpf).orElse(null);
        if (usuarioRecebido != null) {
            return Optional.ofNullable(mapper.toDtoReturn(usuarioRecebido));
        } else {
            return Optional.empty();
        }
    }

    @Override
    public List<DTORetornoUComum> getUComuns(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        return fRepository.findAllByAtividade(Atividade.ATIVO, pageable)
                .stream()
                .map(mapper::toDtoReturn)
                .toList();
    }

    @Override
    public List<DTORetornoUComum> getUComunsInativos(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return fRepository.findAllByAtividade(Atividade.INATIVO, pageable)
                .stream()
                .map(mapper::toDtoReturn)
                .toList();
    }

    @Override
    public List<DTORetornoUComum> getUComumsByName(Integer page, Integer size, String nome) {
        return fRepository.getEuComumsByNome(nome, PageRequest.of(page, size))
                .stream()
                .map(mapper::toDtoReturn)
                .toList();
    }
}
