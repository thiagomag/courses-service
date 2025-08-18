CREATE TABLE courses (
    id              BIGSERIAL PRIMARY KEY,
    name            VARCHAR(255) NOT NULL,
    description     TEXT,
    category        VARCHAR(100) NOT NULL,
    active          BOOLEAN DEFAULT TRUE,
    created_at      TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    created_by_id   BIGINT,
    updated_at      TIMESTAMP,
    updated_by_id   BIGINT,
    deleted_tmsp    TIMESTAMP
);
