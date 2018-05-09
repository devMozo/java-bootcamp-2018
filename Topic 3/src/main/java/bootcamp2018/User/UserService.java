package bootcamp2018.User;

import io.swagger.annotations.*;

import java.util.ArrayList;

/**
 * Represents the service of the User, it's based in CRUD
 * @author Mozo Nicolas
 * @version 1.0
 * @since 1.0
 */
@Api(value = "/user", description = "An API to handle Users", produces = "application/json")
public class UserService implements iUserService{
	// Our DAO
	UserDAO oUserDAO = new UserDAO();
	/**
	 * Create a new User
	 * @param _oUser
	 */
	@ApiOperation(value = "Add a new User.", httpMethod = "POST", consumes =  "User", code = 200)
	@ApiResponses(value = {@ApiResponse(code = 200, message = "All was good!"),
			@ApiResponse(code = 404, message = "You need to pass a not-null user and with its completed fields")})
	@Override public void create(@ApiParam(value = "The user to add", name = "_oUser", required = true, type = "User") User _oUser) {
		this.oUserDAO.create(_oUser);
	}
	/**
	 * Read a User
	 * @param _id
	 * @return
	 */
	@ApiOperation(value = "Read a User.", httpMethod = "POST", produces =  "User", code = 200)
	@Override public User read(@ApiParam(value = "The User's ID", name = "_id", required = true, type = "int") int _id) {
		return this.oUserDAO.read(_id);
	}
	/**
	 * Read All Users
	 * @return
	 */
	@ApiOperation(value = "Read all Users.", httpMethod = "POST", produces =  "User", code = 200)
	@Override public ArrayList<User> readAll() {
		return this.oUserDAO.readAll();
	}
	/**
	 * Update a User
	 * @param _oUser
	 */
	@ApiOperation(value = "Update a User.", httpMethod = "POST", consumes =  "User", code = 200)
	@ApiResponses(value = {@ApiResponse(code = 200, message = "All was good!"),
			@ApiResponse(code = 404, message = "You need to pass a not-null user and with its completed fields")})
	@Override public void update(@ApiParam(value = "The user to update", name = "_oUser", required = true, type = "User") User _oUser) {
		this.oUserDAO.update(_oUser);
	}
	/**
	 * Delete a User
	 * @param _oUser
	 */
	@ApiOperation(value = "Delete a User.", httpMethod = "POST", consumes =  "User", code = 200)
	@ApiResponses(value = {@ApiResponse(code = 200, message = "All was good!"),
			@ApiResponse(code = 404, message = "You need to pass a not-null user and with its completed fields")})
	@Override public void delete(@ApiParam(value = "The user to delete", name = "_oUser", required = true, type = "User") User _oUser) {
		this.oUserDAO.delete(_oUser);
	}
}
