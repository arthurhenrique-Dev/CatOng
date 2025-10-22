CREATE TABLE IF NOT EXISTS cachorro_fotos (
    cachorro_id BIGINT NOT NULL,
    foto    TEXT NOT NULL,
    FOREIGN KEY (cachorro_id) REFERENCES cachorro (id) ON DELETE CASCADE
);