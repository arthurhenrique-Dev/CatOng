CREATE TABLE IF NOT EXISTS usuario_gerenciamento(

    nr NUMERIC PRIMARY KEY NOT NULL,
    nome VARCHAR(300) NOT NULL,
    cpf VARCHAR(11) NOT NULL,
    rg VARCHAR(9) NOT NULL,
    atividade VARCHAR(7) NOT NULL,
    permissao VARCHAR(13) NOT NULL,
    email VARCHAR(200) NOT NULL,
    telefone VARCHAR(11) NOT NULL

);