package Bootcamp2018.API.daos;

import Bootcamp2018.API.entities.LineCart;
import Bootcamp2018.API.entities.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;

/**
 * Represents the class to test the Auth's Repository
 * @author Mozo Nicolas
 * @version 1.0
 * @since 1.0
 */
// Is used to provide a bridge between Spring Boot test features and JUnit.
@RunWith(SpringRunner.class)
// When we use a database that isn't a in-memory's database we need to put this
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
// Provides some standard setup needed for testing the persistence layer
@SpringBootTest
public class DAOAuthTest {
	// The repository to test
	@Autowired
	private DAOAuth daoAuth;
	// Create a new User
	private User oUserToTest;
	/**
	 * Before each test
	 */
	@Before
	public void init(){
		// Instance the User
		this.oUserToTest = new User();
		// Set some example data
		this.oUserToTest.setFirstName("Jorge");
		this.oUserToTest.setLastName("Roberto");
		this.oUserToTest.setEmail("dev@gmail.com");
		this.oUserToTest.setNickname("jorgito");
		this.oUserToTest.setPassword("1234");
		// Save the user in Redis
		this.daoAuth.save(this.oUserToTest, "1");
	}
	/**
	 *	Check if the user is persisted correctly
	 */
	@Test
	public void givenAUserWhenPersistIt(){
		// Get the User
		User oUserFound = this.daoAuth.find("1");
		// Find that user
		Assert.assertNotEquals(null, oUserFound);
		// Check if the saved user is correct
		Assert.assertEquals("dev@gmail.com", oUserFound.getEmail());
	}
	/**
	 * Check if the user is updated
	 */
	@Test
	public void givenAnExistedUserWhenUpdateIt(){
		// Get the User
		User oUserFound = this.daoAuth.find("1");
		// Change the email
		oUserFound.setEmail("jorge@gmail.com");
		// Update it
		this.daoAuth.update(oUserFound, "1");
		// Find again
		oUserFound = this.daoAuth.find("1");
		// Find that user
		Assert.assertNotEquals(null, oUserFound);
		// Check if the saved user is correct
		Assert.assertEquals("jorge@gmail.com", oUserFound.getEmail());
	}
	/**
	 * Check if the user is deleted
	 */
	@Test
	public void givenAnExistedUserWhenDeleteIt(){
		// Delete the User
		this.daoAuth.delete("1");
		// Find again
		User oUserFound = this.daoAuth.find("1");
		// Find that user
		Assert.assertEquals(null, oUserFound);
	}
	/**
	 * Check when get all the users
	 */
	@Test
	public void whenGetAll(){
		// Get all users
		Map<String, User> listAuthUsers = this.daoAuth.findAll();
		// Find that user
		Assert.assertTrue(listAuthUsers.size() >= 0);
	}
}
