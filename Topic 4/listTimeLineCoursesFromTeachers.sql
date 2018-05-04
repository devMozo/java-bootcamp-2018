USE highschool;

SELECT people.first_name, people.last_name,
       days.value, day_x_course.hours
FROM teachers
INNER JOIN people
    ON people.id = teachers.id_person
INNER JOIN courses
    ON courses.id_teacher = teachers.id
INNER JOIN day_x_course
    ON day_x_course.id_course = courses.id
INNER JOIN days
    ON days.id = day_x_course.id_day
WHERE teachers.id = 3
ORDER BY days.id ASC