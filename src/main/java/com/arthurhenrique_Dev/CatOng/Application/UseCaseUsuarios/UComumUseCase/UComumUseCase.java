package com.arthurhenrique_Dev.CatOng.Application.UseCaseUsuarios.UComumUseCase;

import com.arthurhenrique_Dev.CatOng.Application.DTOs.Usuarios.Atualizacao.DTOAtualizacaoUComum;
import com.arthurhenrique_Dev.CatOng.Application.DTOs.Usuarios.Cadastro.DTORegistroUComum;
import com.arthurhenrique_Dev.CatOng.Application.DTOs.Usuarios.Retorno.DTORetornoUComum;
import com.arthurhenrique_Dev.CatOng.Domain.Usuarios.Repositorys.UComumRepository.UComumRepository;
import com.arthurhenrique_Dev.CatOng.Domain.Usuarios.UComum.UComum;

import java.util.List;
import java.util.Optional;

public class UComumUseCase {

    private final UComumRepository uComumRepository;

    public UComumUseCase(UComumRepository uComumRepository) {
        this.uComumRepository = uComumRepository;
    }
    public void salvarComum(DTORegistroUComum registroUComum) {
        uComumRepository.salvarUComum(registroUComum);
    }
    public void atualizarComum(String cpf, DTOAtualizacaoUComum dto){
        uComumRepository.atualizarUComum(cpf, dto);
    }
    public void removerComum(String cpf) {
        uComumRepository.removerUComum(cpf);
    }
    public Optional<DTORetornoUComum> getUComum(String cpf){
        return uComumRepository.getUComum(cpf);
    }
    public List<DTORetornoUComum> getUComuns(int page, int size) {
        return uComumRepository.getUComuns(page, size);
    }
    public List<DTORetornoUComum> getUComunsInativos(int page, int size) { return uComumRepository.getUComunsInativos(page, size); }
    public List<DTORetornoUComum> getUComunsByName(int page, int size, String nome) {
        return uComumRepository.getUComumsByName(page, size, nome);
    }
}
