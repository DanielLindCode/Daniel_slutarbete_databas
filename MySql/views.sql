-- VIEWS

DROP VIEW if exists vw_emp_info;
create or replace view vw_emp_info as
select 
employees.emp_id,
employees.emp_first_name,
employees.emp_last_name,
employees.emp_hired_date,
employee_contact_info.phone_nr,
employee_contact_info.email,
living_quarters.lq_name,
living_quarters.room_nr,
living_quarters.site
from employees
join employee_contact_info on employee_contact_info.emp_contact_id = employees.emp_contact_id
join living_quarters on living_quarters.lq_id = employee_contact_info.lq_id;

DROP VIEW if exists vw_dino_info;
create or replace view vw_dino_info as
select 
dinosaurs.dino_id,
dinosaurs.dino_name,
dino_types.type_name,
diets.diet,
dinosaurs.year_of_birth,
paddocks.pad_name
from dinosaurs
join dino_types on dino_types.dino_type_id = dinosaurs.dino_type_id
join diets on diets.diet_id = dino_types.diet_id
join pad_inhibits on pad_inhibits.dino_id = dinosaurs.dino_id
join paddocks on paddocks.pad_id = pad_inhibits.pad_id;


