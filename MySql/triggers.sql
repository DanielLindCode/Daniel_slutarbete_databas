-- TRIGGERS

use jpdb;


DROP TRIGGER IF EXISTS tr_ins_employees;
DELIMITER $$
CREATE TRIGGER tr_ins_employees
BEFORE INSERT ON employees
FOR EACH ROW 
SET NEW.emp_first_name = UPPER(NEW.emp_first_name);
SET NEW.emp_last_name = UPPER(NEW.emp_last_name);
DELIMITER ;

DROP TRIGGER IF EXISTS tr_ins_dino;
DELIMITER $$
CREATE TRIGGER tr_ins_dino
BEFORE INSERT ON dinosaurs
FOR EACH ROW 
SET NEW.dino_name = UPPER(NEW.dino_name);
END;
DELIMITER ;

DROP TRIGGER IF EXISTS tr_ins_dino_type;
DELIMITER $$
CREATE TRIGGER tr_ins_dino_type
BEFORE INSERT ON dino_types
FOR EACH ROW 
SET NEW.type_name = UPPER(NEW.type_name);
END;
DELIMITER ;

DROP TRIGGER IF EXISTS tr_ins_diet;
DELIMITER $$	
CREATE TRIGGER tr_ins_diet
BEFORE INSERT ON diets
FOR EACH ROW 
SET NEW.diet = UPPER(NEW.diet);
END;
DELIMITER ;

DROP TRIGGER IF EXISTS tr_after_delete_dino;
DELIMITER $$
CREATE TRIGGER tr_after_delete_dino
AFTER DELETE
ON dinosaurs FOR EACH ROW
BEGIN
DECLARE db_user VARCHAR(50);
SELECT USER() INTO db_user;
INSERT INTO dino_audit (
contact_id,
deleted_date,
deleted_by)
VALUES (
OLD.contact_id,
SYSDATE(),
db_user);
END;
DELIMITER ;

DROP TRIGGER IF EXISTS tr_after_delete_emp;
DELIMITER $$
CREATE TRIGGER tr_after_delete_emp
AFTER DELETE
ON employees FOR EACH ROW
BEGIN
DECLARE db_user VARCHAR(50);
SELECT USER() INTO db_user;
INSERT INTO emp_audit (
contact_id,
deleted_date,
deleted_by)
VALUES (
OLD.contact_id,
SYSDATE(),
db_user);
END;
DELIMITER ;

DROP TRIGGER IF EXISTS tr_after_delete_faci;
DELIMITER $$
CREATE TRIGGER tr_after_delete_faci
AFTER DELETE
ON facilities FOR EACH ROW
BEGIN
DECLARE db_user VARCHAR(50);
SELECT USER() INTO db_user;
INSERT INTO faci_audit (
contact_id,
deleted_date,
deleted_by)
VALUES (
OLD.contact_id,
SYSDATE(),
db_user);
END;
DELIMITER ;
