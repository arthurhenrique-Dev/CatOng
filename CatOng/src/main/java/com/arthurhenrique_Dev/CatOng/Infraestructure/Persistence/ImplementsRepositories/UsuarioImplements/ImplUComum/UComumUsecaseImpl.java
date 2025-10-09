package com.arthurhenrique_Dev.CatOng.Infraestructure.Persistence.ImplementsRepositories.UsuarioImplements.ImplUComum;

import com.arthurhenrique_Dev.CatOng.Application.DTOs.DTORegistroUComum;
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
    public void removerUComum(Long id) {
        EUComum usuarioDeletado = fRepository.findById(id).orElse(null);
        usuarioDeletado.setAtividade(Atividade.INATIVO);
    }

    @Override
    public void atualizarUComum(Long id, UComum uComum) {

    }

    @Override
    public Optional<UComum> getUComumById(Long id) {
        return fRepository.findById(id)
                .map(mapper::toDomain);
    }

    @Override
    public List<UComum> getUComuns(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return fRepository.findAll(pageable)
                .stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public List<UComum> getUComumsByName(int page, int size, String nome) {
        return fRepository.getEuComumsByNome(nome, PageRequest.of(page, size))
                .stream()
                .map(mapper::toDomain)
                .toList();
    }
}
