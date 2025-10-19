package com.arthurhenrique_Dev.CatOng.Application.UseCaseUsuarios.UComumUseCase;

import com.arthurhenrique_Dev.CatOng.Application.DTOs.Usuarios.Cadastro.DTORegistroUComum;
import com.arthurhenrique_Dev.CatOng.Domain.Usuarios.UComum.Endereco;
import com.arthurhenrique_Dev.CatOng.Domain.Usuarios.UComum.UComum;
import com.arthurhenrique_Dev.CatOng.Infraestructure.InfraMappers.UserMappers.UComumMappers.UComumMapper;
import com.arthurhenrique_Dev.CatOng.Infraestructure.Persistence.Entities.UsuarioEntities.EUComum.EUComum;
import com.arthurhenrique_Dev.CatOng.Infraestructure.Persistence.ImplementsRepositories.UsuarioImplements.ImplUComum.UComumUsecaseImpl;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.time.Month;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
class UComumUseCaseTest {

    @Autowired
    EntityManager em;

    @Autowired
    UComumUseCase uComumUseCase;

    @Test
    @DisplayName("Esperado sucesso")
    void getUComum() {


        Endereco endereco = new Endereco();
        DTORegistroUComum dto = new DTORegistroUComum();

        System.out.println(dto.senha());

        this.createUser(dto);

        Optional<UComum> usuarioEncontrado = this.uComumUseCase.getUComum(cpf);

        assertThat(usuarioEncontrado.isPresent()).isTrue();
    }

    private EUComum createUser(DTORegistroUComum dto){
        UComumMapper mapper = new UComumMapper();
        EUComum euComum = mapper.ValidacaoEInscricao(dto);
        this.em.persist(euComum);
        return euComum;
    }
}