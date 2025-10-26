package com.arthurhenrique_Dev.CatOng.Infraestructure.InfraMappers.AnimalMappers.CachorroMapper;

import com.arthurhenrique_Dev.CatOng.Application.DTOs.Animais.DTOCadastroAnimal;
import com.arthurhenrique_Dev.CatOng.Controllers.TratamentoDeExcecoes.Excecoes.DadoIncorretoException;
import com.arthurhenrique_Dev.CatOng.Domain.Animal.BaseAnimal.Atividade;
import com.arthurhenrique_Dev.CatOng.Domain.Animal.Cachorros.Cachorro;
import com.arthurhenrique_Dev.CatOng.Infraestructure.Persistence.Entities.AnimalEntities.ECachorro;
import org.springframework.stereotype.Component;

@Component
public class CachorroMapper {

    public ECachorro toEntity(Cachorro cachorroDomain) {
        if (cachorroDomain.getNome() == null) throw new DadoIncorretoException("Insira o nome do gato");
        if (cachorroDomain.getIdade() == null) throw new DadoIncorretoException("Insira a idade do gato");
        if (cachorroDomain.getRaca() == null) throw new DadoIncorretoException("Insira a raça do gato (pode ser viralata)");
        if (cachorroDomain.getAtividade() == null) throw new DadoIncorretoException();
        if (cachorroDomain.getSexo() == null) throw new DadoIncorretoException("Insira o gênero do cachorroDomain");
        if (cachorroDomain.getPeso() == null) throw new DadoIncorretoException("Insira o peso do cachorroDomain");
        if (cachorroDomain.getTipoDeAnimal() == null) throw new DadoIncorretoException();
        if (cachorroDomain.getFotos() == null) throw new DadoIncorretoException("Insira as fotos do gato");
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

    public Cachorro toDomain(ECachorro entityCachorro) {
        Cachorro cachorroTraduzido = new Cachorro(
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
