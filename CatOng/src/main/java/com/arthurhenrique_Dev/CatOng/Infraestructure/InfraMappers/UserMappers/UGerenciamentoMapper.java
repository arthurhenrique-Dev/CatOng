package com.arthurhenrique_Dev.CatOng.Infraestructure.InfraMappers.UserMappers;

import com.arthurhenrique_Dev.CatOng.Application.DTOs.DTORegistroUGerenciamento;
import com.arthurhenrique_Dev.CatOng.Domain.Usuarios.UGerenciamento.UGerenciamento;
import com.arthurhenrique_Dev.CatOng.Infraestructure.Persistence.Entities.UsuarioEntities.EUGerenciamento;
import org.springframework.stereotype.Component;

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
    public UGerenciamento toDomain(EUGerenciamento eUGerenciamento) {
        UGerenciamento uGerenciamentoTraduzido = new UGerenciamento(
                eUGerenciamento.getNome(),
                eUGerenciamento.getCpf(),
                eUGerenciamento.getRG(),
                eUGerenciamento.getEmail(),
                eUGerenciamento.getSenha(),
                eUGerenciamento.getTelefone());
        return uGerenciamentoTraduzido;
    }

    public UGerenciamento DTORegisterToDomain(DTORegistroUGerenciamento DTOUGerenciamento){
        UGerenciamento uGerenciamentoTraduzido = new UGerenciamento(
                DTOUGerenciamento.nome(),
                DTOUGerenciamento.cpf(),
                DTOUGerenciamento.rg(),
                DTOUGerenciamento.email(),
                DTOUGerenciamento.senha(),
                DTOUGerenciamento.telefone());
        return uGerenciamentoTraduzido
    }
    public EUGerenciamento ValidacaoEInscricao(DTORegistroUGerenciamento dtoRegistroUGerenciamento){
        UGerenciamento validacaoPeloDomain = DTORegisterToDomain(dtoRegistroUGerenciamento);
        EUGerenciamento usuarioValidado =
    }
}
