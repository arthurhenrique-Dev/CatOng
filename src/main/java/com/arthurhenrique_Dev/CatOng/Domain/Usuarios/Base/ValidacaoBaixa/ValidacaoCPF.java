package com.arthurhenrique_Dev.CatOng.Domain.Usuarios.Base.ValidacaoBaixa;

public class ValidacaoCPF {

    public boolean ValidarCpf(String cpf) {
        if (cpf == null || !cpf.matches("\\d{11}")) {
            return false;
        }

        if (cpf.chars().distinct().count() == 1) {
            return false;
        }

        int[] digitos = cpf.chars().map(c -> c - '0').toArray();

        int sum = 0;
        for (int i = 0; i < 9; i++) {
            sum += digitos[i] * (10 - i);
        }
        int primeiroDigito = sum % 11 < 2 ? 0 : 11 - (sum % 11);

        sum = 0;
        for (int i = 0; i < 10; i++) {
            sum += digitos[i] * (11 - i);
        }
        int segundoDigito = sum % 11 < 2 ? 0 : 11 - (sum % 11);

        return digitos[9] == primeiroDigito && digitos[10] == segundoDigito;
    }
}
