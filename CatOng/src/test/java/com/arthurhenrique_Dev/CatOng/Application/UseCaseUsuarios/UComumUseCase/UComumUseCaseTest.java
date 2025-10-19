package com.arthurhenrique_Dev.CatOng.Application.UseCaseUsuarios.UComumUseCase;

import com.arthurhenrique_Dev.CatOng.Application.DTOs.Usuarios.Cadastro.DTORegistroUComum;
import com.arthurhenrique_Dev.CatOng.Domain.Usuarios.UComum.Endereco;
import com.arthurhenrique_Dev.CatOng.Domain.Usuarios.UComum.UComum;
import com.arthurhenrique_Dev.CatOng.Infraestructure.InfraMappers.UserMappers.UComumMappers.UComumMapper;
import com.arthurhenrique_Dev.CatOng.Infraestructure.Persistence.Entities.UsuarioEntities.EUComum.EUComum;
import com.arthurhenrique_Dev.CatOng.Infraestructure.Persistence.ImplementsRepositories.UsuarioImplements.ImplUComum.UComumUsecaseImpl;
import com.arthurhenrique_Dev.CatOng.Secreto.DadosParaTesteValido;
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

    //Arquivos em gitignore por conter dados pessoais, mas se trata de um DTORegistroUComum com meus dados, você pode fazer um com os seus
    DadosParaTesteValido dadosParaTesteValido =  new DadosParaTesteValido();

    @Test
    @DisplayName("Esperado sucesso")
    void getUComum() {


        this.createUser(dadosParaTesteValido.registroValido());

        Optional<UComum> usuarioEncontrado = this.uComumUseCase.getUComum(dadosParaTesteValido.registroValido().cpf());

        assertThat(usuarioEncontrado.isPresent()).isTrue();
    }

    private EUComum createUser(DTORegistroUComum dto){
        UComumMapper mapper = new UComumMapper();
        EUComum euComum = mapper.ValidacaoEInscricao(dto);
        this.em.persist(euComum);
        return euComum;
    }

    @Test
    void salvarComum() {
    }

    @Test
    void atualizarComum() {
    }

    @Test
    void removerComum() {
    }

    @Test
    void getUComuns() {
    }

    @Test
    void getUComunsByName() {
    }
}