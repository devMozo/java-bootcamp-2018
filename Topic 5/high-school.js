use highschool;

db.dropDatabase();

use highschool;

db.createCollection('teachers', {
    validator: {
        $jsonSchema : {
            bsonType: 'object',
            required: ['first_name', 'last_name'],
            properties: {
                'first_name' : {
                    bsonType: 'string',
                    description: 'This must be an String and is Required'
                },
                'last_name' : {
                    bsonType: 'string',
                    description: 'This must be an String and is Required'
                },
                'date_of_birth' : {
                    bsonType: 'date',
                    description: 'This must be a Date\'field and isn\'t required'                    
                }
            }
        }
    }
});

db.createCollection('students', {
    validator: {
        $jsonSchema : {
            bsonType: 'object',
            required: ['first_name', 'last_name'],
            properties: {
                'first_name' : {
                    bsonType: 'string',
                    description: 'This must be an String and is Required'
                },
                'last_name' : {
                    bsonType: 'string',
                    description: 'This must be an String and is Required'
                },
                'date_of_birth' : {
                    bsonType: 'date',
                    description: 'This must be a Date\'field and isn\'t required'                    
                }
            }
        }
    }
});

db.createCollection('courses', {
    validator: {
        $jsonSchema : {
            bsonType: 'object',
            required: ['name', 'id_teacher', 'schedule_time'],
            properties: {
                'name' : {
                    bsonType: 'string',
                    description: 'This must be an String and is Required'
                },
                'id_teacher' : {
                    bsonType: 'objectId',
                    description: 'This must be the Id of a teacher and is Required'
                },
                'schedule_time' : {
                    bsonType: 'object',
                    description: 'This must be the list of days and hours that the courses will be dictated',
                    required: ['days'],
                    properties: {
                        'days' : {
                            bsonType: 'array',
                            description: 'This must be an array with the days that the courses will be dictated',
                            items: {
                                bsonType: 'object',
                                required: ['day', 'hour'],
                                properties: {
                                    'day' : {
                                        enum: ['Monday','Tuesday','Wednesday','Thursday','Friday','Saturday','Sunday'],
                                        description: 'This must be a day like the enum that we can see above.'
                                    },
                                    'hour' : {
                                        bsonType: 'date'                                  
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
});

db.createCollection('notes', {
    validator: {
        $jsonSchema : {
            bsonType: 'object',
            required: ['type_note', 'note', 'id_student', 'id_course'],
            description: 'This must represent the note of the students',
            properties: {
                'type_note' : {
                    enum: ['partial', 'final'],
                    description: 'The note represent the calification of a partial test or final test?'
                }, 
                'note' : {
                    bsonType: 'number',
                    description: 'Calification'
                },
                'id_student' : {
                    bsonType: 'objectId',
                    'description': 'Student\'s ID'
                },
                'id_course' : {
                    bsonType: 'objectId',
                    description: 'Course\'s ID'
                }
            }
        }
    }
});

// Inserting some data..

function insertStudent(firstName, lastName, dateOfBirth){

    db.students.insertOne({
        first_name: firstName,
        last_name: lastName,
        date_of_birth: dateOfBirth
    });

}

function insertTeacher(firstName, lastName, dateOfBirth){

    db.teachers.insertOne({
        first_name: firstName,
        last_name: lastName,
        date_of_birth: dateOfBirth
    });

}

function insertCourses(name, idTeacher, scheduleTime){

    db.courses.insertOne({
        name: name,
        id_teacher: idTeacher,
        schedule_time: scheduleTime
    });

}

function insertNotes(typeNote, note, idStudent, idCourse){

    db.notes.insertOne({
        type_note: typeNote,
        note: note,
        id_student: idStudent,
        id_course: idCourse
    });
}

insertStudent("Nicolas", "Mozo", new Date(1525354305));
insertStudent("Jorge", "Hernesto", new Date(1524354305));
insertStudent("Pipa", "Higuain", new Date(1525254305));
insertStudent("Era", "PorAbajo", new Date(1525154305));
insertStudent("Gustavo", "Guardiola", new Date(1535354505));
insertStudent("Quinquela", "Martinez", new Date(152564305));
insertStudent("Madonna", "Perez", new Date(152544305));
insertStudent("Porque", "TantosEstudiantes", new Date(1525632305));
insertStudent("Player", "1", new Date(152565305));
insertStudent("Player", "2", new Date(152464305));
insertStudent("Nicolas", "Mozo", new Date(1525354305));
insertStudent("Jorge", "Hernesto", new Date(1524354305));
insertStudent("Pipa", "Higuain", new Date(1525254305));
insertStudent("Era", "PorAbajo", new Date(1525154305));
insertStudent("Gustavo", "Guardiola", new Date(1535354505));
insertStudent("Quinquela", "Martinez", new Date(152564305));
insertStudent("Madonna", "Perez", new Date(152544305));
insertStudent("Porque", "TantosEstudiantes", new Date(1525632305));
insertStudent("Player", "1", new Date(152565305));
insertStudent("Player", "2", new Date(152464305));
insertStudent("Nicolas", "Mozo", new Date(1525354305));
insertStudent("Jorge", "Hernesto", new Date(1524354305));
insertStudent("Pipa", "Higuain", new Date(1525254305));
insertStudent("Era", "PorAbajo", new Date(1525154305));
insertStudent("Gustavo", "Guardiola", new Date(1535354505));
insertStudent("Quinquela", "Martinez", new Date(152564305));
insertStudent("Madonna", "Perez", new Date(152544305));
insertStudent("Porque", "TantosEstudiantes", new Date(1525632305));
insertStudent("Player", "1", new Date(152565305));
insertStudent("Player", "2", new Date(152464305));

insertTeacher('Arquimedes', "Raul", new Date(152522305));
insertTeacher('Aristoteles', "Jose", new Date(152522305));
insertTeacher('Aquino', "Santo Tomas", new Date(152522305));

insertCourses("Mamposteria en Seco", 
              db.teachers.findOne({ first_name: 'Aquino' })._id,
              {
                days : [
                    {
                        day: 'Monday',
                        hour: new Date("2016-01-01T22:00:00Z")
                    },
                    {
                        day: 'Thursday',
                        hour: new Date("2016-01-01T13:00:00Z")
                    },
                    {
                        day: 'Friday',
                        hour: new Date("2016-01-01T16:00:00Z")
                    }
                ]
              });

insertCourses("Carpinteria Azteca", 
              db.teachers.findOne({ first_name: 'Aristoteles' })._id,
              {
                days : [
                    {
                        day: 'Tuesday',
                        hour: new Date("2016-01-01T15:00:00Z")
                    },
                    {
                        day: 'Wednesday',
                        hour: new Date("2016-01-01T22:00:00Z")
                    }
                ]
              });

insertCourses("Breaking Bad (Nombre Clave)", 
              db.teachers.findOne({ first_name: 'Arquimedes' })._id, 
              {
                days : [
                    {
                        day: 'Monday',
                        hour: new Date("2016-01-01T12:00:00Z")
                    },
                    {
                        day: 'Thursday',
                        hour: new Date("2016-01-01T12:00:00Z")
                    }
                ]
              });

insertCourses("Breaking Bad (Nombre Clave) 2", 
              db.teachers.findOne({ first_name: 'Arquimedes' })._id, 
              {
                days : [
                    {
                        day: 'Monday',
                        hour: new Date("2016-01-01T12:00:00Z")
                    },
                    {
                        day: 'Thursday',
                        hour: new Date("2016-01-01T12:00:00Z")
                    }
                ]
              });

insertCourses("aaBreaking Bad (Nombre Clave) 2", 
              db.teachers.findOne({ first_name: 'Arquimedes' })._id, 
              {
                days : [
                    {
                        day: 'Monday',
                        hour: new Date("2016-01-01T12:00:00Z")
                    },
                    {
                        day: 'Thursday',
                        hour: new Date("2016-01-01T12:00:00Z")
                    }
                ]
              });


insertNotes('partial', 4, db.students.findOne({ first_name: "Nicolas"})._id, db.courses.findOne({ name: 'Mamposteria en Seco' })._id);
insertNotes('partial', 5, db.students.findOne({ first_name: "Jorge"})._id, db.courses.findOne({ name: 'Mamposteria en Seco' })._id);
insertNotes('partial', 2, db.students.findOne({ first_name: "Pipa"})._id, db.courses.findOne({ name: 'Mamposteria en Seco' })._id);
insertNotes('partial', 5, db.students.findOne({ first_name: "Era"})._id, db.courses.findOne({ name: 'Mamposteria en Seco' })._id);
insertNotes('partial', 3, db.students.findOne({ first_name: "Gustavo"})._id, db.courses.findOne({ name: 'Carpinteria Azteca' })._id);
insertNotes('partial', 1, db.students.findOne({ first_name: "Quinquela"})._id, db.courses.findOne({ name: 'Carpinteria Azteca' })._id);
insertNotes('partial', 3, db.students.findOne({ first_name: "Madonna"})._id, db.courses.findOne({ name: 'Carpinteria Azteca' })._id);
insertNotes('partial', 2, db.students.findOne({ first_name: "Porque"})._id, db.courses.findOne({ name: 'Breaking Bad (Nombre Clave)' })._id);
insertNotes('partial', 4, db.students.findOne({ first_name: "Player"})._id, db.courses.findOne({ name: 'Breaking Bad (Nombre Clave)' })._id);
insertNotes('partial', 5, db.students.findOne({ first_name: "Player"})._id, db.courses.findOne({ name: 'Breaking Bad (Nombre Clave)' })._id);
insertNotes('partial', 5, db.students.findOne({ first_name: "Nicolas"})._id, db.courses.findOne({ name: 'Mamposteria en Seco' })._id);
insertNotes('partial', 7, db.students.findOne({ first_name: "Jorge"})._id, db.courses.findOne({ name: 'Mamposteria en Seco' })._id);
insertNotes('partial', 3, db.students.findOne({ first_name: "Pipa"})._id, db.courses.findOne({ name: 'Mamposteria en Seco' })._id);
insertNotes('partial', 2, db.students.findOne({ first_name: "Era"})._id, db.courses.findOne({ name: 'Mamposteria en Seco' })._id);
insertNotes('partial', 2, db.students.findOne({ first_name: "Gustavo"})._id, db.courses.findOne({ name: 'Carpinteria Azteca' })._id);
insertNotes('partial', 2, db.students.findOne({ first_name: "Quinquela"})._id, db.courses.findOne({ name: 'Carpinteria Azteca' })._id);
insertNotes('partial', 9, db.students.findOne({ first_name: "Madonna"})._id, db.courses.findOne({ name: 'Carpinteria Azteca' })._id);
insertNotes('partial', 5, db.students.findOne({ first_name: "Porque"})._id, db.courses.findOne({ name: 'Breaking Bad (Nombre Clave)' })._id);
insertNotes('partial', 4, db.students.findOne({ first_name: "Player"})._id, db.courses.findOne({ name: 'Breaking Bad (Nombre Clave)' })._id);
insertNotes('partial', 5, db.students.findOne({ first_name: "Player"})._id, db.courses.findOne({ name: 'Breaking Bad (Nombre Clave)' })._id);
insertNotes('partial', 5, db.students.findOne({ first_name: "Nicolas"})._id, db.courses.findOne({ name: 'Mamposteria en Seco' })._id);
insertNotes('partial', 8, db.students.findOne({ first_name: "Jorge"})._id, db.courses.findOne({ name: 'Mamposteria en Seco' })._id);
insertNotes('partial', 8, db.students.findOne({ first_name: "Pipa"})._id, db.courses.findOne({ name: 'Mamposteria en Seco' })._id);
insertNotes('partial', 1, db.students.findOne({ first_name: "Era"})._id, db.courses.findOne({ name: 'Mamposteria en Seco' })._id);
insertNotes('partial', 8, db.students.findOne({ first_name: "Gustavo"})._id, db.courses.findOne({ name: 'Carpinteria Azteca' })._id);
insertNotes('partial', 5, db.students.findOne({ first_name: "Quinquela"})._id, db.courses.findOne({ name: 'Carpinteria Azteca' })._id);
insertNotes('partial', 6, db.students.findOne({ first_name: "Madonna"})._id, db.courses.findOne({ name: 'Carpinteria Azteca' })._id);
insertNotes('partial', 5, db.students.findOne({ first_name: "Porque"})._id, db.courses.findOne({ name: 'Breaking Bad (Nombre Clave)' })._id);
insertNotes('partial', 8, db.students.findOne({ first_name: "Player"})._id, db.courses.findOne({ name: 'Breaking Bad (Nombre Clave)' })._id);
insertNotes('partial', 5, db.students.findOne({ first_name: "Player"})._id, db.courses.findOne({ name: 'Breaking Bad (Nombre Clave)' })._id);

insertNotes('final', 4, db.students.findOne({ first_name: "Nicolas"})._id, db.courses.findOne({ name: 'Mamposteria en Seco' })._id);
insertNotes('final', 5, db.students.findOne({ first_name: "Jorge"})._id, db.courses.findOne({ name: 'Mamposteria en Seco' })._id);
insertNotes('final', 2, db.students.findOne({ first_name: "Pipa"})._id, db.courses.findOne({ name: 'Mamposteria en Seco' })._id);
insertNotes('final', 5, db.students.findOne({ first_name: "Era"})._id, db.courses.findOne({ name: 'Mamposteria en Seco' })._id);
insertNotes('final', 3, db.students.findOne({ first_name: "Gustavo"})._id, db.courses.findOne({ name: 'Carpinteria Azteca' })._id);
insertNotes('final', 1, db.students.findOne({ first_name: "Quinquela"})._id, db.courses.findOne({ name: 'Carpinteria Azteca' })._id);
insertNotes('final', 3, db.students.findOne({ first_name: "Madonna"})._id, db.courses.findOne({ name: 'Carpinteria Azteca' })._id);
insertNotes('final', 2, db.students.findOne({ first_name: "Porque"})._id, db.courses.findOne({ name: 'Breaking Bad (Nombre Clave)' })._id);
insertNotes('final', 4, db.students.findOne({ first_name: "Player"})._id, db.courses.findOne({ name: 'Breaking Bad (Nombre Clave)' })._id);
insertNotes('final', 5, db.students.findOne({ first_name: "Player"})._id, db.courses.findOne({ name: 'Breaking Bad (Nombre Clave)' })._id);