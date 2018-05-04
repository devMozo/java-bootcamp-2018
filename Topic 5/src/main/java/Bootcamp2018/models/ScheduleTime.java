package Bootcamp2018.models;

import org.mongodb.morphia.annotations.Embedded;

import java.util.ArrayList;
/**
 * Represents an scheduletime that will be embedded inside the Course
 * @author Mozo Nicolas
 * @version 1.0
 * @since 1.0
 */
@Embedded
public class ScheduleTime {
	private ArrayList<Day> arrayListDays;
}
