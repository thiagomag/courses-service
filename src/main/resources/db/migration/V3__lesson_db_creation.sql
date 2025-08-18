CREATE TABLE lessons (
    id                      BIGSERIAL PRIMARY KEY,
    title                   VARCHAR(255) NOT NULL,
    description             TEXT,
    video_url               VARCHAR(255),
    duration_in_minutes     INTEGER,
    module_id               BIGINT NOT NULL,
    created_at              TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    created_by_id           BIGINT,
    updated_at              TIMESTAMP,
    updated_by_id           BIGINT,
    deleted_tmsp            TIMESTAMP,
    CONSTRAINT fk_lessons_module FOREIGN KEY (module_id) REFERENCES modules(id) ON DELETE CASCADE
);