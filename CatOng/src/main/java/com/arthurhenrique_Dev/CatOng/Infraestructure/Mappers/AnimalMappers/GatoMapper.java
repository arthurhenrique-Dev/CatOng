package com.arthurhenrique_Dev.CatOng.Infraestructure.Mappers.AnimalMappers;

import com.arthurhenrique_Dev.CatOng.Domain.Animal.Gatos.Gato;
import com.arthurhenrique_Dev.CatOng.Infraestructure.Persistence.Entities.AnimalEntities.EGato;
import org.springframework.stereotype.Component;

@Component
public class GatoMapper {

    public EGato toEntity(Gato gatoDomain){
        EGato gatoTraduzido = new EGato(
                gatoDomain.getNome(),
                gatoDomain.getIdade(),
                gatoDomain.getRaça(),
                gatoDomain.getAtividade(),
                gatoDomain.getDescrição(),
                gatoDomain.getSexo(),
                gatoDomain.getPeso());
        return gatoTraduzido;
    }
    public Gato toDomain(EGato entityGato){
        Gato gatoTraduzido= new Gato(
                entityGato.getNome(),
                entityGato.getIdade(),
                entityGato.getRaça(),
                entityGato.getAtividade(),
                entityGato.getDescrição(),
                entityGato.getSexo(),
                entityGato.getPeso(),
                entityGato.);
        return gatoTraduzido;
    }
}
