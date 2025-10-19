package com.arthurhenrique_Dev.CatOng.Infraestructure.InfraMappers.UserMappers.UComumMappers;

import com.arthurhenrique_Dev.CatOng.Application.DTOs.Usuarios.Cadastro.DTORegistroUComum;
import com.arthurhenrique_Dev.CatOng.Application.DTOs.Usuarios.Retorno.DTORetornoUComum;
import com.arthurhenrique_Dev.CatOng.Domain.Usuarios.Base.Atividade;
import com.arthurhenrique_Dev.CatOng.Domain.Usuarios.UComum.UComum;
import com.arthurhenrique_Dev.CatOng.Infraestructure.Persistence.Entities.UsuarioEntities.EUComum.EUComum;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class UComumMapper {

    EnderecoMapper enderecoMapper = new EnderecoMapper();

    public EUComum toEntity(UComum uComumDomain){
        EUComum euComumTraduzido= new EUComum(
                uComumDomain.getNome(),
                uComumDomain.getCpf(),
                uComumDomain.getRG(),
                uComumDomain.getAtividade(),
                uComumDomain.getEmail(),
                uComumDomain.getSenha(),
                uComumDomain.getTelefone(),
                enderecoMapper.toPersistenceEndereco(uComumDomain.getEndereco()),
                uComumDomain.getDataNascimento());
        return euComumTraduzido;
    }
    public UComum toDomain(EUComum euComum){
        UComum uComumTraduzido= new UComum(
                euComum.getNome(),
                euComum.getCpf(),
                euComum.getRG(),
                euComum.getAtividade(),
                euComum.getEmail(),
                euComum.getSenha(),
                euComum.getTelefone(),
                enderecoMapper.toDomainEndereco(euComum.getEndereco()),
                euComum.getDataNascimento());
        return uComumTraduzido;
    }
    public DTORetornoUComum toDtoReturn(EUComum euComum){
        DTORetornoUComum dto= new DTORetornoUComum(
                euComum.getNome(),
                euComum.getCpf(),
                euComum.getRG(),
                euComum.getAtividade(),
                euComum.getEmail(),
                euComum.getTelefone(),
                LocalDate.now().getYear() - euComum.getDataNascimento().getYear(),
                enderecoMapper.toDomainEndereco(euComum.getEndereco()));
        return dto;
    }
    public UComum DTORegisterToDomain(DTORegistroUComum DTOuComum){
        UComum uComumTraduzido = new UComum(
                DTOuComum.nome(),
                DTOuComum.cpf(),
                DTOuComum.rg(),
                Atividade.ATIVO,
                DTOuComum.email(),
                DTOuComum.senha(),
                DTOuComum.telefone(),
                DTOuComum.endereco(),
                DTOuComum.dataDeNascimento());
        return uComumTraduzido;
    }
    public Atividade validarAtividade(Atividade atividade) {
        if (atividade != Atividade.ATIVO && atividade != Atividade.INATIVO) throw new IllegalArgumentException("Erro na atividade");
        return atividade;
    }
}
