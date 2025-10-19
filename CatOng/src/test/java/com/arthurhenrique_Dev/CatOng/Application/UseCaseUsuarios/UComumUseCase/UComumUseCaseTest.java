package com.arthurhenrique_Dev.CatOng.Application.UseCaseUsuarios.UComumUseCase;

import com.arthurhenrique_Dev.CatOng.Application.DTOs.Usuarios.Cadastro.DTORegistroUComum;
import com.arthurhenrique_Dev.CatOng.Application.DTOs.Usuarios.Retorno.DTORetornoUComum;
import com.arthurhenrique_Dev.CatOng.Domain.Usuarios.Base.Atividade;
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
import java.util.Objects;
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

    //DadosParaTesteValido em gitignore por conter dados pessoais, mas se trata de um DTORegistroUComum com meus dados, você pode fazer um com os seus
    DadosParaTesteValido dtoParaTesteValido =  new DadosParaTesteValido();

    @Test
    @DisplayName("Esperado sucesso ao encontrar usuário")
    void getUComum() {

        this.createUser(dtoParaTesteValido.registroValido());

        Optional<DTORetornoUComum> usuarioEncontrado = this.uComumUseCase.getUComum(dtoParaTesteValido.registroValido().cpf());

        assertThat(usuarioEncontrado.isPresent()).isTrue();
    }

    @Test
    @DisplayName("Sucesso ao salvar usuário")
    void salvarComum() {

        this.uComumUseCase.salvarComum(dtoParaTesteValido.registroValido());

        Optional<DTORetornoUComum> usuarioSalvo = this.uComumUseCase.getUComum(
                dtoParaTesteValido.registroValido().cpf());
        assertThat(usuarioSalvo.isPresent()).isTrue();

    }

    @Test
    @DisplayName("Atualizacao com sucesso")
    void atualizarComum() {

        this.createUser(dtoParaTesteValido.registroValido());

        Optional<DTORetornoUComum> usuarioAntigo = this.uComumUseCase.getUComum(dtoParaTesteValido.registroValido().cpf());

        this.uComumUseCase.atualizarComum(dtoParaTesteValido.registroValido().cpf(), dtoParaTesteValido.atualizacaoValido());

        Optional<DTORetornoUComum> usuarioAtualizado = this.uComumUseCase.getUComum(dtoParaTesteValido.registroValido().cpf());

        assertThat(usuarioAntigo).isNotEqualTo(usuarioAtualizado);
    }

    @Test
    void removerComum() {

        this.createUser(dtoParaTesteValido.registroValido());

        this.uComumUseCase.removerComum(dtoParaTesteValido.registroValido().cpf());

        Optional<DTORetornoUComum> usuarioDeletado = this.uComumUseCase.getUComum(dtoParaTesteValido.registroValido().cpf());

        assertThat(usuarioDeletado)
                .isPresent()
                .hasValueSatisfying(usuarioDeletado1 -> assertThat(usuarioDeletado1.atividade()).isEqualTo(Atividade.INATIVO));
    }

    @Test
    void getUComuns() {
    }

    @Test
    void getUComunsByName() {
    }
    private EUComum createUser(DTORegistroUComum dto){
        UComumMapper mapper = new UComumMapper();
        EUComum euComum = (
                mapper.toEntity(
                        mapper.DTORegisterToDomain(dto)
                )
        );
        this.em.persist(euComum);
        return euComum;
    }
}