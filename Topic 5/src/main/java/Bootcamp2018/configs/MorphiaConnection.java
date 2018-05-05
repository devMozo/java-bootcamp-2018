package Bootcamp2018.configs;

import org.mongodb.morphia.Morphia;

/**
 * Singleton that return always the Morphia's Object
 * that allows us connect to MongoDB
 * @author Mozo Nicolas
 * @version 1.0
 * @since 1.0
 */
public class MorphiaConnection {
	// Instance of the class
	private static MorphiaConnection oInstance = new MorphiaConnection();
	private final Morphia oMorphia = new Morphia();
	/**
	 * Get the current instance
	 * @return MorhpiaConnection's Intance
	 */
	public static MorphiaConnection getInstance() {
		return oInstance;
	}
	/**
	 *	For security reason we putted a private constructor
 	 */
	private MorphiaConnection() {
		// Tell Morphia where to find your classes
		// can be called multiple times with different packages or classes
		this.oMorphia.mapPackage("org.mongodb.morphia.example");
	}
	/**
	 * Return Morphia's Connection
	 */
	public Morphia getMorphiaMongoDBConnection(){
		return this.oMorphia;
	}
}
