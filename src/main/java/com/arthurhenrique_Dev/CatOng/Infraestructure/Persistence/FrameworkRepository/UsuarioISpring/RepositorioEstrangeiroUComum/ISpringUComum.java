package com.arthurhenrique_Dev.CatOng.Infraestructure.Persistence.FrameworkRepository.UsuarioISpring.RepositorioEstrangeiroUComum;

import com.arthurhenrique_Dev.CatOng.Infraestructure.Persistence.Entities.UsuarioEntities.EUComum.EUComum;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Range;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.Optional;

public interface ISpringUComum extends JpaRepository<EUComum, String> {
    List<EUComum> getEuComumsByNome(String nome, Pageable pageable);
}
