package Bootcamp2018.API.daos;

import Bootcamp2018.API.entities.User;
import org.apache.tomcat.util.security.MD5Encoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.Map;

/**
 * Represents the repository to manage
 * the login and register of each user
 * @author Mozo Nicolas
 * @version 1.0
 * @since 1.0
 */
@Repository
public class DAOAuth {
	// We are gonna save all users inside this KEY :OOO
	private static final String KEY = "Users";
	// Helper class that simplifies Redis data access code.
	private RedisTemplate<String, Object> redisTemplate;
	// Redis map specific operations working on a hash.
	private HashOperations<String, String, User> hashOperations;
	/**
	 *	Class's Constructor
	 * @param redisTemplate
	 */
	@Autowired
	public DAOAuth(RedisTemplate<String, Object> redisTemplate) {
		this.redisTemplate = redisTemplate;
	}
	/**
	 * After we construct the object we need
	 * to initialize it with this awesome method
	 */
	@PostConstruct
	private void init() {
		this.hashOperations = this.redisTemplate.opsForHash();
	}
	/**
	 * Save a new User's Session
	 * @param oUser
	 * @return An encoded ID
	 */
	public void save(User oUser, String strEncodedID) {
		// Save the user with this encoded id
		this.hashOperations.put(DAOAuth.KEY, strEncodedID, oUser);
	}
	/**
	 * Find the User inside the Redis's DB
	 * with the encoded ID
	 * @param strEncodedID
	 * @return
	 */
	public User find(String strEncodedID) {
		return this.hashOperations.get(KEY, strEncodedID);
	}
	/**
	 *	Find entries inside the Redis's DB
	 * @return All entries with its encodedID and User's Object
	 */
	public Map<String, User> findAll() {
		return this.hashOperations.entries(KEY);
	}
	/**
	 * Update a user with the encoded ID
	 * @param oUser
	 * @param strEncodedID
	 */
	public void update(User oUser, String strEncodedID) {
		this.hashOperations.put(KEY, strEncodedID, oUser);
	}
	/**
	 * Delete a user from the Redis's DB
	 * @param strEncodedID
	 */
	public void delete(String strEncodedID) {
		this.hashOperations.delete(KEY, strEncodedID);
	}
}
