CREATE TABLE IF NOT EXISTS usuario_comum (

    nome VARCHAR(300) NOT NULL,
    cpf VARCHAR(11) PRIMARY KEY NOT NULL,
    rg VARCHAR(9) NOT NULL,
    atividade VARCHAR(7) NOT NULL,
    permissao VARCHAR(13) NOT NULL,
    email VARCHAR(200) NOT NULL,
    cep VARCHAR(8) NOT NULL,
    logradouro VARCHAR(200) NOT NULL,
    complemento TEXT NOT NULL,
    bairro VARCHAR(100) NOT NULL,
    cidade VARCHAR(200) NOT NULL,
    numero INTEGER,
    telefone VARCHAR(11) NOT NULL
);