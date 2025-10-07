package com.arthurhenrique_Dev.CatOng.Domain.Usuarios.Repositorys.UComumRepository;

import com.arthurhenrique_Dev.CatOng.Application.DTOs.DTORegistroUComum;
import com.arthurhenrique_Dev.CatOng.Domain.Usuarios.UComum.UComum;

import java.util.List;
import java.util.Optional;

public interface UComumRepository {

    void salvarUComum(DTORegistroUComum uComum);
    void removerUComum(Long id);
    void atualizarUComum(Long id, UComum uComum);
    Optional<UComum> getUComumById(Long id);
    List<UComum> getUComuns(int page, int size);
    List<UComum> getUComumsByName(int page, int size, String nome);
}
