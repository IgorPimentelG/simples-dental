CREATE TABLE tb_profissionais(
    id UUID UNIQUE NOT NULL,
    nome VARCHAR(255) NOT NULL,
    cargo VARCHAR(100) NOT NULL,
    nascimento TIMESTAMP NOT NULL,
    created_date TIMESTAMP NOT NULL,
    updated_date TIMESTAMP NULL,
    deleted_date TIMESTAMP NULL,

    CONSTRAINT pk_profissional PRIMARY KEY (id)
);