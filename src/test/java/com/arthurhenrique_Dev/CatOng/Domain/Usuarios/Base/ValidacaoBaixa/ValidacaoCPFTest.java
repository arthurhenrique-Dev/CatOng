package com.arthurhenrique_Dev.CatOng.Domain.Usuarios.Base.ValidacaoBaixa;

import com.arthurhenrique_Dev.CatOng.DadosParaTeste.DadosPessoa.DadosParaTesteValido;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.Assertions.assertThat;

class ValidacaoCPFTest {

    private DadosParaTesteValido dtv;

    private ValidacaoCPF validacao;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        validacao = new ValidacaoCPF();
        dtv = new DadosParaTesteValido();
    }

    @Test
    void validarCpf() {

        var cpfValido = dtv.registroValidoUComum().cpf();

        var validando = validacao.ValidarCpf(cpfValido);

        assertThat(validando).isTrue();
    }

    @Test
    void naoValidarCPF() {

        var cpfInvalido = "48017203132";

        var validando = validacao.ValidarCpf(cpfInvalido);

        assertThat(validando).isFalse();
    }
}