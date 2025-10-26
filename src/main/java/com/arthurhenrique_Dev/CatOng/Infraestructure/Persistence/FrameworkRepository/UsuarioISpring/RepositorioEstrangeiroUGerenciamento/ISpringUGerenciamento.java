package com.arthurhenrique_Dev.CatOng.Infraestructure.Persistence.FrameworkRepository.UsuarioISpring.RepositorioEstrangeiroUGerenciamento;

import com.arthurhenrique_Dev.CatOng.Application.DTOs.Usuarios.Retorno.DTORetornoUGerenciamento;
import com.arthurhenrique_Dev.CatOng.Domain.Usuarios.Base.Atividade;
import com.arthurhenrique_Dev.CatOng.Infraestructure.Persistence.Entities.UsuarioEntities.EUGerenciamento.EUGerenciamento;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public interface ISpringUGerenciamento extends JpaRepository<EUGerenciamento, Long> {
    EUGerenciamento findByNR(Long nr);

    EUGerenciamento findByCpf(String cpf);

    List<EUGerenciamento> findAllByAtividade(Atividade atividade, Pageable pageable);
}
