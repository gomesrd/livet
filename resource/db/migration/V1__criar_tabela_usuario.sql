CREATE TABLE usuario
(
    id       SERIAL PRIMARY KEY,
    nome     VARCHAR(100) NOT NULL,
    username VARCHAR(50)  NOT NULL UNIQUE,
    senha    VARCHAR(100) NOT NULL
);
