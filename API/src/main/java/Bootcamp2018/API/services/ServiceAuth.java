package Bootcamp2018.API.services;

import Bootcamp2018.API.daos.DAOAuth;
import Bootcamp2018.API.entities.User;
import Bootcamp2018.API.helpers.EncryptionString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Map;

/**
 * Represents the service to manage
 * the login and register of each user
 * @author Mozo Nicolas
 * @version 1.0
 * @since 1.0
 */
@Service
public class ServiceAuth {
	// The repository to make some actions with Redis
	@Autowired
	private DAOAuth daoAuth;
	/**
	 * Save a new User's Session
	 * @param oUser
	 * @return An encoded ID
	 */
	@Transactional
	public String save(User oUser) throws Exception {
		// By default the string is null
		String strEncodedID = null;
		// Try to make some different :OOOOO RT: Steve Jobs
		if(oUser != null) {
			// Encoded the id
			strEncodedID = EncryptionString.encrypt(oUser.getId().toString());
			// Save the user with this encoded id
			this.daoAuth.save(oUser, strEncodedID);
		}
		// Return that encoded id
		return strEncodedID;
	}
	/**
	 * Find the User inside the Redis's DB
	 * with the encrypted id
	 * @return
	 */
	@Transactional
	public User find(String encryptedID) {
		// Return the result
		return this.daoAuth.find(encryptedID);
	}
	/**
	 *	Find entries inside the Redis's DB
	 * @return All entries with its encodedID and User's Object
	 */
	@Transactional
	public Map<String, User> findAll() {
		return this.daoAuth.findAll();
	}
	/**
	 * Update a user with the encoded ID
	 * @param oUser
	 */
	@Transactional
	public void update(User oUser) throws Exception {
		this.daoAuth.update(oUser, EncryptionString.encrypt(oUser.getId().toString()));
	}
	/**
	 * Delete a user from the Redis's DB
	 * @param oUser
	 */
	@Transactional
	public void delete(User oUser) throws Exception {
		this.daoAuth.delete(EncryptionString.encrypt(oUser.getId().toString()));
	}
}
