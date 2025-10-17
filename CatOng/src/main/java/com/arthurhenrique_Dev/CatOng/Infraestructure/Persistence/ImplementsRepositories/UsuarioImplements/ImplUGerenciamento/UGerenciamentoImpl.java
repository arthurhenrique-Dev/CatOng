package com.arthurhenrique_Dev.CatOng.Infraestructure.Persistence.ImplementsRepositories.UsuarioImplements.ImplUGerenciamento;

import com.arthurhenrique_Dev.CatOng.Application.DTOs.Usuarios.Atualizacao.DTOAtualizacaoUGerenciamento;
import com.arthurhenrique_Dev.CatOng.Application.DTOs.Usuarios.Cadastro.DTORegistroUGerenciamento;
import com.arthurhenrique_Dev.CatOng.Domain.Usuarios.Base.Atividade;
import com.arthurhenrique_Dev.CatOng.Domain.Usuarios.Repositorys.UGerenciamentoRepository.UGerenciamentoRepository;
import com.arthurhenrique_Dev.CatOng.Domain.Usuarios.UGerenciamento.UGerenciamento;
import com.arthurhenrique_Dev.CatOng.Infraestructure.InfraMappers.UserMappers.UGerenciamentoMapper.UGerenciamentoMapper;
import com.arthurhenrique_Dev.CatOng.Infraestructure.Persistence.Entities.UsuarioEntities.EUGerenciamento.EUGerenciamento;
import com.arthurhenrique_Dev.CatOng.Infraestructure.Persistence.FrameworkRepository.UsuarioISpring.RepositorioEstrangeiroUGerenciamento.ISpringUGerenciamento;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UGerenciamentoImpl implements UGerenciamentoRepository {

    private final ISpringUGerenciamento fRepository;
    private final UGerenciamentoMapper mapper;

    public UGerenciamentoImpl(ISpringUGerenciamento fRepository, UGerenciamentoMapper mapper) {
        this.fRepository = fRepository;
        this.mapper = mapper;
    }

    @Override
    public void salvarUGerenciamento(DTORegistroUGerenciamento dto) {
        fRepository.save(mapper.ValidacaoEInscricao(dto));
    }

    @Override
    public void removerUGerenciamento(Long NR) {
        UGerenciamento usuarioDeletado = (mapper.toDomain(fRepository.findByNR(NR)));
        usuarioDeletado.setAtividade(Atividade.INATIVO);
        EUGerenciamento usuarioRetorno = (mapper.toEntity(usuarioDeletado));
    }

    @Override
    public void atualizarUGerenciamento(Long NR, DTOAtualizacaoUGerenciamento dto) {
        EUGerenciamento usuarioAlterado = fRepository.findByNR(NR);
        if (usuarioAlterado != null) {
            UGerenciamento moldeDeManipulacao = mapper.toDomain(usuarioAlterado);
            if (dto.telefone() != null) {
                moldeDeManipulacao.setTelefone(dto.telefone());
                fRepository.save(mapper.toEntity(moldeDeManipulacao));
            }
            else  {
                throw new IllegalArgumentException("Insira um telefone");
            }
        } else   {
            throw new IllegalArgumentException("Insira sua alteração");
        }
    }

    @Override
    public Optional<UGerenciamento> getUGerenciamentoByNR(Long NR) {
        EUGerenciamento usuarioRetorno = fRepository.findByNR(NR);
        return Optional.ofNullable(usuarioRetorno)
                .map(mapper::toDomain);
    }

    @Override
    public List<UGerenciamento> getUGerenciamentos(int page, int size) {
        return fRepository.findAll()
                .stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public Optional<UGerenciamento> getUGerenciamentoByCpf(String cpf) {
        EUGerenciamento recebido = fRepository.findByCpf(cpf);
        return Optional.ofNullable(recebido)
                .map(mapper::toDomain);
    }
}
