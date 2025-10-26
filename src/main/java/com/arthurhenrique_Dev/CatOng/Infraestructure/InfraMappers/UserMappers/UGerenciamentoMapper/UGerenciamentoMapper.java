package com.arthurhenrique_Dev.CatOng.Infraestructure.InfraMappers.UserMappers.UGerenciamentoMapper;

import com.arthurhenrique_Dev.CatOng.Application.DTOs.Usuarios.Cadastro.DTORegistroUGerenciamento;
import com.arthurhenrique_Dev.CatOng.Application.DTOs.Usuarios.Retorno.DTORetornoUGerenciamento;
import com.arthurhenrique_Dev.CatOng.Controllers.TratamentoDeExcecoes.Excecoes.DadoIncorretoException;
import com.arthurhenrique_Dev.CatOng.Domain.Usuarios.Base.Atividade;
import com.arthurhenrique_Dev.CatOng.Domain.Usuarios.UGerenciamento.UGerenciamento;
import com.arthurhenrique_Dev.CatOng.Infraestructure.Persistence.Entities.UsuarioEntities.EUGerenciamento.EUGerenciamento;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UGerenciamentoMapper {

    public EUGerenciamento toEntity(UGerenciamento uGerenciamentoDomain) {

        if (uGerenciamentoDomain.getNome() == null) throw new DadoIncorretoException("Insira seu nome real");
        if (uGerenciamentoDomain.getCpf() == null) throw new DadoIncorretoException("Cpf inválido");
        if (uGerenciamentoDomain.getRG() == null) throw new DadoIncorretoException("RG inválido");
        if (uGerenciamentoDomain.getAtividade() == null) throw new DadoIncorretoException();
        if (uGerenciamentoDomain.getPermissao() == null) throw new DadoIncorretoException();
        if (uGerenciamentoDomain.getSenha() == null) throw new DadoIncorretoException("Senha inválida");
        if (uGerenciamentoDomain.getEmail() == null) throw new DadoIncorretoException("Email inválido");
        if (uGerenciamentoDomain.getTelefone() == null) throw new DadoIncorretoException("Telefone inválido");

        String senhaCriptografada = new BCryptPasswordEncoder().encode(uGerenciamentoDomain.getSenha());

        EUGerenciamento uGerenciamentoTraduzido = new EUGerenciamento(
                uGerenciamentoDomain.getNome(),
                uGerenciamentoDomain.getCpf(),
                uGerenciamentoDomain.getRG(),
                uGerenciamentoDomain.getAtividade(),
                uGerenciamentoDomain.getPermissao(),
                senhaCriptografada,
                uGerenciamentoDomain.getEmail(),
                uGerenciamentoDomain.getTelefone());
        return uGerenciamentoTraduzido;
    }

    public DTORetornoUGerenciamento toDtoReturn(EUGerenciamento euGerenciamento) {

        DTORetornoUGerenciamento dtoRetorno = new DTORetornoUGerenciamento(
                euGerenciamento.getNR(),
                euGerenciamento.getNome(),
                euGerenciamento.getCpf(),
                euGerenciamento.getRG(),
                euGerenciamento.getAtividade(),
                euGerenciamento.getPermissao(),
                euGerenciamento.getEmail(),
                euGerenciamento.getTelefone()
        );
        return dtoRetorno;
    }

    public UGerenciamento DTORegisterToDomain(DTORegistroUGerenciamento DTOUGerenciamento) {
        UGerenciamento uGerenciamentoTraduzido = new UGerenciamento(
                DTOUGerenciamento.nome(),
                DTOUGerenciamento.cpf(),
                DTOUGerenciamento.rg(),
                Atividade.ATIVO,
                DTOUGerenciamento.email(),
                DTOUGerenciamento.senha(),
                DTOUGerenciamento.telefone());
        return uGerenciamentoTraduzido;
    }

    public EUGerenciamento ValidacaoEInscricao(DTORegistroUGerenciamento dtoRegistroUGerenciamento) {
        UGerenciamento validacaoPeloDomain = DTORegisterToDomain(dtoRegistroUGerenciamento);
        EUGerenciamento usuarioValidado = toEntity(validacaoPeloDomain);
        return usuarioValidado;
    }
}
