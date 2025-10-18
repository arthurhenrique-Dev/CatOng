package com.arthurhenrique_Dev.CatOng.Security.Service;

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
        UserDetails user;
        if (fRepositoryUComum
                .findById(username)
                .isPresent()){
            user = fRepositoryUComum
                    .findById(username)
                    .get();
            return user;
        }
        EUGerenciamento recebido = fRepositoryUGerenciamento.findByCpf(username);
        if (recebido != null){
            user = recebido;
            return user;
        }
        if (repositoryUsoPessoal.findByNome(username)!=null){
            user = repositoryUsoPessoal.findByNome(username);
            return user;
        }
        throw new UsernameNotFoundException("usuário não encontrado");
    }
}
