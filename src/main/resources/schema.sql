CREATE TABLE equations (
    id SERIAL PRIMARY KEY,
    equation VARCHAR(255) NOT NULL,
    root DOUBLE PRECISION NOT NULL
);