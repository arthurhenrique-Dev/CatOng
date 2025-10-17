package com.arthurhenrique_Dev.CatOng.UsoPessoal.ProcessosIniciais;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceUsoPessoal {

    @Autowired
    RepositoryUsoPessoal fRepository;

    @PostConstruct
    public void carregarAdmin(){
        ADMIN verificaExistencia = fRepository.findByNome("inicial");
        if(verificaExistencia != null){
            ADMIN inicial = new ADMIN(
                    1L,
                    "inicial",
                    "Aa@12345"
            );
            fRepository.save(inicial);
        }
    }
}
