ALTER TABLE goals
RENAME COLUMN name TO title;
TRUNCATE TABLE goals;
ALTER TABLE goals ADD COLUMN finish_title VARCHAR(80) NOT NULL;

CREATE TABLE stages (
    id UUID PRIMARY KEY,
    name VARCHAR(120) NOT NULL,
    description VARCHAR(500) NOT NULL,
    start_date date,
    end_date date,
    status varchar(20),
    goal_id UUID REFERENCES goals
);