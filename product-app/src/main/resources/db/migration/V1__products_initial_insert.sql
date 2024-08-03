CREATE TABLE products_schema.users
(
    id       BIGSERIAL PRIMARY KEY,
    username VARCHAR(255) UNIQUE
);

CREATE TABLE products_schema.products
(
    id             BIGSERIAL PRIMARY KEY,
    account_number VARCHAR UNIQUE,
    balance        NUMERIC(10, 2),
    product_type   VARCHAR(125),
    user_id        INTEGER REFERENCES products_schema.users (id)
);

INSERT INTO products_schema.users (username)
VALUES ('j.joyce'),
       ('m.proust'),
       ('c.baudelaire');

INSERT INTO products_schema.products (account_number, balance, product_type, user_id)
VALUES ('88956864', 451.0, 'CARD', 1),
       ('74833456', 8800.55, 'ACCOUNT', 2),
       ('49676938', 35.35, 'CARD', 2),
       ('58266112', 42.0, 'ACCOUNT', 1),
       ('76697762', 128.0, 'ACCOUNT', 3);