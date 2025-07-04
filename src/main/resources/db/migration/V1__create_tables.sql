CREATE SCHEMA IF NOT EXISTS "livet";

CREATE TABLE  IF NOT EXISTS "livet"."users"
(
    id         UUID PRIMARY KEY,
    first_name VARCHAR(100) NOT NULL,
    last_name  VARCHAR(100) NOT NULL,
    created_at TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP,
    deleted    BOOLEAN      NOT NULL DEFAULT FALSE
);
