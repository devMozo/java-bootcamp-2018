package Bootcamp2018.entities;

import Bootcamp2018.models.ScheduleTime;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Reference;

/**
 * Represents a Course
 * @author Mozo Nicolas
 * @version 1.0
 * @since 1.0
 */
@Entity("courses")
public class Course {
	@Id
	private ObjectId id;
	@Reference
	private Teacher teacher;
	@Embedded
	private ScheduleTime schedule_time;
}
