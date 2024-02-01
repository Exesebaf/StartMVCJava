CREATE SEQUENCE IF NOT EXISTS user_seq;

CREATE TABLE IF NOT EXISTS users
(
    id BIGINT NOT NULL DEFAULT NEXTVAL('user_seq'),
    user_name VARCHAR(50) NOT NULL UNIQUE,
    last_name VARCHAR(50) NOT NULL,
    email VARCHAR(70) NOT NULL UNIQUE,
    age INT,
    password VARCHAR(100) NOT NULL,
    CONSTRAINT user_id PRIMARY KEY (id)
    );

COMMENT ON TABLE users IS 'Таблица с информацией о пользователях';
COMMENT ON COLUMN users.id IS 'Идентификационный номер пользователя';
COMMENT ON COLUMN users.user_name IS 'Никнейм пользователя';
COMMENT ON COLUMN users.last_name IS 'Имя пользователя';
COMMENT ON COLUMN users.email IS 'Электронная почта пользователя';
COMMENT ON COLUMN users.age IS 'Возраст пользователя';
COMMENT ON COLUMN users.password IS 'Пароль пользователя';