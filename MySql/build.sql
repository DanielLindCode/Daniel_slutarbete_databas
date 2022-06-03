DROP DATABASE IF EXISTS jpdb;
CREATE DATABASE IF NOT EXISTS jpdb;

USE jpdb;

-- Main Tables

DROP TABLE IF EXISTS parks;
CREATE TABLE IF NOT EXISTS parks (
park_id INT AUTO_INCREMENT,
park_name VARCHAR(100) NOT NULL,
park_location varchar(100),
PRIMARY KEY (park_id));

DROP TABLE IF EXISTS living_quarters;
CREATE TABLE IF NOT EXISTS living_quarters (
lq_id INT AUTO_INCREMENT,
lq_name VARCHAR(100),
room_nr VARCHAR(20) NOT NULL,
site VARCHAR(10) NOT NULL,
PRIMARY KEY (lq_id)
);

DROP TABLE IF EXISTS employee_contact_info;
CREATE TABLE IF NOT EXISTS employee_contact_info (
emp_contact_id INT AUTO_INCREMENT,
lq_id INT NOT NULL,
phone_nr VARCHAR(50),
email VARCHAR(100),
PRIMARY KEY (emp_contact_id),
FOREIGN KEY (lq_id) REFERENCES living_quarters(lq_id)            
);

DROP TABLE IF EXISTS employees;
CREATE TABLE IF NOT EXISTS employees (
emp_id INT AUTO_INCREMENT,
emp_first_name VARCHAR(100) NOT NULL,
emp_last_name VARCHAR(100) NOT NULL,
emp_age INT,
emp_contact_id INT,
PRIMARY KEY (emp_id),
FOREIGN KEY (emp_contact_id) REFERENCES employee_contact_info(emp_contact_id)
);

DROP TABLE IF EXISTS diets;
CREATE TABLE IF NOT EXISTS diets (
diet_id INT AUTO_INCREMENT,
diet varchar(100) NOT NULL,
PRIMARY KEY (diet_id)
);

DROP TABLE IF EXISTS dino_types;
CREATE TABLE IF NOT EXISTS dino_types (
dino_type_id INT AUTO_INCREMENT,
type_name varchar(100) NOT NULL,
diet_id INT NOT NULL,
PRIMARY KEY (dino_type_id),
FOREIGN KEY (diet_id) REFERENCES diets(diet_id)
);

DROP TABLE IF EXISTS dinosaurs;
CREATE TABLE IF NOT EXISTS dinosaurs (
dino_id INT AUTO_INCREMENT,
dino_type_id INT NOT NULL,
dino_name varchar(100) NOT NULL,
year_of_birth INT NOT NULL,
PRIMARY KEY (dino_id),
FOREIGN KEY (dino_type_id) REFERENCES dino_types(dino_type_id)
);

DROP TABLE IF EXISTS profession_list;
CREATE TABLE IF NOT EXISTS profession_list (
profession_id INT AUTO_INCREMENT,
prof_titel VARCHAR(100) NOT NULL,
PRIMARY KEY (profession_id)
);

DROP TABLE IF EXISTS clearance;
CREATE TABLE IF NOT EXISTS clearance (
cl_id INT AUTO_INCREMENT,
cl_level VARCHAR(100) NOT NULL,
PRIMARY KEY (cl_id)
);

DROP TABLE IF EXISTS facilities;
CREATE TABLE IF NOT EXISTS facilities (
faci_id INT AUTO_INCREMENT,
faci_name VARCHAR(100),
site VARCHAR(10),
PRIMARY KEY (faci_id)
);

DROP TABLE IF EXISTS paddocks;
CREATE TABLE IF NOT EXISTS paddocks (
pad_id INT AUTO_INCREMENT,
pad_name VARCHAR(100) NOT NULL,
PRIMARY KEY (pad_id)
);

DROP TABLE IF EXISTS park_employees;
CREATE TABLE IF NOT EXISTS park_employees (
emp_id INT,
park_id INT,
FOREIGN KEY (park_id) REFERENCES parks(park_id),
FOREIGN KEY (emp_id) REFERENCES employees(emp_id)
);

DROP TABLE IF EXISTS park_facilities;
CREATE TABLE IF NOT EXISTS park_facilities (
faci_id INT NOT NULL,
park_id INT NOT NULL,
FOREIGN KEY (faci_id) REFERENCES facilities(faci_id),
FOREIGN KEY (park_id) REFERENCES parks(park_id)
);

DROP TABLE IF EXISTS park_dinos;
CREATE TABLE IF NOT EXISTS park_dinos (
park_id INT,
dino_id INT,
FOREIGN KEY (park_id) REFERENCES parks(park_id),
FOREIGN KEY (dino_id) REFERENCES dinosaurs(dino_id)
);

DROP TABLE IF EXISTS park_living_quarters;
CREATE TABLE IF NOT EXISTS park_living_quarters (
lq_id INT,
park_id INT,
FOREIGN KEY (lq_id) REFERENCES living_quarters(lq_id),
FOREIGN KEY (park_id) REFERENCES parks(park_id)
);

DROP TABLE IF EXISTS pad_inhibits;
CREATE TABLE IF NOT EXISTS pad_inhibits (
pad_id INT,
dino_id INT,
FOREIGN KEY (dino_id) REFERENCES dinosaurs(dino_id),
FOREIGN KEY (pad_id) REFERENCES paddocks(pad_id)
);

DROP TABLE IF EXISTS park_paddocks;
CREATE TABLE IF NOT EXISTS park_paddocks (
pad_id INT,
park_id INT,
FOREIGN KEY (park_id) REFERENCES parks(park_id),
FOREIGN KEY (pad_id) REFERENCES paddocks(pad_id)
);

DROP TABLE IF EXISTS emp_clarance;
CREATE TABLE IF NOT EXISTS emp_clarance (
emp_id INT,
cl_id INT,
FOREIGN KEY (cl_id) REFERENCES clearance(cl_id),
FOREIGN KEY (emp_id) REFERENCES employees(emp_id)
);

DROP TABLE IF EXISTS emp_profession;
CREATE TABLE IF NOT EXISTS emp_profession (
emp_id INT,
profession_id INT,
FOREIGN KEY (profession_id) REFERENCES profession_list(profession_id),
FOREIGN KEY (emp_id) REFERENCES employees(emp_id)
);

DROP TABLE IF EXISTS emp_work_facilities;
CREATE TABLE IF NOT EXISTS emp_work_facilities (
emp_id INT,
faci_id INT,
FOREIGN KEY (faci_id) REFERENCES facilities(faci_id),
FOREIGN KEY (emp_id) REFERENCES employees(emp_id)
);

-- Help tables

DROP TABLE IF EXISTS dino_audit;
CREATE TABLE IF NOT EXISTS dino_audit(
contact_id INT,
deleted_date DATE,
deleted_by VARCHAR(50)
);

DROP TABLE IF EXISTS emp_audit;
CREATE TABLE IF NOT EXISTS emp_audit(
contact_id INT,
deleted_date DATE,
deleted_by VARCHAR(50)
);

DROP TABLE IF EXISTS faci_audit;
CREATE TABLE IF NOT EXISTS faci_audit(
contact_id INT,
deleted_date DATE,
deleted_by VARCHAR(50)
);

/*
use jpdb;

UPDATE employees
SET emp_first_name = 'Sam', emp_last_name = 'Osti', emp_birth_year = 1988, emp_hired_date = 2010
WHERE emp_first_name = 'Sammi';
*/