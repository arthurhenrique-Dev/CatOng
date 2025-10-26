package com.arthurhenrique_Dev.CatOng.Infraestructure.InfraMappers.UserMappers.UComumMappers;

import com.arthurhenrique_Dev.CatOng.Application.DTOs.Usuarios.Cadastro.DTORegistroUComum;
import com.arthurhenrique_Dev.CatOng.Application.DTOs.Usuarios.Retorno.DTORetornoUComum;
import com.arthurhenrique_Dev.CatOng.Controllers.TratamentoDeExcecoes.Excecoes.DadoIncorretoException;
import com.arthurhenrique_Dev.CatOng.Domain.Usuarios.Base.Atividade;
import com.arthurhenrique_Dev.CatOng.Domain.Usuarios.UComum.Endereco;
import com.arthurhenrique_Dev.CatOng.Domain.Usuarios.UComum.UComum;
import com.arthurhenrique_Dev.CatOng.Infraestructure.Persistence.Entities.UsuarioEntities.EUComum.EUComum;
import com.arthurhenrique_Dev.CatOng.Infraestructure.Persistence.Entities.UsuarioEntities.EUComum.PersistenceEndereco;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class UComumMapper {


    public EUComum toEntity(UComum uComumDomain) {

        if (uComumDomain.getNome() == null) throw new DadoIncorretoException("Insira seu nome real");
        if (uComumDomain.getCpf() == null) throw new DadoIncorretoException("Cpf inválido");
        if (uComumDomain.getRG() == null) throw new DadoIncorretoException("RG inválido");
        if (uComumDomain.getAtividade() == null) throw new DadoIncorretoException();
        if (uComumDomain.getPermissao() == null) throw new DadoIncorretoException();
        if (uComumDomain.getEmail() == null) throw new DadoIncorretoException("Email inválido");
        if (uComumDomain.getSenha() == null) throw new DadoIncorretoException("Senha inválida");
        if (uComumDomain.getDataNascimento() == null) throw new DadoIncorretoException("Idade inválida");
        if (uComumDomain.getTelefone() == null) throw new DadoIncorretoException("Telefone inválido");

        String senhaCriptografada = new BCryptPasswordEncoder().encode(uComumDomain.getSenha());

        EUComum euComumTraduzido = new EUComum(
                uComumDomain.getNome(),
                uComumDomain.getCpf(),
                uComumDomain.getRG(),
                uComumDomain.getAtividade(),
                uComumDomain.getPermissao(),
                uComumDomain.getEmail(),
                senhaCriptografada,
                uComumDomain.getTelefone(),
                this.toPersistenceEndereco(uComumDomain.getEndereco()),
                uComumDomain.getDataNascimento());
        return euComumTraduzido;
    }

    public DTORetornoUComum toDtoReturn(EUComum euComum) {
        DTORetornoUComum dto = new DTORetornoUComum(
                euComum.getNome(),
                euComum.getCpf(),
                euComum.getRG(),
                euComum.getAtividade(),
                euComum.getEmail(),
                euComum.getTelefone(),
                LocalDate.now().getYear() - euComum.getDataNascimento().getYear(),
                this.toDomainEndereco(euComum.getEndereco()));
        return dto;
    }

    public UComum DTORegisterToDomain(DTORegistroUComum DTOuComum) {
        UComum uComumTraduzido = new UComum(
                DTOuComum.nome(),
                DTOuComum.cpf(),
                DTOuComum.rg(),
                Atividade.ATIVO,
                DTOuComum.email(),
                DTOuComum.senha(),
                DTOuComum.telefone(),
                DTOuComum.endereco(),
                DTOuComum.dataDeNascimento());
        return uComumTraduzido;
    }

    public PersistenceEndereco toPersistenceEndereco(Endereco enderecoDomain) {
        if (enderecoDomain.getCep() == null) throw new DadoIncorretoException("CEP inválido");
        PersistenceEndereco enderecoTraduzido = new PersistenceEndereco(
                enderecoDomain.getCep(),
                enderecoDomain.getLogradouro(),
                enderecoDomain.getComplemento(),
                enderecoDomain.getBairro(),
                enderecoDomain.getCidade(),
                enderecoDomain.getNumero()
        );
        return enderecoTraduzido;
    }

    public Endereco toDomainEndereco(PersistenceEndereco enderecoPersistence) {
        Endereco enderecoTraduzido = new Endereco(
                enderecoPersistence.getCep(),
                enderecoPersistence.getLogradouro(),
                enderecoPersistence.getComplemento(),
                enderecoPersistence.getBairro(),
                enderecoPersistence.getCidade(),
                enderecoPersistence.getNumero()
        );
        return enderecoTraduzido;
    }

    public Atividade validarAtividade(Atividade atividade) {
        if (atividade != Atividade.ATIVO && atividade != Atividade.INATIVO)
            throw new IllegalArgumentException("Erro na atividade");
        return atividade;
    }
}
