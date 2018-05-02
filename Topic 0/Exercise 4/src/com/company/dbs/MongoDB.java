package com.company.dbs;
/**
 * Represents a MongoDB database
 * @author Mozo Nicolas
 * @version 1.0
 * @since 1.0
 */
public class MongoDB implements iDB{
	/**
	 * Implements the connection to MongoDB
	 */
	@Override
	public void connect() {
		System.out.println("Connecting to the MongoDB...");
	}
}
