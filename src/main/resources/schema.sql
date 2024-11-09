CREATE SEQUENCE IF NOT EXISTS employees_id_seq;
CREATE SEQUENCE IF NOT EXISTS job_titles_id_seq;

CREATE TABLE job_titles (
    id INT NOT NULL DEFAULT nextval('job_titles_id_seq') PRIMARY KEY,
    name VARCHAR(255)
);

CREATE TABLE employees (
    id INT NOT NULL DEFAULT nextval('employees_id_seq') PRIMARY KEY,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    email_address VARCHAR(255) NOT NULL,
    salary DECIMAL(10, 2) NOT NULL,
    job_title_id INT NOT NULL,
    FOREIGN KEY (job_title_id) REFERENCES job_titles(id)
);

INSERT INTO job_titles (name) VALUES ('Software Engineer');
INSERT INTO job_titles (name) VALUES ('DevOps Engineer');
INSERT INTO job_titles (name) VALUES ('Business Analyst');
INSERT INTO job_titles (name) VALUES ('Project Manager');

INSERT INTO employees (first_name, last_name, email_address, salary, job_title_id) VALUES ('Jon', 'Doe', 'jon.doe@mail.com', 130000.00, (SELECT id FROM job_titles WHERE job_titles.name LIKE 'Software Engineer'));
INSERT INTO employees (first_name, last_name, email_address, salary, job_title_id) VALUES ('Bob', 'James', 'bob.james@mail.com', 140000.00, (SELECT id FROM job_titles WHERE job_titles.name LIKE 'Project Manager'));
