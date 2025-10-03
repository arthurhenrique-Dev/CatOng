package com.arthurhenrique_Dev.CatOng.Infraestructure.InfraMappers.AnimalMappers;

import com.arthurhenrique_Dev.CatOng.Domain.Animal.Cachorros.Cachorro;
import com.arthurhenrique_Dev.CatOng.Infraestructure.Persistence.Entities.AnimalEntities.ECachorro;
import org.springframework.stereotype.Component;

@Component
public class CachorroMapper {

    public ECachorro toEntity(Cachorro cachorroDomain){
        ECachorro cachorroTraduzido = new ECachorro(
                cachorroDomain.getNome(),
                cachorroDomain.getIdade(),
                cachorroDomain.getRaça(),
                cachorroDomain.getAtividade(),
                cachorroDomain.getDescrição(),
                cachorroDomain.getSexo(),
                cachorroDomain.getPeso(),
                cachorroDomain.getFotos());
        return cachorroTraduzido;
    }
    public Cachorro toDomain(ECachorro entityCachorro){
        Cachorro cachorroTraduzido= new Cachorro(
                entityCachorro.getNome(),
                entityCachorro.getIdade(),
                entityCachorro.getRaça(),
                entityCachorro.getAtividade(),
                entityCachorro.getDescrição(),
                entityCachorro.getSexo(),

                entityCachorro.getPeso());
        return cachorroTraduzido;
    }
}
