package com.company.dbs;
/**
 * Represents a MariaDB database
 * @author Mozo Nicolas
 * @version 1.0
 * @since 1.0
 */
public class MariaDB implements iDB{
	/**
	 * Implements the connection to MariaDB
	 */
	@Override
	public void connect() {
		System.out.println("Connecting to the MariaDB...");
	}
}
