package bootcamp2018.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

/**
 * Represents the DAO (Data Access Object) of the User, it's based in CRUD
 * @author Mozo Nicolas
 * @version 1.0
 * @since 1.0
 */
public class UserDAO implements iUserDAO{
	// Our DB :O IT'S A TRAP!!
	ArrayList<User> oUserDB = new ArrayList<>();
	/**
	 * Create a new User and insert it in the DB
	 */
	@Override
	public void create(User _oUser) {
		// If the user is not null
		/**
		 * NOTE: We can make a lot of validations about the user
		 * but this is a test, please consider my position..
		 */
		if(_oUser != null){
			// Add the User
			this.oUserDB.add(_oUser);
		}
	}
	/**
	 * Read a user from the DB and return it
	 * @param _id
	 * @return An specific User || Null
	 */
	@Override
	public User read(int _id) {
		// Initialize a counter
		int i = 0;
		// Loop over all DB
		while(this.oUserDB.get(i).getId() != _id){
			// Update the counter
			i++;
		}
		// Return the user that stay in the position of the updated index
		return this.oUserDB.get(i);
	}
	/**
	 * Read all users from the DB
	 * @return All the DB. OMG!!
	 */
	@Override
	public ArrayList<User> readAll() {
		return this.oUserDB;
	}
	/**
	 * Update a User
	 * @param _oUser
	 */
	@Override
	public void update(final User _oUser) {
		// If the user is not null
		/**
		 * NOTE: We can make a lot of validations about the user
		 * but this is a test, please consider my position..
		 */
		if(_oUser != null){
			// Get the position of the old user that we are gonna update
			Optional<User> optUser = this.oUserDB.stream()
					  							  .filter(user -> user.getId() == _oUser.getId())
					  							  .findFirst();
			// Get its position inside the DB
			int index = this.oUserDB.indexOf(optUser.get());
			// Delete it
			this.oUserDB.remove(index);
			// Add the new User
			this.oUserDB.add(index, _oUser);
		}
	}
	/**
	 * Delete a User
	 * @param _oUser
	 */
	@Override
	public void delete(User _oUser) {
		// If the user is not null
		/**
		 * NOTE: We can make a lot of validations about the user
		 * but this is a test, please consider my position..
		 */
		if(_oUser != null){
			// Add the User
			this.oUserDB.remove(_oUser);
		}
	}
}
