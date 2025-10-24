package com.arthurhenrique_Dev.CatOng.Infraestructure.InfraMappers.AnimalMappers.CachorroMapper;

import com.arthurhenrique_Dev.CatOng.Application.DTOs.Animais.DTOCadastroAnimal;
import com.arthurhenrique_Dev.CatOng.Domain.Animal.BaseAnimal.Atividade;
import com.arthurhenrique_Dev.CatOng.Domain.Animal.Cachorros.Cachorro;
import com.arthurhenrique_Dev.CatOng.Infraestructure.Persistence.Entities.AnimalEntities.ECachorro;
import org.springframework.stereotype.Component;

@Component
public class CachorroMapper {

    public ECachorro toEntity(Cachorro cachorroDomain){
        ECachorro cachorroTraduzido = new ECachorro(
                cachorroDomain.getNome(),
                cachorroDomain.getIdade(),
                cachorroDomain.getRaca(),
                cachorroDomain.getAtividade(),
                cachorroDomain.getDescricao(),
                cachorroDomain.getSexo(),
                cachorroDomain.getPeso(),
                cachorroDomain.getFotos());
        return cachorroTraduzido;
    }
    public Cachorro toDomain(ECachorro entityCachorro){
        Cachorro cachorroTraduzido= new Cachorro(
                entityCachorro.getNome(),
                entityCachorro.getIdade(),
                entityCachorro.getRaca(),
                entityCachorro.getAtividade(),
                entityCachorro.getDescricao(),
                entityCachorro.getSexo(),
                entityCachorro.getPeso(),
                entityCachorro.getFotos());
        return cachorroTraduzido;
    }
    public Cachorro DtoToDomain(DTOCadastroAnimal dto) {
        Cachorro cachorroTraduzido = new Cachorro(
                dto.nome(),
                dto.idade(),
                dto.raca(),
                Atividade.ATIVO,
                dto.descricao(),
                dto.sexo(),
                dto.peso(),
                dto.fotos()
        );
        return cachorroTraduzido;
    }
}
