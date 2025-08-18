CREATE TABLE modules (
    id              BIGSERIAL PRIMARY KEY,
    title           VARCHAR(255) NOT NULL,
    description     TEXT,
    position        INTEGER,
    course_id       BIGINT NOT NULL,
    created_at      TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    created_by_id   BIGINT,
    updated_at      TIMESTAMP,
    updated_by_id   BIGINT,
    deleted_tmsp    TIMESTAMP,
    CONSTRAINT fk_modules_course FOREIGN KEY (course_id) REFERENCES courses(id) ON DELETE CASCADE
);
