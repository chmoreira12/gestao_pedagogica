CREATE TABLE coordenadores (
    id UUID PRIMARY KEY,
    nome VARCHAR(150) NOT NULL,
    cpf VARCHAR(11) NOT NULL,
    email VARCHAR(255) NOT NULL,
    data_cadastro TIMESTAMP WITH TIME ZONE NOT NULL,
    CONSTRAINT uk_coordenadores_cpf UNIQUE (cpf),
    CONSTRAINT ck_coordenadores_cpf_formato CHECK (cpf ~ '^[0-9]{11}$')
);

CREATE UNIQUE INDEX uk_coordenadores_email_lower ON coordenadores (lower(email));
