package com.arthurhenrique_Dev.CatOng.Infraestructure.Persistence.ImplementsRepositories.UsuarioImplements;

import com.arthurhenrique_Dev.CatOng.Domain.Usuarios.Base.Atividade;
import com.arthurhenrique_Dev.CatOng.Domain.Usuarios.Repositorys.UGerenciamentoRepository.UGerenciamentoRepository;
import com.arthurhenrique_Dev.CatOng.Domain.Usuarios.UGerenciamento.UGerenciamento;
import com.arthurhenrique_Dev.CatOng.Infraestructure.InfraMappers.UserMappers.UGerenciamentoMapper;
import com.arthurhenrique_Dev.CatOng.Infraestructure.Persistence.Entities.UsuarioEntities.EUGerenciamento;
import com.arthurhenrique_Dev.CatOng.Infraestructure.Persistence.FrameworkRepository.UsuarioISpring.ISpringUGerenciamento;
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
    public void salvarUGerenciamento(UGerenciamento ugerenciamento) {
        fRepository.save(mapper.toEntity(ugerenciamento));
    }

    @Override
    public void removerUGerenciamento(Long NR) {
        EUGerenciamento usuarioDeletado = fRepository.findByNR(NR);
        usuarioDeletado.setAtividade(Atividade.INATIVO);
    }

    @Override
    public void atualizarUGerenciamento(Long NR, UGerenciamento uGerenciamento) {

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
}
