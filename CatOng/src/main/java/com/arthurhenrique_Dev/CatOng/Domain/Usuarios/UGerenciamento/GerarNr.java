package com.arthurhenrique_Dev.CatOng.Domain.Usuarios.UGerenciamento;

import java.time.LocalDate;

public class GerarNr {

    public Long NrGerado(String cpf){
        Long NrGerado = Long.parseLong("300" + (((Integer.parseInt(cpf.substring(cpf.length() - 3)) * 9) /4) + 37));
        return NrGerado;
    }
}
