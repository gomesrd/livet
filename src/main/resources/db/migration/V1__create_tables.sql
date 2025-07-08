CREATE SCHEMA IF NOT EXISTS "livet";

CREATE TABLE  IF NOT EXISTS "livet"."users"
(
    "id"         UUID PRIMARY KEY,
    "first_name" VARCHAR(100) NOT NULL,
    "last_name"  VARCHAR(100) NOT NULL,
    "role"       VARCHAR(50)  NOT NULL,
    "email"      VARCHAR(255) NOT NULL UNIQUE,
    "external_id" VARCHAR(255) UNIQUE,
    "created_at" TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    "updated_at" TIMESTAMP,
    "deleted"    BOOLEAN      NOT NULL DEFAULT FALSE
);

CREATE TABLE IF NOT EXISTS "livet"."patients_clinical_records"
(
    id          UUID PRIMARY KEY,
    patient_id UUID NOT NULL,
    patient_name VARCHAR(255) NOT NULL,
    user_id    UUID NOT NULL,
    user_name  VARCHAR(255) NOT NULL,
    clinical_record_data JSONB NOT NULL,
    created_at  TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at  TIMESTAMP,
    deleted     BOOLEAN NOT NULL DEFAULT FALSE
);

-- PRIMARY KEYS

CREATE UNIQUE INDEX ON "livet"."users" ("id");

CREATE UNIQUE INDEX ON "livet"."patients_clinical_records" ("id");


-- FOREIGN KEYS

ALTER TABLE "livet"."patients_clinical_records" ADD FOREIGN KEY ("patient_id") REFERENCES "livet"."users" ("id");

ALTER TABLE "livet"."patients_clinical_records" ADD FOREIGN KEY ("user_id") REFERENCES "livet"."users" ("id");
