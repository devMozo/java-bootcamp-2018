
use highschool;

var arrStudents = new Array();
var notes = db.notes.find({
                note: { $gte: 4 },
                id_course: db.courses.findOne()._id // Here would go the id that we wanna check
            }, { 
                _id : 0, 
                id_course: 0 
            }).forEach( function(note) { 
                arrStudents.push(note.id_student);
            });

db.students.find({
    _id: { $in: arrStudents }    
}, {
    _id: 0,
    date_of_birth: 0
});