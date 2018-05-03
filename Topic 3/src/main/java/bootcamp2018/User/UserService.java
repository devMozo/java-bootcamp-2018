package bootcamp2018.User;

import java.util.ArrayList;

/**
 * Represents the service of the User, it's based in CRUD
 * @author Mozo Nicolas
 * @version 1.0
 * @since 1.0
 */
public class UserService implements iUserService{
	// Our DAO
	UserDAO oUserDAO = new UserDAO();
	/**
	 * Create a new User
	 * @param _oUser
	 */
	@Override public void create(User _oUser) {
		this.oUserDAO.create(_oUser);
	}
	/**
	 * Read a User
	 * @param _id
	 * @return
	 */
	@Override public User read(int _id) {
		return this.oUserDAO.read(_id);
	}
	/**
	 * Read All Users
	 * @return
	 */
	@Override public ArrayList<User> readAll() {
		return this.oUserDAO.readAll();
	}
	/**
	 * Update a User
	 * @param _oUser
	 */
	@Override public void update(User _oUser) {
		this.oUserDAO.update(_oUser);
	}
	/**
	 * Delete a User
	 * @param _oUser
	 */
	@Override public void delete(User _oUser) {
		this.oUserDAO.delete(_oUser);
	}
}
