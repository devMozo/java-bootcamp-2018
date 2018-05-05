package Bootcamp2018.services;

import Bootcamp2018.configs.FactoryDatastore;
import Bootcamp2018.entities.Course;
import Bootcamp2018.entities.Teacher;
import Bootcamp2018.models.Day;
import Bootcamp2018.models.ScheduleTime;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.FindOptions;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.Sort;

import java.util.ArrayList;
import java.util.Date;

/**
 * Represents a service for the courses' queries
 * @author Mozo Nicolas
 * @version 1.0
 * @since 1.0
 */
public class CourseService {
	// New instance of the Course's Entity
	private Datastore oCourseDatastore = null;
	/**
	 * The constructor will initialize the datastore
	 */
	public CourseService() {
		// New factory of datastores
		FactoryDatastore oFactoryDatastore = new FactoryDatastore();
		// Get a datastore from the Morphia's Object
		this.oCourseDatastore = oFactoryDatastore.getDatastore("highschool-morphia");
	}
	/**
	 * Save a new course
	 * @param oCourse
	 */
	public void save(Course oCourse){
		this.oCourseDatastore.save(oCourse);
	}
	/**
	 * Save random course
	 */
	public void saveRandom(){
		// Create a query to handle teacher like the government :OOOOO
		Query<Teacher> oQueryTeacher = this.oCourseDatastore.createQuery(Teacher.class);
		// New schedule time to insert inside the course
		ScheduleTime oScheduleTime = new ScheduleTime();
		// Days
		final Day oMondayDay = new Day();
		// Set data
		oMondayDay.setHour(new Date());
		oMondayDay.setDay(Day.Daytime.MONDAY);
		// Another Day
		final Day oFridayDay = new Day();
		// Set data
		oFridayDay.setHour(new Date());
		oFridayDay.setDay(Day.Daytime.FRIDAY);
		// Set this day to the schedule time
		oScheduleTime.setArrayListDays(new ArrayList<Day>(){{
			add(oMondayDay);
			add(oFridayDay);
		}});
		// Create a ghost course
		Course newCourse = new Course();
		// Set the teacher
		newCourse.setTeacher(oQueryTeacher.get());
		newCourse.setOScheduleTime(oScheduleTime);
		// Save the course
		this.save(newCourse);
	}
	/**
	 * Get the last inserted course
	 */
	public Course getFirst(){
		// Return the course ordered by descending
		return this.oCourseDatastore.createQuery(Course.class)
									.order(Sort.naturalDescending())
									.get();
	}
}
