CREATE TABLE tb_contatos(
    id UUID UNIQUE NOT NULL,
    nome VARCHAR(255) NOT NULL,
    contato VARCHAR(255) NOT NULL,
    profissional_id UUID NOT NULL,
    created_date TIMESTAMP NOT NULL,
    updated_date TIMESTAMP NULL,
    deleted_date TIMESTAMP NULL,

    CONSTRAINT pk_contato PRIMARY KEY (id),
    CONSTRAINT fk_profissional FOREIGN KEY (profissional_id) REFERENCES tb_profissionais(id)
);