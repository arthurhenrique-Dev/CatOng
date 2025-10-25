package com.arthurhenrique_Dev.CatOng.Infraestructure.Persistence.ImplementsRepositories.UsuarioImplements.ImplUGerenciamento;

import com.arthurhenrique_Dev.CatOng.Application.DTOs.Usuarios.Atualizacao.DTOAtualizacaoUGerenciamento;
import com.arthurhenrique_Dev.CatOng.Application.DTOs.Usuarios.Cadastro.DTORegistroUGerenciamento;
import com.arthurhenrique_Dev.CatOng.Application.DTOs.Usuarios.Retorno.DTORetornoUGerenciamento;
import com.arthurhenrique_Dev.CatOng.Domain.Usuarios.Base.Atividade;
import com.arthurhenrique_Dev.CatOng.Domain.Usuarios.Repositorys.UGerenciamentoRepository.UGerenciamentoRepository;
import com.arthurhenrique_Dev.CatOng.Infraestructure.InfraMappers.UserMappers.UGerenciamentoMapper.UGerenciamentoMapper;
import com.arthurhenrique_Dev.CatOng.Infraestructure.Persistence.Entities.UsuarioEntities.EUGerenciamento.EUGerenciamento;
import com.arthurhenrique_Dev.CatOng.Infraestructure.Persistence.FrameworkRepository.UsuarioISpring.RepositorioEstrangeiroUGerenciamento.ISpringUGerenciamento;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UGerenciamentoUseCaseImpl implements UGerenciamentoRepository {

    private final ISpringUGerenciamento fRepository;
    private final UGerenciamentoMapper mapper;

    public UGerenciamentoUseCaseImpl(ISpringUGerenciamento fRepository, UGerenciamentoMapper mapper) {
        this.fRepository = fRepository;
        this.mapper = mapper;
    }

    @Override
    public void salvarUGerenciamento(DTORegistroUGerenciamento dto) {
        fRepository.save(mapper.ValidacaoEInscricao(dto));
    }

    @Override
    public void removerUGerenciamento(Long NR) {
        EUGerenciamento usuarioDeletado = (fRepository.findByNR(NR));
        usuarioDeletado.setAtividade(Atividade.INATIVO);
        fRepository.save(usuarioDeletado);
    }

    @Override
    public void atualizarUGerenciamento(Long NR, DTOAtualizacaoUGerenciamento dto) {
        EUGerenciamento usuarioAlterado = fRepository.findByNR(NR);
        if (usuarioAlterado != null) {
            if (dto.telefone() != null) {
                usuarioAlterado.setTelefone(dto.telefone());
                fRepository.save(usuarioAlterado);
            }
            else  {
                throw new IllegalArgumentException("Usuario não encontrado");
            }
        }
    }

    @Override
    public Optional<DTORetornoUGerenciamento> getUGerenciamentoByNR(Long NR) {
        EUGerenciamento usuarioRetorno = fRepository.findByNR(NR);
        return Optional.ofNullable(usuarioRetorno)
                .map(mapper::toDtoReturn);
    }

    @Override
    public List<DTORetornoUGerenciamento> getUGerenciamentos(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        return fRepository.findAllByAtividade(Atividade.ATIVO, pageable)
                .stream()
                .map(mapper::toDtoReturn)
                .toList();
    }

    @Override
    public List<DTORetornoUGerenciamento> getUGerenciamentosInativos(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        return fRepository.findAllByAtividade(Atividade.INATIVO, pageable)
                .stream()
                .map(mapper::toDtoReturn)
                .toList();
    }

    @Override
    public Optional<DTORetornoUGerenciamento> getUGerenciamento(String cpf) {
        EUGerenciamento recebido = fRepository.findByCpf(cpf);
        return Optional.ofNullable(recebido)
                .map(mapper::toDtoReturn);
    }
}
