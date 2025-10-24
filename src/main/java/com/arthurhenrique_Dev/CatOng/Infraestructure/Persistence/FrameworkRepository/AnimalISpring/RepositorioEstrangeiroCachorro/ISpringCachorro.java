package com.arthurhenrique_Dev.CatOng.Infraestructure.Persistence.FrameworkRepository.AnimalISpring.RepositorioEstrangeiroCachorro;

import com.arthurhenrique_Dev.CatOng.Domain.Animal.BaseAnimal.Atividade;
import com.arthurhenrique_Dev.CatOng.Infraestructure.Persistence.Entities.AnimalEntities.ECachorro;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface ISpringCachorro extends JpaRepository<ECachorro, Long> {
    List<ECachorro> findByNome(String nome, Pageable pageable);
    List<ECachorro> findAllByAtividade(Atividade atividade, Pageable pageable);
}
