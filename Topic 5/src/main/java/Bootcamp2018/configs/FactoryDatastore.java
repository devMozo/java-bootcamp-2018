package Bootcamp2018.configs;

import com.mongodb.MongoClient;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

/**
 * Represents a factory of datastores..
 * @author Mozo Nicolas
 * @version 1.0
 * @since 1.0
 */
public class FactoryDatastore {
	// Create an instance of Morphia
	private Morphia myMorphia = MorphiaConnection.getInstance().getMorphiaMongoDBConnection();
	/**
	 * Get the datastore respecting the passed string
 	 * @param str
	 * @return
	 */
	public Datastore getDatastore(String str){
		// By default the response is null
		Datastore oResponse = null;
		// If the datastore that I want is the Highschool
		if(str.equals("highschool-morphia")){
			oResponse = this.myMorphia.createDatastore(new MongoClient(), "highschool-morphia");
		}
		// Return the generated response
		return oResponse;
	}
}
