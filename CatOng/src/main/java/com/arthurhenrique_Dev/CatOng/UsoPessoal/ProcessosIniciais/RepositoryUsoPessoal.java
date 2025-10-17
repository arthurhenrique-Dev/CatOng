package com.arthurhenrique_Dev.CatOng.UsoPessoal.ProcessosIniciais;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryUsoPessoal extends JpaRepository<ADMIN, Long> {
    ADMIN findByNome(String nome);
}
