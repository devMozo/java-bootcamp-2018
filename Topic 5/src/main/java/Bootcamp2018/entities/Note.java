package Bootcamp2018.entities;

import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Property;
import org.mongodb.morphia.annotations.Reference;

/**
 * Represents a Note
 * @author Mozo Nicolas
 * @version 1.0
 * @since 1.0
 */
@Entity("notes")
@Getter
@Setter
public class Note {
	@Id
	private ObjectId id;
	public enum enmTypeNote {
		PARTIAL,
		FINAL
	};
	@Property("type_note")
	private enmTypeNote typeNote;
	private int note;
	@Reference
	private Student student;
	@Reference
	private Course course;
}
