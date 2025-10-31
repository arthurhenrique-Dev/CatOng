package com.arthurhenrique_Dev.CatOng.Infraestructure.Persistence.ImplementsRepositories.UsuarioImplements.ImplUGerenciamento;

import com.arthurhenrique_Dev.CatOng.Application.DTOs.Usuarios.Atualizacao.DTOAtualizacaoUGerenciamento;
import com.arthurhenrique_Dev.CatOng.Application.DTOs.Usuarios.Cadastro.DTORegistroUGerenciamento;
import com.arthurhenrique_Dev.CatOng.Application.DTOs.Usuarios.Retorno.DTORetornoUGerenciamento;
import com.arthurhenrique_Dev.CatOng.Controllers.TratamentoDeExcecoes.Excecoes.Usuarios.AtualizacaoInvalidaException;
import com.arthurhenrique_Dev.CatOng.Controllers.TratamentoDeExcecoes.Excecoes.DadoIncorretoException;
import com.arthurhenrique_Dev.CatOng.Controllers.TratamentoDeExcecoes.Excecoes.Usuarios.UsuarioExistenteException;
import com.arthurhenrique_Dev.CatOng.Controllers.TratamentoDeExcecoes.Excecoes.Usuarios.UsuarioInexistenteException;
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
        if (dto == null) throw new DadoIncorretoException("Insira os dados");
        var retorno = fRepository.findByCpf(dto.cpf());
        if (retorno != null) throw new UsuarioExistenteException();
        fRepository.save(mapper.ValidacaoEInscricao(dto));
    }

    @Override
    public void removerUGerenciamento(Long NR) {
        if (NR == null) throw new DadoIncorretoException("Insira o NR");
        EUGerenciamento usuarioDeletado = fRepository.findByNR(NR);
        if (usuarioDeletado == null) throw new UsuarioInexistenteException();
        usuarioDeletado.setAtividade(Atividade.INATIVO);
        fRepository.save(usuarioDeletado);
    }

    @Override
    public void atualizarUGerenciamento(Long NR, DTOAtualizacaoUGerenciamento dto) {
        if (NR == null) throw new DadoIncorretoException("Insira o NR");
        if (dto == null) throw new DadoIncorretoException("Insira o telefone para a atualização");
        EUGerenciamento usuarioAlterado = fRepository.findByNR(NR);
        if (usuarioAlterado != null) {
            if (dto.telefone() != null) {
                usuarioAlterado.setTelefone(dto.telefone());
                fRepository.save(usuarioAlterado);
            } else throw new AtualizacaoInvalidaException();
        } else throw new UsuarioInexistenteException();
    }

    @Override
    public Optional<DTORetornoUGerenciamento> getUGerenciamentoByNR(Long NR) {
        if (NR == null) throw new DadoIncorretoException("Insira o NR");
        EUGerenciamento usuarioRetorno = fRepository.findByNR(NR);
        if (usuarioRetorno == null) throw new UsuarioInexistenteException();
        return Optional.ofNullable(usuarioRetorno)
                .map(mapper::toDtoReturn);
    }

    @Override
    public List<DTORetornoUGerenciamento> getUGerenciamentos(Integer page, Integer size) {
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
    public List<DTORetornoUGerenciamento> getUGerenciamentosInativos(Integer page, Integer size) {
        if (page < 0 || page == null && size < 0 || size == null) {
            throw new DadoIncorretoException("Insira a paginação e o tamanho");
        }
        Pageable pageable = PageRequest.of(page, size);
        var retorno = fRepository.findAllByAtividade(Atividade.INATIVO, pageable)
                .stream()
                .map(mapper::toDtoReturn)
                .toList();
        if (retorno.isEmpty()) throw new UsuarioInexistenteException();
        return retorno;
    }

    @Override
    public Optional<DTORetornoUGerenciamento> getUGerenciamento(String cpf) {
        if (cpf.isEmpty()) throw new DadoIncorretoException("Insira o CPF");
        EUGerenciamento recebido = fRepository.findByCpf(cpf);
        if (recebido == null) throw new UsuarioInexistenteException();
        return Optional.ofNullable(recebido)
                .map(mapper::toDtoReturn);
    }
}
