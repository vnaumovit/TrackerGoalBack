CREATE TABLE habits (
    id UUID PRIMARY KEY,
    name VARCHAR(200) NOT NULL,
    description varchar(500),
    create_at TIMESTAMP
);

CREATE TABLE habit_days (
    id UUID PRIMARY KEY,
    date date NOT NULL,
    status BOOLEAN NOT NULL,
    habit_id UUID REFERENCES habits
);