package com.arthurhenrique_Dev.CatOng.Configuration;

import com.arthurhenrique_Dev.CatOng.Application.UseCaseAnimal.UCachorroUseCase.CachorroUseCase;
import com.arthurhenrique_Dev.CatOng.Application.UseCaseAnimal.UGatoUseCase.GatoUseCase;
import com.arthurhenrique_Dev.CatOng.Application.UseCaseUsuarios.UComumUseCase.UComumUseCase;
import com.arthurhenrique_Dev.CatOng.Application.UseCaseUsuarios.UGerenciamentoUseCase.UGerenciamentoUseCase;
import com.arthurhenrique_Dev.CatOng.Domain.Animal.Repositorys.CachorroRepo.CachorroRepo;
import com.arthurhenrique_Dev.CatOng.Domain.Animal.Repositorys.GatoRepo.GatoRepo;
import com.arthurhenrique_Dev.CatOng.Domain.Usuarios.Repositorys.UComumRepository.UComumRepository;
import com.arthurhenrique_Dev.CatOng.Domain.Usuarios.Repositorys.UGerenciamentoRepository.UGerenciamentoRepository;
import com.arthurhenrique_Dev.CatOng.Infraestructure.InfraMappers.AnimalMappers.CachorroMapper.CachorroMapper;
import com.arthurhenrique_Dev.CatOng.Infraestructure.InfraMappers.AnimalMappers.GatoMapper.GatoMapper;
import com.arthurhenrique_Dev.CatOng.Infraestructure.InfraMappers.UserMappers.UComumMappers.UComumMapper;
import com.arthurhenrique_Dev.CatOng.Infraestructure.InfraMappers.UserMappers.UGerenciamentoMapper.UGerenciamentoMapper;
import com.arthurhenrique_Dev.CatOng.Infraestructure.Persistence.FrameworkRepository.AnimalISpring.RepositoriEstrangeiroGato.ISpringGato;
import com.arthurhenrique_Dev.CatOng.Infraestructure.Persistence.FrameworkRepository.AnimalISpring.RepositorioEstrangeiroCachorro.ISpringCachorro;
import com.arthurhenrique_Dev.CatOng.Infraestructure.Persistence.FrameworkRepository.UsuarioISpring.RepositorioEstrangeiroUComum.ISpringUComum;
import com.arthurhenrique_Dev.CatOng.Infraestructure.Persistence.FrameworkRepository.UsuarioISpring.RepositorioEstrangeiroUGerenciamento.ISpringUGerenciamento;
import com.arthurhenrique_Dev.CatOng.Infraestructure.Persistence.ImplementsRepositories.AnimalImplements.ImplCachorro.CachorroUseCaseImpl;
import com.arthurhenrique_Dev.CatOng.Infraestructure.Persistence.ImplementsRepositories.AnimalImplements.ImplGato.GatoUseCaseImpl;
import com.arthurhenrique_Dev.CatOng.Infraestructure.Persistence.ImplementsRepositories.UsuarioImplements.ImplUComum.UComumUsecaseImpl;
import com.arthurhenrique_Dev.CatOng.Infraestructure.Persistence.ImplementsRepositories.UsuarioImplements.ImplUGerenciamento.UGerenciamentoUseCaseImpl;
import org.springframework.context.annotation.Bean;

@org.springframework.context.annotation.Configuration
public class Configuration {

    @Bean
    UComumUseCase uComumUseCase(UComumRepository uComumRepository) {
        return new UComumUseCase(uComumRepository);
    }

    @Bean
    UComumRepository uComumRepository(ISpringUComum ispringUComum, UComumMapper uComumMapper) {
        return new UComumUsecaseImpl(ispringUComum, uComumMapper);
    }

    @Bean
    UGerenciamentoUseCase uGerenciamentoUseCase(UGerenciamentoRepository uGerenciamentoRepository) {
        return new UGerenciamentoUseCase(uGerenciamentoRepository);
    }

    @Bean
    UGerenciamentoRepository uGerenciamentoRepository(ISpringUGerenciamento iSpringUGerenciamento, UGerenciamentoMapper uGerenciamentoMapper) {
        return new UGerenciamentoUseCaseImpl(iSpringUGerenciamento, uGerenciamentoMapper);
    }

    @Bean
    GatoUseCase gatoUseCase(GatoRepo gatoRepository) {
        return new GatoUseCase(gatoRepository);
    }

    @Bean
    GatoRepo gatoRepository(ISpringGato ispringGato, GatoMapper gatoMapper) {
        return new GatoUseCaseImpl(ispringGato, gatoMapper);
    }

    @Bean
    CachorroUseCase cachorroUseCase(CachorroRepo cachorroRepository) {
        return new CachorroUseCase(cachorroRepository);
    }

    @Bean
    CachorroRepo cachorroRepository(ISpringCachorro iSpringCachorro, CachorroMapper cachorroMapper) {
        return new CachorroUseCaseImpl(iSpringCachorro, cachorroMapper);
    }
}