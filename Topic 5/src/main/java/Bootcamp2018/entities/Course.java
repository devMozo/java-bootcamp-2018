package Bootcamp2018.entities;

import Bootcamp2018.models.ScheduleTime;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.*;

/**
 * Represents a Course
 * @author Mozo Nicolas
 * @version 1.0
 * @since 1.0
 */
@Entity("courses")
@Getter
@Setter
public class Course {
	@Id
	private ObjectId id;
	@Reference
	private Teacher teacher;
	@Embedded("schedule_time")
	private ScheduleTime oScheduleTime;
}
