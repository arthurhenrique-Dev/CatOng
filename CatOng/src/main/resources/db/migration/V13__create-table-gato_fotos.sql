CREATE TABLE IF NOT EXISTS gato_fotos (
    gato_id BIGINT NOT NULL,
    foto    TEXT NOT NULL,
    FOREIGN KEY (gato_id) REFERENCES gato (id) ON DELETE CASCADE
    );