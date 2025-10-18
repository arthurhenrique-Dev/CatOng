package com.arthurhenrique_Dev.CatOng.Infraestructure.Persistence.ImplementsRepositories.UsuarioImplements.ImplUComum;

import com.arthurhenrique_Dev.CatOng.Application.DTOs.Usuarios.Atualizacao.DTOAtualizacaoUComum;
import com.arthurhenrique_Dev.CatOng.Application.DTOs.Usuarios.Cadastro.DTORegistroUComum;
import com.arthurhenrique_Dev.CatOng.Domain.Usuarios.Base.Atividade;
import com.arthurhenrique_Dev.CatOng.Domain.Usuarios.Repositorys.UComumRepository.UComumRepository;
import com.arthurhenrique_Dev.CatOng.Domain.Usuarios.UComum.UComum;
import com.arthurhenrique_Dev.CatOng.Infraestructure.InfraMappers.UserMappers.UComumMappers.UComumMapper;
import com.arthurhenrique_Dev.CatOng.Infraestructure.Persistence.Entities.UsuarioEntities.EUComum.EUComum;
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
        fRepository.save(mapper.ValidacaoEInscricao(registroUComum));
    }

    @Override
    public void removerUComum(String cpf) {
        EUComum usuarioDeletado = fRepository.findById(cpf).orElse(null);
        usuarioDeletado.setAtividade(Atividade.INATIVO);
    }

    @Override
    public void atualizarUComum(String cpf, DTOAtualizacaoUComum dto) {
        EUComum usuarioRecebido = fRepository.findById(cpf).orElse(null);
        UComum moldeDeManipulacao = mapper.toDomain(usuarioRecebido);
        if (usuarioRecebido != null) {
            if (dto != null) {
                if (dto.endereco() != null) {
                    moldeDeManipulacao.setEndereco(dto.endereco());
                }
                if (dto.telefone() != null) {
                    moldeDeManipulacao.setTelefone(dto.telefone());
                }
                fRepository.save(mapper.toEntity(moldeDeManipulacao));
            }else {
                throw new IllegalArgumentException("Insira o dado que você quer modificar (endereço ou telefone)");
            }
        }
    }

    @Override
    public Optional<UComum> getUComum(String cpf) {
        EUComum usuarioRecebido = fRepository.findById(cpf).orElse(null);
        if (usuarioRecebido != null) {
            return Optional.of(mapper.toDomain(usuarioRecebido));
        } else {
            throw new IllegalArgumentException("nenhum usuário encontrado");
        }
    }

    @Override
    public List<UComum> getUComuns(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        return fRepository.findAll(pageable)
                .stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public List<UComum> getUComumsByName(Integer page, Integer size, String nome) {
        return fRepository.getEuComumsByNome(nome, PageRequest.of(page, size))
                .stream()
                .map(mapper::toDomain)
                .toList();
    }
}
