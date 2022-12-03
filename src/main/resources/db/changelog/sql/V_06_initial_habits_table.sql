CREATE TABLE habits (
    id UUID PRIMARY KEY,
    name VARCHAR(120) NOT NULL,
    description VARCHAR(500) NOT NULL
);

CREATE TABLE habit_checkmarks (
    id UUID PRIMARY KEY,
    date date,
    status varchar(15),
    habit_id UUID REFERENCES habits
);