CREATE IF NOT EXISTS SCHEMA "livet";

CREATE TABLE "livet"."users"
(
    id         SERIAL PRIMARY KEY,
    name       VARCHAR(100) NOT NULL,
    created_at TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP,
    deleted    BOOLEAN      NOT NULL DEFAULT FALSE
);
