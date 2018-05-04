USE highschool;

SELECT courses.name, 
       students.first_name, students.last_name,
       teachers.first_name, teachers.last_name
FROM courses
INNER JOIN student_x_course
    ON student_x_course.id_course = courses.id
INNER JOIN (
            SELECT students.id, people.first_name, people.last_name
            FROM students
            INNER JOIN people
                ON people.id = students.id_person
            ) AS students
            ON students.id = student_x_course.id_student
INNER JOIN (
            SELECT teachers.id, people.first_name, people.last_name
            FROM teachers
            INNER JOIN people
                ON people.id = teachers.id_person
            ) AS teachers
            ON teachers.id = courses.id_teacher
WHERE courses.id = 1
