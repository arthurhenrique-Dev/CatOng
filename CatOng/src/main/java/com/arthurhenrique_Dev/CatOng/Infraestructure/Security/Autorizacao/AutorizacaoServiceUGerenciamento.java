package com.arthurhenrique_Dev.CatOng.Infraestructure.Security.Autorizacao;

import com.arthurhenrique_Dev.CatOng.Infraestructure.Persistence.FrameworkRepository.UsuarioISpring.ISpringUGerenciamento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AutorizacaoServiceUGerenciamento implements UserDetailsService {

    @Autowired
    ISpringUGerenciamento fRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return fRepository.findByNome(username);
    }
}
