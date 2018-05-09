package bootcamp2018.User;

import java.util.ArrayList;
/**
 * Represents the interface that
 * the UserDAO must implement
 * @author Mozo Nicolas
 * @version 1.0
 * @since 1.0
 */
public interface iUserDAO {
	public void create(User _oUser);
	public User read(int _id);
	public ArrayList<User> readAll();
	public void update(User _oUser);
	public void delete(User _oUser);
}
