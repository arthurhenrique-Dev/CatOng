package com.arthurhenrique_Dev.CatOng.Security.SecurityService;

import com.arthurhenrique_Dev.CatOng.Infraestructure.Persistence.Entities.UsuarioEntities.EUGerenciamento.EUGerenciamento;
import com.arthurhenrique_Dev.CatOng.Infraestructure.Persistence.FrameworkRepository.UsuarioISpring.RepositorioEstrangeiroUComum.ISpringUComum;
import com.arthurhenrique_Dev.CatOng.Infraestructure.Persistence.FrameworkRepository.UsuarioISpring.RepositorioEstrangeiroUGerenciamento.ISpringUGerenciamento;
import com.arthurhenrique_Dev.CatOng.UsoPessoal.ProcessosIniciais.RepositoryUsoPessoal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AutenticacaoService implements UserDetailsService {

    @Autowired
    ISpringUComum fRepositoryUComum;
    @Autowired
    ISpringUGerenciamento fRepositoryUGerenciamento;
    @Autowired
    RepositoryUsoPessoal repositoryUsoPessoal;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var comum = fRepositoryUComum.findById(username);
        if (comum.isPresent()) return comum.get();

        var ger = fRepositoryUGerenciamento.findByCpf(username);
        if (ger != null) return ger;

        var admin = repositoryUsoPessoal.findByNome(username);
        if (admin != null) return admin;

        throw new UsernameNotFoundException("usuário não encontrado");
    }
}
