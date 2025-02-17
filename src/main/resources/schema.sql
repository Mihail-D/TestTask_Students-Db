CREATE TABLE "group" (
    id BIGINT PRIMARY KEY,
    number VARCHAR(50) NOT NULL,
    faculty_name VARCHAR(100) NOT NULL
);

CREATE TABLE students (
    id BIGINT PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    middle_name VARCHAR(50),
    birth_date DATE NOT NULL,
    group_id BIGINT,
    CONSTRAINT fk_group FOREIGN KEY (group_id) REFERENCES "group"(id) ON DELETE RESTRICT
);
