package Bootcamp2018;

import Bootcamp2018.configs.FactoryDatastore;
import Bootcamp2018.configs.MorphiaConnection;
import Bootcamp2018.entities.Course;
import Bootcamp2018.entities.Note;
import Bootcamp2018.entities.Student;
import Bootcamp2018.services.CourseService;
import Bootcamp2018.services.StudentService;
import com.mongodb.MongoClient;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.query.Query;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Represents an app handled with Morphia
 * @author Mozo Nicolas
 * @version 1.0
 * @since 1.0
 */
public class App 
{
	/**
	 * Main
	 * @param args
	 */
    public static void main( String[] args ) {
		// New Student's Service
		StudentService oStudentService = new StudentService();
		// Loop 10 times to make new students
		for(int i = 0; i < 10; i++){
			// Create a new student
			Student newStudent = new Student();
			// Create a new Mozo's Android
			newStudent.setStrFirstName("Mozo");
			newStudent.setStrLastName("Nº" + ThreadLocalRandom.current().nextInt(0, 10));
			newStudent.setDteBirth(new Date());
			// I've created life!!
			oStudentService.save(newStudent);
		}
		// Insert random notes in random courses with random teacher :OOOO
		oStudentService.setRandomNotesToAllStudents();
		// New Course's Service
		CourseService oCourseService = new CourseService();
		// Get the first course thas has inserted in the DB
		Course oCourse = oCourseService.getFirst();
		// Get the list of student that passed the course
		ArrayList<StudentService.StudentCourseNotepad> listStudents = oStudentService.getPassedStudentFromCourse(oCourse);
		// Animation
		System.out.println("The people have passed the course");
		// Loop over all result
		for (int i = 0; i < listStudents.size(); i++){
			// Animation
			System.out.println(listStudents.get(i).getStudent().getStrFirstName()
								+ " "
								+ listStudents.get(i).getStudent().getStrLastName()
								+ " with an average note of: "
								+ listStudents.get(i).getAveragePartialNotes()
								+ " and with his final test passed with "
								+ listStudents.get(i).getFinalNote().getNote());
		}
    }
}
