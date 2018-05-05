
use highschool;

db.courses.find({
    id_teacher: db.teachers.findOne()._id // Here would go the id that we wanna check
}, {
    _id: 0,
    id_teacher: 0
}).sort({ name: -1 });

