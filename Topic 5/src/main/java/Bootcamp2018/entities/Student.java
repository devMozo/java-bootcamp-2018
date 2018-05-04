package Bootcamp2018.entities;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

import java.util.Date;
/**
 * Represents a Student
 * @author Mozo Nicolas
 * @version 1.0
 * @since 1.0
 */
@Entity("students")
public class Student {
	@Id
	private ObjectId id;
	private String first_name;
	private String last_name;
	private Date date_of_birth;
}