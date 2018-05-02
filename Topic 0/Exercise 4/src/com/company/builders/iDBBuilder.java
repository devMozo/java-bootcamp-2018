package com.company.builders;

import com.company.dbs.iDB;
/**
 * Represents an interface that will define the builder pattern's methods
 * @author Mozo Nicolas
 * @version 1.0
 * @since 1.0
 */
public interface iDBBuilder {
	/**
	 * Make the connection to the DB
	 */
	public void buildConnection();
	/**
	 * Get the DB
	 * @return the DB
	 */
	public iDB getDB();
}
