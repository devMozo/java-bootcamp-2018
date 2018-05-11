package Bootcamp2018.API.controllers;

import Bootcamp2018.API.entities.User;
import Bootcamp2018.API.services.ServiceAuth;
import Bootcamp2018.API.services.ServiceUser;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

/**
 * Represents the test to the controllers
 * products and the shoppings cart
 * @author Mozo Nicolas
 * @version 1.0
 * @since 1.0
 */
@RunWith(SpringRunner.class)
//To test the Controllers, we can use @WebMvcTest.
// It will auto-configure the Spring MVC
// infrastructure for our unit tests.
@WebMvcTest(ControllerAuth.class)
public class ControllerAuthTest {
	// MockMvc which offers a powerful way of
	// easy testing MVC controllers
	// without starting a full HTTP server.
	@Autowired
	private MockMvc mockMvc;
	// The Auth's Service of the Controller
	@MockBean
	private ServiceAuth serviceAuth;
	// The User's Service of the Controller
	@MockBean
	private ServiceUser serviceUser;
	// The user that we are going to use
	private User oUser;
	/**
	 * Before each test
	 */
	@Before
	public void init(){
		// New User
		this.oUser = new User();
		// Set the main data
		this.oUser.setId(new Long(1));
		this.oUser.setNickname("jorgito");
		this.oUser.setEmail("jorgito@hotmail.com");
		this.oUser.setPassword("12345");
		this.oUser.setLastName("Jorge");
		this.oUser.setFirstName("ApellidoJorge");
	}
	/**
	 * Test the login with correct data
	 */
	@Test
	public void whenLoginReturnAGoodResponse(){
	}

}
