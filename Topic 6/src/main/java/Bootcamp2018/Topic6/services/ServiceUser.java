package Bootcamp2018.Topic6.services;

import Bootcamp2018.Topic6.entities.Product;
import Bootcamp2018.Topic6.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import Bootcamp2018.Topic6.daos.iDAOUser;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

/**
 * Represents the services to all users app
 * @author Mozo Nicolas
 * @version 1.0
 * @since 1.0
 */
@Service
public class ServiceUser {
	// Get our repo
	@Autowired
	iDAOUser iDAOUser;
	/**
	 * Add a new user
	 * @param oUser
	 */
	@Transactional
	public User add(User oUser) {
		// Default response
		User oInnerUser = null;
		// If the user is null
		if(oUser == null){
			try {
				throw new Exception("You need to pass a not-null user");
			} catch (Exception e) {
				e.printStackTrace();
			}
			// If not..
		} else {
			// Save the user
			oInnerUser = this.iDAOUser.save(oUser);
		}
		// Return the saved user
		return oInnerUser;
	}
	/**
	 * Update a user
	 * @param oUser
	 */
	@Transactional
	public User update(User oUser) {
		// Default response
		User oInnerUser = null;
		// If the index position is bigger than the size of the array
		if(oUser == null || !this.iDAOUser.existsById(oUser.getId())){
			try {
				throw new IndexOutOfBoundsException("You've wanted to delete a user that doesn't exist");
			} catch (Exception e) {
				e.printStackTrace();
			}
			// If not..
		} else {
			// Update the user
			oInnerUser = this.iDAOUser.save(oUser);
		}
		// Return the updated user
		return oInnerUser;
	}
	/**
	 * Remove a user
	 * @param oUser
	 */
	@Transactional
	public void remove(User oUser) {
		// If the index position is bigger than the size of the array
		if(oUser == null || !this.iDAOUser.existsById(oUser.getId())){
			try {
				throw new IndexOutOfBoundsException("You've wanted to delete a user that doesn't exist");
			} catch (Exception e) {
				e.printStackTrace();
			}
			// If not..
		} else {
			// Remove user
			this.iDAOUser.delete(oUser);
		}
	}
	/**
	 * Get an specific user
	 * @return Optional<User>
	 */
	@Transactional
	public Optional<User> get(Long id) {
		// Get the first coincidence
		return this.iDAOUser.findById(id);
	}
	/**
	 * Get all users
	 * @return List<User>
	 */
	@Transactional
	public List<User> getAll() {
		// Get the first coincidence
		return this.iDAOUser.findAll();
	}
}
