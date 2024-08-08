CREATE TABLE finance (
                            id BIGSERIAL PRIMARY KEY,
                            company_id BIGINT,
                            receivable_id BIGINT,
                            amount NUMERIC(15, 2)
);