package Bootcamp2018.API.controllers;

import Bootcamp2018.API.entities.User;
import Bootcamp2018.API.services.ServiceAuth;
import Bootcamp2018.API.services.ServiceUser;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.result.StatusResultMatchers;

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
	// The User converted to JSON
	private String strUserJSON;
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
		// Get the User converted to JSON
		this.strUserJSON = this.createUserJSON(this.oUser);
	}
	/**
	 * Test the login with correct data
	 */
	@Test
	public void whenLoginReturnAGoodResponse() throws Exception {
		// When access to this method I will return the current tested user
		Mockito.when(this.serviceUser.getByNickname(ArgumentMatchers.any(String.class))).thenReturn(this.oUser);
		// When I save the User I will return "1"
		Mockito.when(this.serviceAuth.save(this.oUser)).thenReturn("1");
		// Create the mock to the request
		MockHttpServletRequestBuilder mockHttpRequestBuilder = MockMvcRequestBuilders.post("/login")
																					 .contentType(MediaType.APPLICATION_JSON)
																					 .content(this.strUserJSON);
		// Check the result
		ResultActions oResultActions = this.mockMvc.perform(mockHttpRequestBuilder)
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(MockMvcResultMatchers.cookie().value("strEncodedID", "1"));
	}
	/**
	 * Test the login with incorrect data
	 */
	@Test
	public void givenIncorrectDataWhenLoginReturnABadResponse() throws Exception {
		// Create a fake User
		User oFakeUser = new User();
		// Set some fake data
		oFakeUser.setPassword("123452323423");
		// When access to this method I will return the current tested user
		Mockito.when(this.serviceUser.getByNickname(ArgumentMatchers.any(String.class))).thenReturn(oFakeUser);
		// When I save the User I will return "1"
		Mockito.when(this.serviceAuth.save(new User())).thenReturn(null);
		// Create the mock to the request
		MockHttpServletRequestBuilder mockHttpRequestBuilder = MockMvcRequestBuilders.post("/login")
				.contentType(MediaType.APPLICATION_JSON)
				.content(this.createUserJSON(new User()));
		// Check the result
		ResultActions oResultActions = this.mockMvc.perform(mockHttpRequestBuilder)
				.andExpect(MockMvcResultMatchers.status().isBadRequest())
				.andExpect(MockMvcResultMatchers.cookie().doesNotExist("strEncodedID"));
	}
	/**
	 * Test the register with correct data
	 */
	@Test
	public void whenRegisterReturnAGoodResponse() throws Exception {
		// When access to this method I will return the current tested user
		Mockito.when(this.serviceUser.add(ArgumentMatchers.any(User.class))).thenReturn(this.oUser);
		// Create the mock to the request
		MockHttpServletRequestBuilder mockHttpRequestBuilder = MockMvcRequestBuilders.post("/register")
				.contentType(MediaType.APPLICATION_JSON)
				.content(this.strUserJSON);
		// Check the result
		ResultActions oResultActions = this.mockMvc.perform(mockHttpRequestBuilder)
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8));
	}
	/**
	 * Test the register with incorrect data
	 */
	@Test
	public void givenRegisterDataWhenLoginReturnABadResponse() throws Exception {
		// When access to this method I will return the current tested user
		Mockito.when(this.serviceUser.add(ArgumentMatchers.any(User.class))).thenReturn(null);
		// Create the mock to the request
		MockHttpServletRequestBuilder mockHttpRequestBuilder = MockMvcRequestBuilders.post("/register")
				.contentType(MediaType.APPLICATION_JSON)
				.content(this.createUserJSON(new User()));
		// Check the result
		ResultActions oResultActions = this.mockMvc.perform(mockHttpRequestBuilder)
				.andExpect(MockMvcResultMatchers.status().isBadRequest());
	}
	/**
	 * Create the JSON to pass to the request
	 * @param oUser
	 * @return
	 */
	private String createUserJSON(User oUser){
		return "{ \"nickname\": \"" + oUser.getNickname() + "\", " +
				"\"password\":\"" + oUser.getPassword() + "\"," +
				"\"firstName\":\"" + oUser.getFirstName() + "\"," +
				"\"lastName\":\"" + oUser.getLastName() + "\"," +
				"\"email\":\"" + oUser.getEmail() + "\"}";
	}
}
