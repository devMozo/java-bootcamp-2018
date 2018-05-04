package Bootcamp2018.entities;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Reference;

/**
 * Represents a Note
 * @author Mozo Nicolas
 * @version 1.0
 * @since 1.0
 */
@Entity("notes")
public class Note {
	@Id
	private ObjectId id;
	private enum type_note {
		PARTIAL,
		FINAL
	};
	private int note;
	@Reference
	private Student student;
	@Reference
	private Course course;
}
