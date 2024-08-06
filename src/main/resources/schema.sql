CREATE TABLE job_titles (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255)
);

CREATE TABLE companies (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255)
);

CREATE SEQUENCE IF NOT EXISTS employees_id_seq;

CREATE TABLE employees (
    id INT DEFAULT NEXT VALUE FOR employees_id_seq PRIMARY KEY,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    email_address VARCHAR(255) NOT NULL,
    salary DECIMAL(10, 2) NOT NULL,
    job_title_id INT NOT NULL,
    company_id INT NOT NULL,
    FOREIGN KEY (job_title_id) REFERENCES job_titles(id),
    FOREIGN KEY (company_id) REFERENCES companies(id)
);

INSERT INTO job_titles (name) VALUES ('Software Engineer');
INSERT INTO companies (name) VALUES ('Example Company LLC');
INSERT INTO employees (first_name, last_name, email_address, salary, job_title_id, company_id) VALUES ('Jon', 'Doe', 'jon.doe@mail.com', 130000.00, 1, 1);
