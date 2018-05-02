package com.company.dbs;
/**
 * Represents a MySQL database
 * @author Mozo Nicolas
 * @version 1.0
 * @since 1.0
 */
public class MySQL implements iDB{
	/**
	 * Implements the connection to MySQL
	 */
	@Override
	public void connect() {
		System.out.println("Connecting to the MySQL...");
	}
}
