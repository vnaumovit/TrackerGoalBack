ALTER TABLE goals
ADD COLUMN goal_status varchar(50);
ALTER TABLE goals ALTER COLUMN start_date DROP NOT NULL;
ALTER TABLE goals ALTER COLUMN start_date TYPE date;
ALTER TABLE goals ALTER COLUMN end_date TYPE date;
ALTER TABLE goals ALTER COLUMN end_date DROP NOT NULL;
ALTER TABLE goals RENAME COLUMN goal_status TO status;