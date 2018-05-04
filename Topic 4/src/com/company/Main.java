package com.company;

import java.sql.SQLException;
/**
 * Represents the system of a highschool
 * @author Mozo Nicolas
 * @version 1.0
 * @since 1.0
 */
public class Main {
	/**
	 * Main
	 * @param args
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
		// Create a new highschool
        HighSchool oHighSchoold = new HighSchool();
        // Show a teacher with his courses
        oHighSchoold.showTeacherWithTheirCourses(2);
    }
}
