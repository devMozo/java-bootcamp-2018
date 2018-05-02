package com.company.builders;

import com.company.dbs.*;
/**
 * Represents the MongoDB's Builder
 * @author Mozo Nicolas
 * @version 1.0
 * @since 1.0
 */
public class BuilderMongoDB implements iDBBuilder{
	// The database
	iDB oDB = null;
	/**
	 * Implements the way to connect to the database
	 */
	@Override
	public void buildConnection() {
		this.oDB = new MongoDB();
	}
	/**
	 * Get the current connection
	 * @return the database
	 */
	@Override
	public iDB getDB() {
		return this.oDB;
	}
}
