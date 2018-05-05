package Bootcamp2018.services;

import Bootcamp2018.configs.FactoryDatastore;
import Bootcamp2018.entities.Course;
import Bootcamp2018.entities.Note;
import Bootcamp2018.entities.Student;
import org.mongodb.morphia.Datastore;

import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Represents a service for the note's queries
 * @author Mozo Nicolas
 * @version 1.0
 * @since 1.0
 */
public class NoteService {
	// New instance of the Note's Entity
	private Datastore oNoteDatastore = null;
	/**
	 * The constructor will initialize the datastore
	 */
	public NoteService() {
		// New factory of datastores
		FactoryDatastore oFactoryDatastore = new FactoryDatastore();
		// Get a datastore from the Morphia's Object
		this.oNoteDatastore = oFactoryDatastore.getDatastore("highschool-morphia");
	}
	/**
	 * Save a new Note
	 * @param oNote
	 */
	public void save(Note oNote){
		this.oNoteDatastore.save(oNote);
	}
	/**
	 * Create a random partial note
	 * to an student with a random course and save it
	 * @param oStudent
	 * @param oCourse
	 */
	public void saveRandomPartialToStudent(Student oStudent, Course oCourse){
		// Create a random note and save it in an object
		Note oNote = this.createRandomNote();
		// Set the victim.. ehm the student
		oNote.setStudent(oStudent);
		// Set the course
		oNote.setCourse(oCourse);
		// Set the kind of the test
		oNote.setTypeNote(Note.enmTypeNote.PARTIAL);
		// Save it
		this.save(oNote);
	}
	/**
	 * Create a random final note
	 * to an student with a random course and save it
	 * @param oStudent
	 * @param oCourse
	 */
	public void saveRandomFinalToStudent(Student oStudent, Course oCourse){
		// Create a random note and save it in an object
		Note oNote = this.createRandomNote();
		// Set the victim.. ehm the student
		oNote.setStudent(oStudent);
		// Set the course
		oNote.setCourse(oCourse);
		// Set the kind of the test
		oNote.setTypeNote(Note.enmTypeNote.FINAL);
		// Save it
		this.save(oNote);
	}
	/**
	 * Create a random note
	 * without student's id and course's id
	 * @return
	 */
	private Note createRandomNote(){
		// New note representing the iº partial test
		Note oNote = new Note();
		// Set the random note
		oNote.setNote(ThreadLocalRandom.current().nextInt(0, 10));
		// Return the Note
		return oNote;
	}
}
