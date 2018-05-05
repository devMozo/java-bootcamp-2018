package Bootcamp2018.services;

import Bootcamp2018.configs.FactoryDatastore;
import Bootcamp2018.entities.Teacher;
import org.mongodb.morphia.Datastore;

import java.util.Date;

/**
 * Represents a service for the teachers' queries
 * @author Mozo Nicolas
 * @version 1.0
 * @since 1.0
 */
public class TeacherService {
	// New instance of the Teacher's Entity
	private Datastore oTeacherDatastore = null;
	/**
	 * The constructor will initialize the datastore
	 */
	public TeacherService() {
		// New factory of datastores
		FactoryDatastore oFactoryDatastore = new FactoryDatastore();
		// Get a datastore from the Morphia's Object
		this.oTeacherDatastore = oFactoryDatastore.getDatastore("highschool-morphia");
	}
	/**
	 * Save a new Teacher
	 * @param oTeacher
	 */
	public void save(Teacher oTeacher){
		this.oTeacherDatastore.save(oTeacher);
	}
	/**
	 * Create a random teacher and save it
	 */
	public void saveRandom(){
		// Create a new ghost teacher to insert inside a ghost course
		Teacher newTeacher = new Teacher();
		// Insert some ghost fields
		newTeacher.setStrFirstName("Teacher");
		newTeacher.setStrLastName("Nº 1");
		newTeacher.setDteBirth(new Date());
		// Save inside the DB
		this.save(newTeacher);
	}
}

