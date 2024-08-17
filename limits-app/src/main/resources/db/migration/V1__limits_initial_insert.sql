CREATE TABLE limits_schema.limits
(
    id          BIGSERIAL PRIMARY KEY,
    user_id     INTEGER        NOT NULL UNIQUE,
    daily_limit NUMERIC(10, 2) NOT NULL,
    approved_daily_limit NUMERIC(10, 2) NOT NULL DEFAULT (10000.00)
);

-- Функция для установки значения daily_limit в значение max_daily_limit
CREATE OR REPLACE FUNCTION set_daily_limit_on_insert()
    RETURNS TRIGGER AS $$
BEGIN
    NEW.daily_limit := NEW.approved_daily_limit;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trg_set_daily_limit_on_insert
    BEFORE INSERT ON limits_schema.limits
    FOR EACH ROW
EXECUTE FUNCTION set_daily_limit_on_insert();

DO
$$
    BEGIN
        FOR i IN REVERSE 100..1
            LOOP
                INSERT INTO limits_schema.limits (user_id, approved_daily_limit)
                VALUES (i::INTEGER, ROUND(CAST(1000 + RANDOM() * (10000 - 1000) AS NUMERIC), 2));
            END LOOP;
    END
$$;
