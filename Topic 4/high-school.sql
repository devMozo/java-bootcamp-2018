CREATE DATABASE IF NOT EXISTS highschool;

USE 'highschool';

## CREATING TABLES..

CREATE TABLE IF NOT EXISTS people (
    id INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(150) NOT NULL,
    last_name VARCHAR(150) NOT NULL,
    date_of_birth INT NOT NULL # EPOCH TIME
) ENGINE=INNODB;

CREATE TABLE IF NOT EXISTS students (
    id INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    id_person INT UNSIGNED NOT NULL,
    CONSTRAINT fk_id_person_student
        FOREIGN KEY (id_person)
        REFERENCES people(id)
) ENGINE=INNODB;

CREATE TABLE IF NOT EXISTS teachers (
    id INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    id_person INT UNSIGNED NOT NULL,
    CONSTRAINT fk_id_person_teacher
        FOREIGN KEY (id_person)
        REFERENCES people(id)
) ENGINE=INNODB;

CREATE TABLE IF NOT EXISTS courses (
    id INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(150) NOT NULL,
    hours_by_week INT,
    schedule_time INT
) ENGINE=INNODB;

CREATE TABLE IF NOT EXISTS student_x_course (
    id INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    id_student INT UNSIGNED NOT NULL,
    id_course INT UNSIGNED NOT NULL,
    CONSTRAINT fk_id_student 
        FOREIGN KEY (id_student)
        REFERENCES students(id),
    CONSTRAINT fk_id_course 
        FOREIGN KEY (id_course)
        REFERENCES courses(id)
) ENGINE=INNODB;

# PROCEDURE TO INSERT A STUDENT

DROP PROCEDURE IF EXISTS insertStudent;

DELIMITER //

CREATE PROCEDURE insertStudent(IN _first_name VARCHAR(150),
                               IN _last_name VARCHAR(150),
                               IN _date_of_birth INT )
BEGIN
    
    DECLARE _id_person INT UNSIGNED;

    INSERT INTO people (first_name, last_name, date_of_birth)
    VALUES (_first_name, _last_name, _date_of_birth);

    SET _id_person = LAST_INSERT_ID();

    INSERT INTO students (id_person)
    VALUES (_id_person);
    
END //

DELIMITER ;


DROP PROCEDURE IF EXISTS insertTeacher;

# PROCEDURE TO INSERT A TEACHER

DELIMITER //

CREATE PROCEDURE insertTeacher(IN _first_name VARCHAR(150),
                               IN _last_name VARCHAR(150),
                               IN _date_of_birth INT )
BEGIN

    DECLARE _id_person INT UNSIGNED;
    
    INSERT INTO people (first_name, last_name, date_of_birth)
    VALUES (_first_name, _last_name, _date_of_birth);

    SET _id_person = LAST_INSERT_ID();

    INSERT INTO teachers (id_person)
    VALUES (_id_person);
    
END //

DELIMITER ;

## INSERTING DATA..

CALL insertStudent("Nicolas", "Mozo", 1525354305);
CALL insertStudent("Jorge", "Hernesto", 1524354305);
CALL insertStudent("Pipa", "Higuain", 1525254305);
CALL insertStudent("Era", "PorAbajo", 1525154305);
CALL insertStudent("Gustavo", "Guardiola", 1535354505);
CALL insertStudent("Quinquela", "Martinez", 152564305);
CALL insertStudent("Madonna", "Perez", 152544305);
CALL insertStudent("Porque", "TantosEstudiantes", 1525632305);
CALL insertStudent("Player", "1", 152565305);
CALL insertStudent("Player", "2", 152464305);
CALL insertStudent("Nicolas", "Mozo", 1525354305);
CALL insertStudent("Jorge", "Hernesto", 1524354305);
CALL insertStudent("Pipa", "Higuain", 1525254305);
CALL insertStudent("Era", "PorAbajo", 1525154305);
CALL insertStudent("Gustavo", "Guardiola", 1535354505);
CALL insertStudent("Quinquela", "Martinez", 152564305);
CALL insertStudent("Madonna", "Perez", 152544305);
CALL insertStudent("Porque", "TantosEstudiantes", 1525632305);
CALL insertStudent("Player", "1", 152565305);
CALL insertStudent("Player", "2", 152464305);
CALL insertStudent("Nicolas", "Mozo", 1525354305);
CALL insertStudent("Jorge", "Hernesto", 1524354305);
CALL insertStudent("Pipa", "Higuain", 1525254305);
CALL insertStudent("Era", "PorAbajo", 1525154305);
CALL insertStudent("Gustavo", "Guardiola", 1535354505);
CALL insertStudent("Quinquela", "Martinez", 152564305);
CALL insertStudent("Madonna", "Perez", 152544305);
CALL insertStudent("Porque", "TantosEstudiantes", 1525632305);
CALL insertStudent("Player", "1", 152565305);
CALL insertStudent("Player", "2", 152464305);

CALL insertTeacher('Arquimedes', "Raul", 152522305);
CALL insertTeacher('Aristoteles', "Jose", 152522305);
CALL insertTeacher('Aquino', "Santo Tomas", 152522305);

INSERT INTO courses (name, hours_by_week, schedule_time)
VALUES ("Mamposteria en Seco", 23, 4),
       ("Carpinteria Azteca", 10, 2),
       ("Breaking Bad (Nombre Clave)", 24, 24);


INSERT INTO student_x_course (id_student, id_course)
SELECT id, (
             SELECT id
             FROM courses
             LIMIT 1) AS id_course
FROM students
LIMIT 10;

INSERT INTO student_x_course (id_student, id_course)
SELECT id, (
             SELECT id
             FROM courses
             LIMIT 1,1) AS id_course
FROM students
LIMIT 10,10;

INSERT INTO student_x_course (id_student, id_course)
SELECT  id, (
             SELECT id
             FROM courses
             LIMIT 2,1) AS id_course
FROM students
LIMIT 20,10;

