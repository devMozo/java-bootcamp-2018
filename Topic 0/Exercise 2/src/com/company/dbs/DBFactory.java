package com.company.dbs;
/**
 * Represents an factory of databases.
 * @author Mozo Nicolas
 * @version 1.0
 * @since 1.0
 */
public class DBFactory {
	/**
	 * Get the passed DB with its respective string name
	 * @param strDB
	 * @return null || The connection
	 */
	public iDB getDB(String strDB){
		// By default the connection is null
		iDB oResponse = null;
		// If the user needs MySQL
		if(strDB.equalsIgnoreCase("MySQL")){
			// We return MySQL
			oResponse = new MySQL();
		// If the user need MongoDB
		} else if(strDB.equalsIgnoreCase("MongoDB")){
			// We return MongoDB
			oResponse = new MongoDB();
		// If the user needs MariaDB
		} else if(strDB.equalsIgnoreCase("MariaDB")){
			// We return MariaDB
			oResponse = new MariaDB();
		// If the user needs PostgreSQL
		} else if(strDB.equalsIgnoreCase("PostgreSQL")){
			// We return PostgreSQL
			oResponse = new PostgreSQL();
		}
		// Return the resulted response
		return oResponse;
	}
}
