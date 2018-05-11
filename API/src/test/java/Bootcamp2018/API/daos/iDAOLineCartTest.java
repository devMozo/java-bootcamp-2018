package Bootcamp2018.API.daos;

import Bootcamp2018.API.entities.LineCart;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Represents the class to test the LineCart's Repository
 * @author Mozo Nicolas
 * @version 1.0
 * @since 1.0
 */
// Is used to provide a bridge between Spring Boot test features and JUnit.
@RunWith(SpringRunner.class)
// When we use a database that isn't a in-memory's database we need to put this
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
// Provides some standard setup needed for testing the persistence layer
@DataJpaTest
public class iDAOLineCartTest {
	// The TestEntityManager provided by Spring Boot is an alternative
	// to the standard JPA EntityManager that provides methods
	// commonly used when writing tests.
	@Autowired
	private TestEntityManager testEntityManager;
	// The repository to test
	@Autowired
	private iDAOLineCart iDAOLineCart;
	/**
	 * We don't have implemented methods than the JPARepository's Methods
	 */
	@Test
	public void makeSomething(){
		Assert.assertTrue(true);
	}
}