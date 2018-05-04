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

CREATE TABLE IF NOT EXISTS days (
    id INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    value VARCHAR(100) NOT NULL
) ENGINE=INNODB;

CREATE TABLE IF NOT EXISTS courses (
    id INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(150) NOT NULL,
    id_teacher INT UNSIGNED NOT NULL,
    CONSTRAINT fk_id_teacher 
        FOREIGN KEY (id_teacher)
        REFERENCES teachers(id)
) ENGINE=INNODB;

CREATE TABLE IF NOT EXISTS day_x_course(
    id INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    hours INT UNSIGNED NOT NULL,
    id_day INT UNSIGNED NOT NULL,
    id_course INT UNSIGNED NOT NULL,
    CONSTRAINT fk_id_day_course
        FOREIGN KEY (id_day)
        REFERENCES days(id),
    CONSTRAINT fk_id_course_of_that_day
        FOREIGN KEY (id_course)
        REFERENCES courses(id)
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

CREATE TABLE IF NOT EXISTS partial_notes (
    id INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    note INT UNSIGNED,
    id_student INT UNSIGNED NOT NULL,
    id_course INT UNSIGNED NOT NULL,
    INDEX i_note (note),
    CONSTRAINT fk_id_student_partial_note 
        FOREIGN KEY (id_student)
        REFERENCES students(id),
    CONSTRAINT fk_id_course_partial_note
        FOREIGN KEY (id_course)
        REFERENCES courses(id)
) ENGINE=INNODB;

CREATE TABLE IF NOT EXISTS final_notes (
    id INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    note INT UNSIGNED,
    id_student INT UNSIGNED NOT NULL,
    id_course INT UNSIGNED NOT NULL,
    INDEX i_note (note),
    CONSTRAINT fk_id_student_final_note
        FOREIGN KEY (id_student)
        REFERENCES students(id),
    CONSTRAINT fk_id_course_final_note
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

# ADDED SOME DAYS

INSERT INTO days (value)
VALUES ("Monday"),
       ("Tuesday"),
       ("Wednesday"),
       ("Thursday"),
       ("Friday"),
       ("Saturday"),
       ("Sunday");

# ADDED COURSES

INSERT INTO courses (name, id_teacher)
VALUES ("Mamposteria en Seco", (SELECT id FROM teachers LIMIT 1)),
       ("Carpinteria Azteca", (SELECT id FROM teachers LIMIT 1,1)),
       ("Breaking Bad (Nombre Clave)", (SELECT id FROM teachers LIMIT 2,1));


# ADDED THE DAY OF THE COURSES

INSERT INTO day_x_course (id_course, id_day, hours)
VALUES ((SELECT id FROM courses LIMIT 1), (SELECT id FROM days LIMIT 3,1), 3),
       ((SELECT id FROM courses LIMIT 1), (SELECT id FROM days LIMIT 5,1), 3),
       ((SELECT id FROM courses LIMIT 1,1), (SELECT id FROM days LIMIT 5,1), 3),
       ((SELECT id FROM courses LIMIT 1,1), (SELECT id FROM days LIMIT 1), 2),
       ((SELECT id FROM courses LIMIT 1,1), (SELECT id FROM days LIMIT 5,1), 2),
       ((SELECT id FROM courses LIMIT 2,1), (SELECT id FROM days LIMIT 1,1), 3);

# ADDED STUDENT BY COURSE

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

# ADDED SOME PARTIAL NOTES

INSERT INTO partial_notes (note, id_student, id_course)
SELECT 1, students.id, (
                        SELECT id
                        FROM courses
                        LIMIT 1) AS id_course
FROM students
LIMIT 5;

INSERT INTO partial_notes (note, id_student, id_course)
SELECT 1, students.id, (
                        SELECT id
                        FROM courses
                        LIMIT 1) AS id_course
FROM students
LIMIT 5;

INSERT INTO partial_notes (note, id_student, id_course)
SELECT 6, students.id, (
                        SELECT id
                        FROM courses
                        LIMIT 1) AS id_course
FROM students
LIMIT 5,5;

INSERT INTO partial_notes (note, id_student, id_course)
SELECT 6, students.id, (
                        SELECT id
                        FROM courses
                        LIMIT 1) AS id_course
FROM students
LIMIT 5,5;

INSERT INTO partial_notes (note, id_student, id_course)
SELECT 8, students.id, (
                        SELECT id
                        FROM courses
                        LIMIT 1) AS id_course
FROM students
LIMIT 10;

# ADDED SOME FINAL NOTES

INSERT INTO final_notes (note, id_student, id_course)
SELECT 2, students.id, (
                        SELECT id
                        FROM courses
                        LIMIT 1) AS id_course
FROM students
LIMIT 5;

INSERT INTO final_notes (note, id_student, id_course)
SELECT 7, students.id, (
                        SELECT id
                        FROM courses
                        LIMIT 1) AS id_course
FROM students
LIMIT 5, 5;