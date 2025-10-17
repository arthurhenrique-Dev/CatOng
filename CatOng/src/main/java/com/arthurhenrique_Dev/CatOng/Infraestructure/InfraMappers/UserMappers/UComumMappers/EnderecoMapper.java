package com.arthurhenrique_Dev.CatOng.Infraestructure.InfraMappers.UserMappers.UComumMappers;

import com.arthurhenrique_Dev.CatOng.Domain.Usuarios.UComum.Endereco;
import com.arthurhenrique_Dev.CatOng.Infraestructure.Persistence.Entities.UsuarioEntities.EUComum.PersistenceEndereco;
import org.springframework.stereotype.Component;

@Component
public class EnderecoMapper {

    PersistenceEndereco toPersistenceEndereco(Endereco enderecoDomain) {
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
    Endereco toDomainEndereco(PersistenceEndereco enderecoPersistence) {
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
}
