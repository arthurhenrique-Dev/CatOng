package com.arthurhenrique_Dev.CatOng.Infraestructure.InfraMappers.AnimalMappers.GatoMapper;

import com.arthurhenrique_Dev.CatOng.Domain.Animal.Gatos.Gato;
import com.arthurhenrique_Dev.CatOng.Infraestructure.Persistence.Entities.AnimalEntities.EGato;
import org.springframework.stereotype.Component;

@Component
public class GatoMapper {

    public EGato toEntity(Gato gatoDomain){
        EGato gatoTraduzido = new EGato(
                gatoDomain.getNome(),
                gatoDomain.getIdade(),
                gatoDomain.getRaca(),
                gatoDomain.getAtividade(),
                gatoDomain.getDescrição(),
                gatoDomain.getSexo(),
                gatoDomain.getPeso(),
                gatoDomain.getTipoDeAnimal(),
                gatoDomain.getFotos());
        return gatoTraduzido;
    }
    public Gato toDomain(EGato entityGato){
        Gato gatoTraduzido= new Gato(
                entityGato.getNome(),
                entityGato.getIdade(),
                entityGato.getRaca(),
                entityGato.getAtividade(),
                entityGato.getDescrição(),
                entityGato.getSexo(),
                entityGato.getPeso(),
                entityGato.getFotos());
        return gatoTraduzido;
    }
}
