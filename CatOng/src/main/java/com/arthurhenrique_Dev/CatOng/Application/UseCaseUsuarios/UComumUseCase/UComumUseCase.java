package com.arthurhenrique_Dev.CatOng.Application.UseCaseUsuarios.UComumUseCase;

import com.arthurhenrique_Dev.CatOng.Application.DTOs.DTORegistroUComum;
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
    public void atualizarComum(Long id, UComum uComum){
        uComumRepository.atualizarUComum(id, uComum);
    }
    public void removerComum(Long id) {
        uComumRepository.removerUComum(id);
    }
    public List<UComum> getUComuns(int page, int size) {
        return uComumRepository.getUComuns(page, size);
    }
    public List<UComum> getUComunsByName(int page, int size, String nome) {
        return uComumRepository.getUComumsByName(page, size, nome);
    }
    public Optional<UComum> getUComumById(Long id) {
        return uComumRepository.getUComumById(id);
    }
}
