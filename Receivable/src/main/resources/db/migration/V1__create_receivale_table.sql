CREATE TABLE receivable (
                            id BIGSERIAL PRIMARY KEY,
                            company_id BIGINT,
                            balance NUMERIC(15, 2),
                            status VARCHAR(255)
);