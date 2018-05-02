package com.company.dbs;

import java.util.Date;
/**
 * Represents a proxy to databases.
 * @author Mozo Nicolas
 * @version 1.0
 * @since 1.0
 */
public class DBProxy {
	// The current database
	private iDB oCurrentDatabase;
	/**
	 * A constructor that indicates
	 * since the proxy has started
	 */
	public DBProxy() {
		System.out.println("Creating proxy at " + new Date());
	}
	/**
	 * Connect to the database
	 */
	public void connect(){
		// If there isn't database until now
		if(this.oCurrentDatabase == null){
			// Get the database through the factory
			this.oCurrentDatabase = new MySQL();
		}
		// Connect to the database
		this.oCurrentDatabase.connect();
	}
}
