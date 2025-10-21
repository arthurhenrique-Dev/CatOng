package com.arthurhenrique_Dev.CatOng.Application.UseCaseUsuarios.UComumUseCase;

import com.arthurhenrique_Dev.CatOng.Application.DTOs.Usuarios.Cadastro.DTORegistroUComum;
import com.arthurhenrique_Dev.CatOng.Application.DTOs.Usuarios.Retorno.DTORetornoUComum;
import com.arthurhenrique_Dev.CatOng.Domain.Usuarios.Base.Atividade;
import com.arthurhenrique_Dev.CatOng.Infraestructure.InfraMappers.UserMappers.UComumMappers.UComumMapper;
import com.arthurhenrique_Dev.CatOng.Infraestructure.Persistence.Entities.UsuarioEntities.EUComum.EUComum;
import com.arthurhenrique_Dev.CatOng.DadosParaTeste.DadosPessoa.DadosParaTesteValido;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
class UComumUseCaseAndIntegrationTest {

    @Autowired
    EntityManager em;

    @Autowired
    UComumUseCase uComumUseCase;

    //DadosParaTesteValido em gitignore por conter dados pessoais, mas se trata de um DTORegistroUComum com meus dados, você pode fazer um com os seus
    DadosParaTesteValido dtv =  new DadosParaTesteValido();

    @Test
    @DisplayName("Esperado sucesso ao encontrar usuário")
    void getUComum() {

        this.createUser(dtv.registroValidoUComum());

        Optional<DTORetornoUComum> usuarioEncontrado = this.uComumUseCase.getUComum(dtv.registroValidoUComum().cpf());

        assertThat(usuarioEncontrado.isPresent()).isTrue();

    }

    @Test
    @DisplayName("Sucesso ao salvar usuário")
    void salvarComum() {

        this.uComumUseCase.salvarComum(dtv.registroValidoUComum());

        Optional<DTORetornoUComum> usuarioSalvo = this.uComumUseCase.getUComum(
                dtv.registroValidoUComum().cpf());
        assertThat(usuarioSalvo.isPresent()).isTrue();

    }

    @Test
    @DisplayName("Atualizacao com sucesso")
    void atualizarComum() {

        this.createUser(dtv.registroValidoUComum());

        Optional<DTORetornoUComum> usuarioAntigo = this.uComumUseCase.getUComum(dtv.registroValidoUComum().cpf());

        this.uComumUseCase.atualizarComum(dtv.registroValidoUComum().cpf(), dtv.atualizacaoValidoUComum());

        Optional<DTORetornoUComum> usuarioAtualizado = this.uComumUseCase.getUComum(dtv.registroValidoUComum().cpf());

        assertThat(usuarioAntigo).isNotEqualTo(usuarioAtualizado);
    }

    @Test
    void removerComum() {

        this.createUser(dtv.registroValidoUComum());

        this.uComumUseCase.removerComum(dtv.registroValidoUComum().cpf());

        Optional<DTORetornoUComum> usuarioDeletado = this.uComumUseCase.getUComum(dtv.registroValidoUComum().cpf());

        assertThat(usuarioDeletado)
                .isPresent()
                .hasValueSatisfying(usuarioDeletado1 -> assertThat(usuarioDeletado1.atividade()).isEqualTo(Atividade.INATIVO));
    }

    @Test
    void getUComuns() {

        this.createUser(dtv.registroValidoUComum());

        List<DTORetornoUComum> retorno = this.uComumUseCase.getUComuns(0, 10);

        assertThat(retorno)
                .isNotEmpty()
                .hasSizeGreaterThanOrEqualTo(1);
    }


    @Test
    void getUComunsByName() {

        this.createUser(dtv.registroValidoUComum());

        List<DTORetornoUComum> retorno = this.uComumUseCase.getUComunsByName(0,10, dtv.registroValidoUComum().nome());

        assertThat(retorno)
                .isNotEmpty()
                .hasSizeGreaterThanOrEqualTo(1);
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