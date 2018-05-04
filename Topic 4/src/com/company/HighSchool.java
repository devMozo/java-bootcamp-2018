package com.company;

import java.sql.*;
import java.util.ArrayList;
/**
 * Represents the model of a HighSchool
 * @author Mozo Nicolas
 * @version 1.0
 * @since 1.0
 */
public class HighSchool {
	// Connection to the database
	private Connection oConn;
	/**
	 * Construtor of the class
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public HighSchool() throws SQLException, ClassNotFoundException {
		// Upload the driver to the memory
		Class.forName("com.mysql.jdbc.Driver");
		// Get the connection from the driver
		this.oConn = DriverManager.getConnection("jdbc:mysql://localhost/highschool", "root", "");
	}
	/**
	 * Show a determined teacher with his courses and hours that has that day
	 * @param _idTeacher
	 * @throws SQLException
	 */
	public void showTeacherWithTheirCourses(int _idTeacher) throws SQLException {
		// Prepare the string that will be passed as a query
		String strQuery = "SELECT people.first_name, people.last_name,\n"
				               + "days.value, day_x_course.hours,\n"
				               + "courses.name\n"
						+ "FROM teachers\n"
				        + "INNER JOIN people\n" + "    "
						+ "    ON people.id = teachers.id_person\n"
						+ "INNER JOIN courses\n"
						+ "    ON courses.id_teacher = teachers.id\n"
						+ "INNER JOIN day_x_course\n"
						+ "    ON day_x_course.id_course = courses.id\n"
						+ "INNER JOIN days\n"
						+ "    ON days.id = day_x_course.id_day\n"
						+ "WHERE teachers.id = ?\n"
						+ "ORDER BY days.id ASC";
		// Prepare stament to pre-compiled the query
		PreparedStatement oPrepareStatement = this.oConn.prepareStatement(strQuery);
		// Set the teacher's id
		oPrepareStatement.setInt(1, _idTeacher);
		// Execute the query and its result saved in a Result Set
		ResultSet oResultSet = oPrepareStatement.executeQuery();
		// If exists the first row I will initialize the result in it
		if(oResultSet.first()) {
			// Show the teacher in console
			System.out.println("Teacher: " + oResultSet.getString("last_name") + ", " + oResultSet.getString("first_name"));
			System.out.println("Schedule: ");
			// Loop over the data
			do {
				System.out.println(oResultSet.getString("value") + " " + oResultSet.getString("hours") + " hour/s" + " " + oResultSet.getString("name"));
			} while (oResultSet.next());
		}
	}
}
