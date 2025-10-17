CREATE TABLE IF NOT EXISTS cachorro(

    id NUMERIC PRIMARY KEY NOT NULL,
    nome VARCHAR(300) NOT NULL,
    idade SMALLINT CHECK (idade BETWEEN 0 AND 99) NOT NULL,
    raca VARCHAR (100),
    descricao TEXT,
    atividade VARCHAR(7) NOT NULL,
    peso NUMERIC(4,2),
    fotos TEXT,
    tipo_de_animal VARCHAR(8) NOT NULL
);