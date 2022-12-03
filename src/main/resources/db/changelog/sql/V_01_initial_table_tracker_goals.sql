CREATE TABLE users (
    id       SERIAL PRIMARY KEY,
    name     VARCHAR(20)  NOT NULL,
    surname  VARCHAR(20)  NOT NULL,
    email    VARCHAR(30)  NOT NULL,
    password VARCHAR(200) NOT NULL
);

CREATE TABLE roles (
    id   SERIAL PRIMARY KEY,
    name VARCHAR(20) NOT NULL
);

CREATE TABLE users_roles (
    user_id SERIAL REFERENCES users,
    role_id SERIAL REFERENCES roles
);

CREATE TABLE goal_groups (
    id          UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name        VARCHAR(50) NOT NULL,
    description VARCHAR(150)
);

CREATE TABLE goals (
    id           UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name         VARCHAR(50)  NOT NULL,
    description  VARCHAR(150) NOT NULL,
    start_date   TIMESTAMP    NOT NULL,
    end_date     TIMESTAMP    NOT NULL,
    goal_group_id UUID REFERENCES goal_groups
);

CREATE TABLE images (
    id      SERIAL PRIMARY KEY,
    name    VARCHAR(50) NOT NULL,
    picture OID         NOT NULL,
    is_main BOOL        NOT NULL DEFAULT TRUE
);

