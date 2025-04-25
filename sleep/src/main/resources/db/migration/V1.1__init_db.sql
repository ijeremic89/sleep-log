CREATE TABLE IF NOT EXISTS "user"
(
    id            BIGSERIAL PRIMARY KEY,
    first_name    VARCHAR(255)        NOT NULL,
    last_name     VARCHAR(255)        NOT NULL,
    email         VARCHAR(255) UNIQUE NOT NULL,
    created_date  TIMESTAMP,
    modified_date TIMESTAMP
);

CREATE TABLE IF NOT EXISTS sleep_log
(
    id                BIGSERIAL PRIMARY KEY,
    user_id           BIGINT   NOT NULL,
    sleep_date        DATE     NOT NULL,
    time_in_bed_from  TIME     NOT NULL,
    time_in_bed_to    TIME     NOT NULL,
    total_time_in_bed INTERVAL NOT NULL,
    mood              VARCHAR(10) CHECK (mood IN ('BAD', 'OK', 'GOOD')),
    created_date      TIMESTAMP DEFAULT now(),
    FOREIGN KEY (user_id) REFERENCES "user" (id)
);