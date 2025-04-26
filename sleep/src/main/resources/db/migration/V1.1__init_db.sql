CREATE TABLE IF NOT EXISTS users
(
    id         BIGSERIAL PRIMARY KEY,
    first_name VARCHAR(255)        NOT NULL,
    last_name  VARCHAR(255)        NOT NULL,
    email      VARCHAR(255) UNIQUE NOT NULL
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
    FOREIGN KEY (user_id) REFERENCES users (id)
);

-- insert for testing
-- first user has multiple sleep-log for testing avarages and summary
-- second user has no sleep logs
INSERT INTO users (first_name, last_name, email)
VALUES ('John', 'Doe', 'johnny@gmail.com'),
       ('Johnny', 'Doey', 'johnnyd@gmail.com');

INSERT INTO sleep_log (user_id, sleep_date, time_in_bed_from, time_in_bed_to, total_time_in_bed, mood)
VALUES (1, CURRENT_DATE - INTERVAL '0 day', '23:15', '07:10', '7 hours 55 minutes', 'GOOD'),
       (1, CURRENT_DATE - INTERVAL '1 day', '22:50', '06:30', '7 hours 40 minutes', 'OK'),
       (1, CURRENT_DATE - INTERVAL '2 day', '00:10', '08:00', '7 hours 50 minutes', 'GOOD'),
       (1, CURRENT_DATE - INTERVAL '3 day', '23:30', '06:45', '7 hours 15 minutes', 'BAD'),
       (1, CURRENT_DATE - INTERVAL '4 day', '22:20', '06:10', '7 hours 50 minutes', 'GOOD'),
       (1, CURRENT_DATE - INTERVAL '5 day', '23:55', '07:30', '7 hours 35 minutes', 'OK'),
       (1, CURRENT_DATE - INTERVAL '6 day', '00:30', '08:00', '7 hours 30 minutes', 'GOOD'),
       (1, CURRENT_DATE - INTERVAL '7 day', '22:40', '06:20', '7 hours 40 minutes', 'BAD'),
       (1, CURRENT_DATE - INTERVAL '8 day', '23:20', '07:10', '7 hours 50 minutes', 'GOOD'),
       (1, CURRENT_DATE - INTERVAL '9 day', '23:45', '06:50', '7 hours 5 minutes', 'GOOD'),
       (1, CURRENT_DATE - INTERVAL '10 day', '22:10', '05:50', '7 hours 40 minutes', 'OK'),
       (1, CURRENT_DATE - INTERVAL '11 day', '23:00', '07:00', '8 hours', 'BAD'),
       (1, CURRENT_DATE - INTERVAL '12 day', '00:15', '07:45', '7 hours 30 minutes', 'GOOD'),
       (1, CURRENT_DATE - INTERVAL '13 day', '22:35', '06:15', '7 hours 40 minutes', 'GOOD'),
       (1, CURRENT_DATE - INTERVAL '14 day', '23:10', '06:40', '7 hours 30 minutes', 'OK'),
       (1, CURRENT_DATE - INTERVAL '15 day', '23:50', '07:20', '7 hours 30 minutes', 'GOOD'),
       (1, CURRENT_DATE - INTERVAL '16 day', '00:05', '07:30', '7 hours 25 minutes', 'BAD'),
       (1, CURRENT_DATE - INTERVAL '17 day', '22:25', '06:00', '7 hours 35 minutes', 'OK'),
       (1, CURRENT_DATE - INTERVAL '18 day', '23:40', '07:10', '7 hours 30 minutes', 'GOOD'),
       (1, CURRENT_DATE - INTERVAL '19 day', '00:20', '08:00', '7 hours 40 minutes', 'GOOD'),
       (1, CURRENT_DATE - INTERVAL '20 day', '22:50', '06:30', '7 hours 40 minutes', 'BAD'),
       (1, CURRENT_DATE - INTERVAL '21 day', '23:25', '07:00', '7 hours 35 minutes', 'GOOD'),
       (1, CURRENT_DATE - INTERVAL '22 day', '22:15', '05:50', '7 hours 35 minutes', 'GOOD'),
       (1, CURRENT_DATE - INTERVAL '23 day', '23:55', '07:20', '7 hours 25 minutes', 'OK'),
       (1, CURRENT_DATE - INTERVAL '24 day', '00:30', '08:10', '7 hours 40 minutes', 'GOOD'),
       (1, CURRENT_DATE - INTERVAL '25 day', '23:05', '06:50', '7 hours 45 minutes', 'BAD'),
       (1, CURRENT_DATE - INTERVAL '26 day', '22:30', '06:00', '7 hours 30 minutes', 'GOOD'),
       (1, CURRENT_DATE - INTERVAL '27 day', '23:50', '07:40', '7 hours 50 minutes', 'GOOD'),
       (1, CURRENT_DATE - INTERVAL '28 day', '22:45', '06:30', '7 hours 45 minutes', 'OK'),
       (1, CURRENT_DATE - INTERVAL '29 day', '23:10', '06:40', '7 hours 30 minutes', 'GOOD');