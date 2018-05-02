package com.company.dbs;
/**
 * Represents a PostgreSQL database
 * @author Mozo Nicolas
 * @version 1.0
 * @since 1.0
 */
public class PostgreSQL implements iDB{
	/**
	 * Implements the connection to PostgreSQL
	 */
	@Override
	public void connect() {
		System.out.println("Connecting to the PostgreSQL...");
	}
}
