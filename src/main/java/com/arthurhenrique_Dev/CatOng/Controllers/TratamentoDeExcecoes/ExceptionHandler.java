package com.arthurhenrique_Dev.CatOng.Controllers.TratamentoDeExcecoes;

import com.arthurhenrique_Dev.CatOng.Controllers.TratamentoDeExcecoes.Excecoes.Animais.AnimaisAtualizacaoInvalidaException;
import com.arthurhenrique_Dev.CatOng.Controllers.TratamentoDeExcecoes.Excecoes.Animais.Cachorros.CachorroInexistenteException;
import com.arthurhenrique_Dev.CatOng.Controllers.TratamentoDeExcecoes.Excecoes.Animais.Gatos.GatoInexistenteException;
import com.arthurhenrique_Dev.CatOng.Controllers.TratamentoDeExcecoes.Excecoes.DadoIncorretoException;
import com.arthurhenrique_Dev.CatOng.Controllers.TratamentoDeExcecoes.Excecoes.Usuarios.AtualizacaoInvalidaException;
import com.arthurhenrique_Dev.CatOng.Controllers.TratamentoDeExcecoes.Excecoes.Usuarios.UsuarioExistenteException;
import com.arthurhenrique_Dev.CatOng.Controllers.TratamentoDeExcecoes.Excecoes.Usuarios.UsuarioInexistenteException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(UsuarioExistenteException.class)
    private ResponseEntity<ErrorMessage> UsuarioExistenteException(UsuarioExistenteException exception) {
        ErrorMessage err = new ErrorMessage(exception.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(err);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(UsuarioInexistenteException.class)
    private ResponseEntity<ErrorMessage> UsuarioInexistenteException(UsuarioInexistenteException exception) {
        ErrorMessage err = new ErrorMessage(exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(AtualizacaoInvalidaException.class)
    private ResponseEntity<ErrorMessage> AtualizacaoInvalidaException(AtualizacaoInvalidaException exception) {
        ErrorMessage err = new ErrorMessage(exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(DadoIncorretoException.class)
    private ResponseEntity<ErrorMessage> DadoNaoInseridoException(DadoIncorretoException exception) {
        ErrorMessage err = new ErrorMessage(exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(CachorroInexistenteException.class)
    private ResponseEntity<ErrorMessage> CachorroInexistenteException(CachorroInexistenteException exception) {
        ErrorMessage err = new ErrorMessage(exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(GatoInexistenteException.class)
    private ResponseEntity<ErrorMessage> GatoInexistenteException(GatoInexistenteException exception) {
        ErrorMessage err = new ErrorMessage(exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(AnimaisAtualizacaoInvalidaException.class)
    private ResponseEntity<ErrorMessage> AnimaisAtualizacaoInvalidaException(AtualizacaoInvalidaException exception) {
        ErrorMessage err = new ErrorMessage(exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }
    @org.springframework.web.bind.annotation.ExceptionHandler(UsuarioBanidoException.class)
    private ResponseEntity<ErrorMessage> UsuarioBanidoException(UsuarioBanidoException exception) {
        ErrorMessage err = new ErrorMessage(exception.getMessage());
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(err);
    }
}

