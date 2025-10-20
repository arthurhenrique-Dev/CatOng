package com.arthurhenrique_Dev.CatOng.Infraestructure.InfraMappers.UserMappers.UGerenciamentoMapper;

import com.arthurhenrique_Dev.CatOng.Application.DTOs.Usuarios.Cadastro.DTORegistroUGerenciamento;
import com.arthurhenrique_Dev.CatOng.Application.DTOs.Usuarios.Retorno.DTORetornoUComum;
import com.arthurhenrique_Dev.CatOng.Application.DTOs.Usuarios.Retorno.DTORetornoUGerenciamento;
import com.arthurhenrique_Dev.CatOng.Domain.Usuarios.Base.Atividade;
import com.arthurhenrique_Dev.CatOng.Domain.Usuarios.Base.Permissao;
import com.arthurhenrique_Dev.CatOng.Domain.Usuarios.UGerenciamento.UGerenciamento;
import com.arthurhenrique_Dev.CatOng.Infraestructure.Persistence.Entities.UsuarioEntities.EUComum.EUComum;
import com.arthurhenrique_Dev.CatOng.Infraestructure.Persistence.Entities.UsuarioEntities.EUGerenciamento.EUGerenciamento;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class UGerenciamentoMapper {

    public EUGerenciamento toEntity(UGerenciamento uGerenciamentoDomain) {
        EUGerenciamento uGerenciamentoTraduzido = new EUGerenciamento(
                uGerenciamentoDomain.getNome(),
                uGerenciamentoDomain.getCpf(),
                uGerenciamentoDomain.getRG(),
                uGerenciamentoDomain.getAtividade(),
                uGerenciamentoDomain.getPermissao(),
                uGerenciamentoDomain.getSenha(),
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
    public UGerenciamento DTORegisterToDomain(DTORegistroUGerenciamento DTOUGerenciamento){
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
    public EUGerenciamento ValidacaoEInscricao(DTORegistroUGerenciamento dtoRegistroUGerenciamento){
        UGerenciamento validacaoPeloDomain = DTORegisterToDomain(dtoRegistroUGerenciamento);
        EUGerenciamento usuarioValidado = toEntity(validacaoPeloDomain);
        return usuarioValidado;
    }
}
