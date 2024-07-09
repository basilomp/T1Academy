CREATE DATABASE t1_db;

\c t1_db;

CREATE TABLE users
(
    id       BIGSERIAL PRIMARY KEY,
    username VARCHAR(255) UNIQUE
);

INSERT INTO users (username)
VALUES
    ('j.joyce'),
    ('m.proust'),
    ('c.baudelaire');
