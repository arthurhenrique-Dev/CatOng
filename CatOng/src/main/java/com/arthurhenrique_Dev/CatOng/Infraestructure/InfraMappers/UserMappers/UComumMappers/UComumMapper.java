package com.arthurhenrique_Dev.CatOng.Infraestructure.InfraMappers.UserMappers.UComumMappers;

import com.arthurhenrique_Dev.CatOng.Application.DTOs.Usuarios.Cadastro.DTORegistroUComum;
import com.arthurhenrique_Dev.CatOng.Application.DTOs.Usuarios.Retorno.DTORetornoUComum;
import com.arthurhenrique_Dev.CatOng.Domain.Usuarios.Base.Atividade;
import com.arthurhenrique_Dev.CatOng.Domain.Usuarios.UComum.Endereco;
import com.arthurhenrique_Dev.CatOng.Domain.Usuarios.UComum.UComum;
import com.arthurhenrique_Dev.CatOng.Infraestructure.Persistence.Entities.UsuarioEntities.EUComum.EUComum;
import com.arthurhenrique_Dev.CatOng.Infraestructure.Persistence.Entities.UsuarioEntities.EUComum.PersistenceEndereco;
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
                uComumDomain.getPermissao(),
                uComumDomain.getEmail(),
                uComumDomain.getSenha(),
                uComumDomain.getTelefone(),
                this.toPersistenceEndereco(uComumDomain.getEndereco()),
                uComumDomain.getDataNascimento());
        return euComumTraduzido;
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
                this.toDomainEndereco(euComum.getEndereco()));
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
    public PersistenceEndereco toPersistenceEndereco(Endereco enderecoDomain) {
        PersistenceEndereco enderecoTraduzido = new PersistenceEndereco(
                enderecoDomain.getCep(),
                enderecoDomain.getLogradouro(),
                enderecoDomain.getComplemento(),
                enderecoDomain.getBairro(),
                enderecoDomain.getCidade(),
                enderecoDomain.getNumero()
        );
        return enderecoTraduzido;
    }
    public Endereco toDomainEndereco(PersistenceEndereco enderecoPersistence) {
        Endereco enderecoTraduzido = new Endereco(
                enderecoPersistence.getCep(),
                enderecoPersistence.getLogradouro(),
                enderecoPersistence.getComplemento(),
                enderecoPersistence.getBairro(),
                enderecoPersistence.getCidade(),
                enderecoPersistence.getNumero()
        );
        return enderecoTraduzido;
    }
    public Atividade validarAtividade(Atividade atividade) {
        if (atividade != Atividade.ATIVO && atividade != Atividade.INATIVO) throw new IllegalArgumentException("Erro na atividade");
        return atividade;
    }
}
