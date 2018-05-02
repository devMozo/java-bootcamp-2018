package com.company;

/**
 * Represents a connection to a database made with a Singleton's pattern
 * @author Mozo Nicolas
 * @version 1.0
 * @since 1.0
 */
public class ConnectionSingleton {
	// New instance of the connection, we initialize it in null
	private static ConnectionSingleton oInstance = null;
	/**
	 *  We become the constructor as a private method
	 *  because we need to avoid externals inputs
	 */
	private ConnectionSingleton(){}
	/**
	 *  Create a public static method that allows us
	 *  to get the current instance if exists
	 * @return the instance of the connection
	 */
	public static ConnectionSingleton getInstance(){
		// If the connection doesn't exist
		if(oInstance == null){
			// Create a new connection a save it in the static instance
			oInstance = new ConnectionSingleton();
		}
		// Return that connection
		return oInstance;
	}
}
