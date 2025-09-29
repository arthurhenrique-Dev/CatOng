package com.arthurhenrique_Dev.CatOng.Infraestructure.Persistence.FrameworkRepository.UsuarioISpring;

import com.arthurhenrique_Dev.CatOng.Infraestructure.Persistence.Entities.UsuarioEntities.EUGerenciamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ISpringUGerenciamento extends JpaRepository<EUGerenciamento, Long> {
    EUGerenciamento findByNR(Long nr);
}
