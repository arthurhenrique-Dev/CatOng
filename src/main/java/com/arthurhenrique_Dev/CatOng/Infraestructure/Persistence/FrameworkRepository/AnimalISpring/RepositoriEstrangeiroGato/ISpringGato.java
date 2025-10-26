package com.arthurhenrique_Dev.CatOng.Infraestructure.Persistence.FrameworkRepository.AnimalISpring.RepositoriEstrangeiroGato;

import com.arthurhenrique_Dev.CatOng.Domain.Animal.BaseAnimal.Atividade;
import com.arthurhenrique_Dev.CatOng.Infraestructure.Persistence.Entities.AnimalEntities.ECachorro;
import com.arthurhenrique_Dev.CatOng.Infraestructure.Persistence.Entities.AnimalEntities.EGato;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ISpringGato extends JpaRepository<EGato, Long> {
    List<EGato> getGatoByNome(String nome, Pageable pageable);

    List<EGato> findAllByAtividade(Atividade atividade, Pageable pageable);

}
