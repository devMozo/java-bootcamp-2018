package Bootcamp2018.API.daos;

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
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import java.util.List;

/**
 * Represents the class to test the Product's Repository
 * @author Mozo Nicolas
 * @version 1.0
 * @since 1.0
 */
// Is used to provide a bridge between Spring Boot test features and JUnit.
// Provides some standard setup needed for testing the persistence layer

@DataJpaTest
// When we use a database that isn't a in-memory's database we need to put this
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@RunWith(SpringRunner.class)
public class iDAOUserTest {
	// The repository to test
	@Autowired
	private iDAOUser iDAOUserTest;
	// The TestEntityManager provided by Spring Boot is an alternative
	// to the standard JPA EntityManager that provides methods
	// commonly used when writing tests.
	@Autowired
	private TestEntityManager testEntityManager;
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
	}
	/**
	 * When we find by name
	 */
	@Test
	public void givenANewUserWhenFindByFirstNameThenReturnTheUser(){
		// Make an instance managed and persistent.
		this.testEntityManager.persist(this.oUserToTest);
		// Synchronize the persistence context to the underlying database.
		this.testEntityManager.flush();
		// Get the user
		List<User> oUserFound = this.iDAOUserTest.findByFirstName("Jorge");
		// Check if the result is correct
		Assert.assertTrue(oUserFound.contains(this.oUserToTest));
	}
	/**
	 * When we find by nickname
	 */
	@Test
	public void givenANewUserWhenFindByNicknameThenReturnUser(){
		// Make an instance managed and persistent.
		this.testEntityManager.persist(this.oUserToTest);
		// Synchronize the persistence context to the underlying database.
		this.testEntityManager.flush();
		// Get the user
		User oUserFound = this.iDAOUserTest.findByNickname("jorgito");
		// Check if the result is correct
		Assert.assertNotEquals(null, oUserFound);
	}
}