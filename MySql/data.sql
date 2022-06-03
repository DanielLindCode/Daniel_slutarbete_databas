-- DATA


-- Parks
insert into parks (park_name) VALUES ('Jurassic Park');


-- living_quarters
insert into living_quarters (lq_name, room_nr, site) VALUES ('Staff Apartments', '1 A', 'A');


-- adding contact info
insert into employee_contact_info (lq_id, phone_nr, email) values (1, '555-252479', 'sam.andersson@jp.com');

-- clearance
insert into clearance (cl_level) values ('confidential');
insert into clearance (cl_level) values ('secret');
insert into clearance (cl_level) values ('top secret');

-- adding employees
insert into employees (emp_first_name, emp_last_name, emp_age, emp_contact_id)
values ('Sam', 'Andersson', 32, 1);

-- Employee Clarance
insert into emp_clarance (emp_id, cl_id) values (1,2);

-- diets
insert into diets (diet) values ('carnivore ');
insert into diets (diet) values ('herbivore ');
insert into diets (diet) values ('omnivore  ');

-- dino_types
insert into dino_types (type_name, diet_id) values ('Tyrannosaurus', 3);

-- dinosaurs
insert into dinosaurs (dino_type_id, dino_name, year_of_birth) values (1 ,'Billy', 2010);

-- proffession
insert into profession_list (prof_titel) values ('Engineer');

-- facilities
insert into facilities (faci_name, site) values ('Hydro Electric Station', 'A');

-- Paddocks
insert into paddocks (pad_name) values ('Rex Cage');

-- Park Employees
insert into park_employees (emp_id, park_id) values (1,1);

-- Park facilities
insert into park_facilities (faci_id, park_id) values (1,1);

-- Park Dinos
insert into park_dinos (park_id, dino_id) values (1,1);

-- Living quarters
insert into park_living_quarters (lq_id, park_id) values (1,1);

-- Paddock inhibits
insert into pad_inhibits (pad_id, dino_id) values (1,1);

-- Park paddocks
insert into park_paddocks (pad_id, park_id) values (1,1);

-- Employee Clarance
insert into emp_clarance (emp_id, cl_id) values (1,2);

-- Employee proffression
insert into emp_profession (emp_id, profession_id) values (1,1);

-- Employee work stations
insert into emp_work_facilities (emp_id, faci_id) values (1,1);











