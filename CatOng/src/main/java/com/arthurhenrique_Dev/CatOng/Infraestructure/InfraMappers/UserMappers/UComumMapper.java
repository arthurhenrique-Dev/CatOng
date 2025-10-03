package com.arthurhenrique_Dev.CatOng.Infraestructure.InfraMappers.UserMappers;

import com.arthurhenrique_Dev.CatOng.Domain.Usuarios.UComum.UComum;
import com.arthurhenrique_Dev.CatOng.Infraestructure.Persistence.Entities.UsuarioEntities.EUComum;
import org.springframework.stereotype.Component;

@Component
public class UComumMapper {

    EnderecoMapper enderecoMapper = new EnderecoMapper();

    public EUComum toEntity(UComum uComumDomain){
        EUComum euComumTraduzido= new EUComum(
                uComumDomain.getNome(),
                uComumDomain.getCpf(),
                uComumDomain.getRG(),
                uComumDomain.getEmail(),
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
                euComum.getEmail(),
                euComum.getSenha(),
                euComum.getTelefone(),
                enderecoMapper.toDomainEndereco(euComum.getEndereco()),
                euComum.getDataNascimento());
        return uComumTraduzido;
    }
}
