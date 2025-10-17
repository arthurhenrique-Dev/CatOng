package com.arthurhenrique_Dev.CatOng.Infraestructure.Persistence.FrameworkRepository.UsuarioISpring.RepositorioEstrangeiroUGerenciamento;

import com.arthurhenrique_Dev.CatOng.Infraestructure.Persistence.Entities.UsuarioEntities.EUGerenciamento.EUGerenciamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface ISpringUGerenciamento extends JpaRepository<EUGerenciamento, Long> {
    EUGerenciamento findByNR(Long nr);
    UserDetails findByNome(String nome);

    Object getEUGerenciamentoByCpf(String cpf);

    EUGerenciamento findByCpf(String cpf);
}
