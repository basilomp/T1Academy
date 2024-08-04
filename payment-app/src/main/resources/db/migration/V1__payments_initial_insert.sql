CREATE TABLE payments_schema.operation_status
(
    id          BIGSERIAL PRIMARY KEY,
    code        INTEGER     NOT NULL UNIQUE,
    description VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE payments_schema.payments_history
(
    id          BIGSERIAL PRIMARY KEY,
    product_id  INTEGER NOT NULL,
    sum         NUMERIC(10, 2),
    status_code INTEGER NOT NULL REFERENCES payments_schema.operation_status (code)
);

INSERT INTO payments_schema.operation_status (code, description)
VALUES (20, 'SUCCESS'),
       (40, 'INSUFFICIENT_BALANCE'),
       (50, 'EXTERNAL_SERVICE_UNAVAILABLE');