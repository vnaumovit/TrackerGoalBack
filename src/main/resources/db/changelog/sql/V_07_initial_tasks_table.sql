CREATE TABLE tasks (
    id UUID PRIMARY KEY,
    name VARCHAR(300) NOT NULL,
    is_done BOOLEAN,
    create_at TIMESTAMP
);
