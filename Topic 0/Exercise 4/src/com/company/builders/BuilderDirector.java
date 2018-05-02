package com.company.builders;

import com.company.dbs.*;
/**
 * The director builder of our databases's builders
 * @author Mozo Nicolas
 * @version 1.0
 * @since 1.0
 */
public class BuilderDirector {
	// Initialize the builder in null
	iDBBuilder oBuilder = null;
	/**
	 * BuilderDirector's Constructor
	 * @param oBuilder
	 */
	public BuilderDirector(iDBBuilder oBuilder) {
		this.oBuilder = oBuilder;
	}
	/**
	 * Construct the database
	 */
	public void constructDatabase(){
		// Construct the connection
		this.oBuilder.buildConnection();
	}
	/**
	 * Get the database..
	 * @return the database
	 */
	public iDB getDB(){
		return this.oBuilder.getDB();
	}
}
