package Bootcamp2018.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Property;

import java.util.Date;
/**
 * Represents a Student
 * @author Mozo Nicolas
 * @version 1.0
 * @since 1.0
 */
@Entity("students")
@Getter
@Setter
@EqualsAndHashCode
public class Student {
	@Id
	private ObjectId id;
	@Property("first_name")
	private String strFirstName;
	@Property("last_name")
	private String strLastName;
	@Property("date_of_birth")
	private Date dteBirth;
}