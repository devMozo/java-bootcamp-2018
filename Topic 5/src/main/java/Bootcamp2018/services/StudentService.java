package Bootcamp2018.services;

import Bootcamp2018.configs.FactoryDatastore;
import Bootcamp2018.entities.Course;
import Bootcamp2018.entities.Note;
import Bootcamp2018.entities.Student;
import lombok.*;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.*;

import java.util.*;
/**
 * Represents a the service that handled the
 * business logic of the students queries
 * @author Mozo Nicolas
 * @version 1.0
 * @since 1.0
 */
public class StudentService {
	// New instance of the Student's Entity
	private Datastore oStudentDatastore = null;
	/**
	 * The constructor will initialize the datastore
	 */
	public StudentService() {
		// New factory of datastores
		FactoryDatastore oFactoryDatastore = new FactoryDatastore();
		// Get a datastore from the Morphia's Object
		this.oStudentDatastore = oFactoryDatastore.getDatastore("highschool-morphia");
	}
	/**
	 * Save a new student
	 * @param oStudent
	 */
	public void save(Student oStudent){
		this.oStudentDatastore.save(oStudent);
	}
	/**
	 * This is only for bootcamp's reason
	 * isn't a real method... or not?
	 * What do you think about it?
	 * It's something mystic, do you wanna a beer?
	 * How about the weekend?
	 */
	public void setRandomNotesToAllStudents(){
		// Services
		// Teacher
		TeacherService oTeacherService = new TeacherService();
		// Course
		CourseService oCourseService = new CourseService();
		// Note
		NoteService oNoteService = new NoteService();
		// Save random fields
		// Teacher
		oTeacherService.saveRandom();
		// Course
		oCourseService.saveRandom();
		// Get the first course
		Course oCourse = oCourseService.getFirst();
		// Get a query to implement some strange algorithm with students
		Query<Student> oQuery = this.oStudentDatastore.createQuery(Student.class);
		// Get all students as a list
		List<Student> listStudent = oQuery.asList();
		// Loop over all
		for(int i = 0; i < listStudent.size(); i++){
			// Insert three partial test
			for(int j = 0; j < 3; j++){
				// New note representing the partial
				oNoteService.saveRandomPartialToStudent(listStudent.get(i), oCourse);
			}
			// New note representing the final test
			oNoteService.saveRandomFinalToStudent(listStudent.get(i), oCourse);
		}
	}
	/**
	 * Get all students with a note greater than 4
	 * passing the Course's ID
	 * @param oCourse
	 * @return
	 */
	public ArrayList<StudentCourseNotepad> getPassedStudentFromCourse(Course oCourse){
		// First create a query to retrieve the students that were in the course
		Query<Note> oQueryPartialNotes = this.oStudentDatastore.find(Note.class)
													    .field("course").equal(oCourse)
														.field("type_note").equal(Note.enmTypeNote.PARTIAL);
		// We create a dictionary to save all correct students
		ArrayList<StudentCourseNotepad> arrStudentsAverageNotes = new ArrayList<>();
		// Convert the query to list
		final List<Note> listNote = oQueryPartialNotes.asList();
		// This boolean will allow me to check if the student exists inside the array
		boolean bExists = false;
		// Loop over all notes
		for (int i = 0; i < listNote.size(); i++){
			// Re-Initialize j and boolean for each loop
			int j = 0;
			bExists = false;
			// While the student doesn't exist and j is less than the total size of the array
			while(!bExists && j < arrStudentsAverageNotes.size()){
				// Check if the student is the same that the saved in the array
				// And save it the result inside the boolean
				bExists = listNote.get(i).getStudent().equals(arrStudentsAverageNotes.get(j).getStudent());
				// Re-check that boolean and if the student hasn't existed yet,
				// Update the j
				if(!bExists){
					j++;
				}
			}
			// If the student exist
			if(bExists){
				// I take advantage of the J and add the new note to that student
				arrStudentsAverageNotes.get(j).addPartialNote(listNote.get(i));
			// If not..
			} else {
				// Create a new Student's Notepad
				StudentCourseNotepad studentCourseNotepad = new StudentCourseNotepad(listNote.get(i).getStudent());
				// Add also the note
				studentCourseNotepad.addPartialNote(listNote.get(i));
				// Add the student's notepad to the array
				arrStudentsAverageNotes.add(studentCourseNotepad);

			}
		}
		// Initialize a new query
		Query<Note> oQueryFinalNote = null;
		// Now I have all average note of each student
		// So we are gonna loop each one
		for (int i = 0; i < arrStudentsAverageNotes.size(); i++){
			// Get the final note if exists and is greater or equals than 4
			oQueryFinalNote = this.oStudentDatastore.createQuery(Note.class)
													.field("type_note").equal(Note.enmTypeNote.FINAL)
													.field("student").equal(arrStudentsAverageNotes.get(i).getStudent())
													.filter("note >=", 4);
			// If the note doesn't exist and the average of the partial tests is under the 4
			if(oQueryFinalNote.count() <= 0 || arrStudentsAverageNotes.get(i).getAveragePartialNotes() < 4){
				// I'll remove this student
				arrStudentsAverageNotes.remove(i);
				i--;
			} else {
				arrStudentsAverageNotes.get(i).setFinalNote(oQueryFinalNote.get());
			}
		}
		// Return the iterator
		return arrStudentsAverageNotes;
	}
	/**
	 * Internal class that allows me to handle a virtual notepad about
	 * the califications of each user
	 * @author Mozo Nicolas
	 * @version 1.0
	 * @since 1.0
	 */
	@Setter
	@Getter
	@EqualsAndHashCode
	@RequiredArgsConstructor
	public class StudentCourseNotepad{
		@NonNull
		private Student student;
		private Course course;
		private ArrayList<Note> arrPartialNotes = new ArrayList<>();
		private Note finalNote;
		/**
		 * Add new note to the array of partial tests
		 * @param oNote
		 */
		public void addPartialNote(Note oNote){
			this.arrPartialNotes.add(oNote);
		}
		/**
		 * Return an integer that represents the average of all notes
		 * @return The average of the notes
		 */
		public int getAveragePartialNotes(){
			// By default the response is 0
			int iResponse = 0;
			// Loop over all notes
			for (int i = 0; i < this.arrPartialNotes.size(); i++){
				// I accumulate each note in the response
				iResponse += this.arrPartialNotes.get(i).getNote();
			}
			// Return the response divide by the size of the array
			return iResponse / this.arrPartialNotes.size();
		}
	}
}
