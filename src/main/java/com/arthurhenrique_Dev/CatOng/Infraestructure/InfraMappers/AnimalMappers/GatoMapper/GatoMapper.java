package com.arthurhenrique_Dev.CatOng.Infraestructure.InfraMappers.AnimalMappers.GatoMapper;

import com.arthurhenrique_Dev.CatOng.Application.DTOs.Animais.DTOCadastroAnimal;
import com.arthurhenrique_Dev.CatOng.Controllers.TratamentoDeExcecoes.Excecoes.DadoIncorretoException;
import com.arthurhenrique_Dev.CatOng.Domain.Animal.BaseAnimal.Atividade;
import com.arthurhenrique_Dev.CatOng.Domain.Animal.Gatos.Gato;
import com.arthurhenrique_Dev.CatOng.Infraestructure.Persistence.Entities.AnimalEntities.EGato;
import org.springframework.stereotype.Component;

@Component
public class GatoMapper {

    public EGato toEntity(Gato gatoDomain) {
        if (gatoDomain.getNome() == null) throw new DadoIncorretoException("Insira o nome do Gato");
        if (gatoDomain.getIdade() == null) throw new DadoIncorretoException("Insira a idade do Gato");
        if (gatoDomain.getRaca() == null) throw new DadoIncorretoException("Insira a raça do Gato (pode ser viralata)");
        if (gatoDomain.getAtividade() == null) throw new DadoIncorretoException();
        if (gatoDomain.getSexo() == null) throw new DadoIncorretoException("Insira o gênero do gato");
        if (gatoDomain.getPeso() == null) throw new DadoIncorretoException("Insira o peso do gato");
        if (gatoDomain.getTipoDeAnimal() == null) throw new DadoIncorretoException();
        if (gatoDomain.getFotos() == null) throw new DadoIncorretoException("Insira as fotos do Gato");
        EGato gatoTraduzido = new EGato(
                gatoDomain.getNome(),
                gatoDomain.getIdade(),
                gatoDomain.getRaca(),
                gatoDomain.getAtividade(),
                gatoDomain.getDescricao(),
                gatoDomain.getSexo(),
                gatoDomain.getPeso(),
                gatoDomain.getTipoDeAnimal(),
                gatoDomain.getFotos());
        return gatoTraduzido;
    }

    public Gato toDomain(EGato entityGato) {
        Gato gatoTraduzido = new Gato(
                entityGato.getNome(),
                entityGato.getIdade(),
                entityGato.getRaca(),
                entityGato.getAtividade(),
                entityGato.getDescricao(),
                entityGato.getSexo(),
                entityGato.getPeso(),
                entityGato.getFotos());
        return gatoTraduzido;
    }

    public Gato DtoToDomain(DTOCadastroAnimal dto) {
        Gato gatoTraduzido = new Gato(
                dto.nome(),
                dto.idade(),
                dto.raca(),
                Atividade.ATIVO,
                dto.descricao(),
                dto.sexo(),
                dto.peso(),
                dto.fotos()
        );
        return gatoTraduzido;
    }
}
