package Bootcamp2018;

import Bootcamp2018.configs.MorphiaConnection;
import org.mongodb.morphia.Morphia;

/**
 * Represents an app handled with Morphia
 * @author Mozo Nicolas
 * @version 1.0
 * @since 1.0
 */
public class App 
{
    public static void main( String[] args ) {

		Morphia myMorphia = MorphiaConnection.getInstance().getMorphiaMongoDBConnection();

    }
}
