package Bootcamp2018.API.controllers;

import Bootcamp2018.API.entities.User;
import Bootcamp2018.API.services.ServiceAuth;
import Bootcamp2018.API.services.ServiceUser;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;

/**
 * Represents the test to the User's Controller
 * @author Mozo Nicolas
 * @version 1.0
 * @since 1.0
 */
@RunWith(SpringRunner.class)
// To test the Controllers, we can use @WebMvcTest.
// It will auto-configure the Spring MVC
// infrastructure for our unit tests.
@WebMvcTest(ControllerUser.class)
public class ControllerUserTest {
	// MockMvc which offers a powerful way of
	// easy testing MVC controllers
	// without starting a full HTTP server.
	@Autowired
	private MockMvc mockMvc;
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
	 * Test when I want to create a new user with correct data
	 */
	@Test
	public void givenCorrectDataWhenAddItThenReturnIt() throws Exception {
		// When call to this method return the current user
		Mockito.when(this.serviceUser.add(ArgumentMatchers.any(User.class))).thenReturn(this.oUser);
		// Mock the request to this endpoint
		MockHttpServletRequestBuilder mockHttpServletRequestBuilder = MockMvcRequestBuilders.post("/user/action/add")
				.contentType(MediaType.APPLICATION_JSON)
				.content(this.strUserJSON);
		// Check the Result
		ResultActions resultActions = this.mockMvc.perform(mockHttpServletRequestBuilder)
				.andExpect(MockMvcResultMatchers.status().isCreated());
	}
	/**
	 * Test when I want to create a new user with incorrect data
	 */
	@Test
	public void givenIncorrectDataWhenAddItThenReturnIt() throws Exception {
		// When call to this method return the current user
		Mockito.when(this.serviceUser.add(ArgumentMatchers.any(User.class))).thenReturn(null);
		// Mock the request to this endpoint
		MockHttpServletRequestBuilder mockHttpServletRequestBuilder = MockMvcRequestBuilders.post("/user/action/add")
				.contentType(MediaType.APPLICATION_JSON)
				.content(this.strUserJSON);
		// Check the Result
		ResultActions resultActions = this.mockMvc.perform(mockHttpServletRequestBuilder)
				.andExpect(MockMvcResultMatchers.status().isBadRequest());
	}
	/**
	 * Test when I want to update a user with correct data
	 */
	@Test
	public void givenCorrectDataWhenUpdateItThenReturnIt() throws Exception {
		// Create an optional
		Optional<User> optionalUser = Optional.of(this.oUser);
		// When call to this method return the current user
		Mockito.when(this.serviceUser.get(new Long(1))).thenReturn(optionalUser);
		// When call the method to update the user
		Mockito.when(this.serviceUser.update(ArgumentMatchers.any(User.class))).thenReturn(this.oUser);
		// Mock the request to this endpoint
		MockHttpServletRequestBuilder mockHttpServletRequestBuilder = MockMvcRequestBuilders.put("/user/action/update/" + 1)
				.contentType(MediaType.APPLICATION_JSON)
				.content(this.strUserJSON);
		// Check the Result
		ResultActions resultActions = this.mockMvc.perform(mockHttpServletRequestBuilder)
				.andExpect(MockMvcResultMatchers.status().isCreated());
	}
	/**
	 * Test when I want to update a user with incorrect data
	 */
	@Test
	public void givenIncorrectDataWhenUpdateItThenReturnIt() throws Exception {
		// When call to this method return the current user
		Mockito.when(this.serviceUser.get(new Long(1))).thenReturn(Optional.empty());
		// Mock the request to this endpoint
		MockHttpServletRequestBuilder mockHttpServletRequestBuilder = MockMvcRequestBuilders.put("/user/action/update/" + 1)
				.contentType(MediaType.APPLICATION_JSON)
				.content(this.strUserJSON);
		// Check the Result
		ResultActions resultActions = this.mockMvc.perform(mockHttpServletRequestBuilder)
				.andExpect(MockMvcResultMatchers.status().isNotFound());
	}
	/**
	 * Test when I want to delete a user with correct data
	 */
	@Test
	public void givenCorrectDataWhenDeleteItThenReturnIt() throws Exception {
		// Create an optional
		Optional<User> optionalUser = Optional.of(this.oUser);
		// When call to this method return the current user
		Mockito.when(this.serviceUser.get(new Long(1))).thenReturn(optionalUser);
		// When call the method to update the user
		Mockito.doNothing().when(this.serviceUser).remove(ArgumentMatchers.any(User.class));
		// Mock the request to this endpoint
		MockHttpServletRequestBuilder mockHttpServletRequestBuilder = MockMvcRequestBuilders.delete("/user/action/delete/" + 1)
				.contentType(MediaType.APPLICATION_JSON);
		// Check the Result
		ResultActions resultActions = this.mockMvc.perform(mockHttpServletRequestBuilder)
				.andExpect(MockMvcResultMatchers.status().isOk());
	}
	/**
	 * Test when I want to delete a user with incorrect data
	 */
	@Test
	public void givenIncorrectDataWhenDeleteItThenReturnIt() throws Exception {
		// When call to this method return the current user
		Mockito.when(this.serviceUser.get(new Long(1))).thenReturn(Optional.empty());
		// Mock the request to this endpoint
		MockHttpServletRequestBuilder mockHttpServletRequestBuilder = MockMvcRequestBuilders.delete("/user/action/delete/" + 1)
				.contentType(MediaType.APPLICATION_JSON);
		// Check the Result
		ResultActions resultActions = this.mockMvc.perform(mockHttpServletRequestBuilder)
				.andExpect(MockMvcResultMatchers.status().isNotFound());
	}
	/**
	 * Test when I want to get a user with correct data
	 */
	@Test
	public void givenCorrectDataWhenGetItThenReturnIt() throws Exception {
		// Create an optional
		Optional<User> optionalUser = Optional.of(this.oUser);
		// When call to this method return the current user
		Mockito.when(this.serviceUser.get(new Long(1))).thenReturn(optionalUser);
		// When call the method to update the user
		Mockito.doNothing().when(this.serviceUser).remove(ArgumentMatchers.any(User.class));
		// Mock the request to this endpoint
		MockHttpServletRequestBuilder mockHttpServletRequestBuilder = MockMvcRequestBuilders.get("/user/action/get/" + 1)
				.contentType(MediaType.APPLICATION_JSON);
		// Check the Result
		ResultActions resultActions = this.mockMvc.perform(mockHttpServletRequestBuilder)
				.andExpect(MockMvcResultMatchers.status().isOk());
		// New Mapper
		ObjectMapper objectMapper = new ObjectMapper();
		// Get the returned user
		User oGettedUser = objectMapper.readValue(resultActions.andReturn().getResponse().getContentAsString(), User.class);
		// Check equals
		Assert.assertEquals(this.oUser.getId(), oGettedUser.getId());
		Assert.assertEquals(this.oUser.getNickname(), oGettedUser.getNickname());
	}
	/**
	 * Test when I want to get a user with incorrect data
	 */
	@Test
	public void givenIncorrectDataWhenGetItThenReturnIt() throws Exception {
		// Create an optional
		Optional<User> optionalUser = Optional.empty();
		// When call to this method return the current user
		Mockito.when(this.serviceUser.get(new Long(1))).thenReturn(optionalUser);
		// When call the method to update the user
		Mockito.doNothing().when(this.serviceUser).remove(ArgumentMatchers.any(User.class));
		// Mock the request to this endpoint
		MockHttpServletRequestBuilder mockHttpServletRequestBuilder = MockMvcRequestBuilders.get("/user/action/get/" + 1)
				.contentType(MediaType.APPLICATION_JSON);
		// Check the Result
		ResultActions resultActions = this.mockMvc.perform(mockHttpServletRequestBuilder)
				.andExpect(MockMvcResultMatchers.status().isOk());
		// Check equals
		Assert.assertEquals("", resultActions.andReturn().getResponse().getContentAsString());
	}
	/**
	 * Test when I want to get a user by the first name with correct data
	 */
	@Test
	public void givenCorrectDataWhenGetItByFirstNameThenReturnIt() throws Exception {
		// Save the user in a final variable
		final User oUserGettedByFirstName = this.oUser;
		// Create a list
		List<User> listUser = new ArrayList<User>(){{
			add(oUserGettedByFirstName);
		}};
		// When call to this method return the current user
		Mockito.when(this.serviceUser.getByFirstName(ArgumentMatchers.any(String.class))).thenReturn(listUser);
		// Mock the request to this endpoint
		MockHttpServletRequestBuilder mockHttpServletRequestBuilder = MockMvcRequestBuilders.get("/user/action/getByFirstName/Jorge")
				.contentType(MediaType.APPLICATION_JSON);
		// Check the Result
		ResultActions resultActions = this.mockMvc.perform(mockHttpServletRequestBuilder)
				.andExpect(MockMvcResultMatchers.status().isOk());
		// New Mapper
		ObjectMapper objectMapper = new ObjectMapper();
		// Get the returned user
		List<User> oGettedUsers = objectMapper.readValue(resultActions.andReturn().getResponse().getContentAsString(),
				objectMapper.getTypeFactory().constructCollectionType(List.class, User.class));
		// Check equals
		Assert.assertEquals(this.oUser.getId(), oGettedUsers.get(0).getId());
		Assert.assertEquals(this.oUser.getNickname(), oGettedUsers.get(0).getNickname());
	}
	/**
	 * Test when I want to get a user by the first name with incorrect data
	 */
	@Test
	public void givenIncorrectDataWhenGetItByFirstNameThenReturnIt() throws Exception {
		// Create an empty list
		List<User> listUser = new ArrayList<User>();
		// When call to this method return the current user
		Mockito.when(this.serviceUser.getByFirstName(ArgumentMatchers.any(String.class))).thenReturn(listUser);
		// Mock the request to this endpoint
		MockHttpServletRequestBuilder mockHttpServletRequestBuilder = MockMvcRequestBuilders.get("/user/action/getByFirstName/Jorge")
				.contentType(MediaType.APPLICATION_JSON);
		// Check the Result
		ResultActions resultActions = this.mockMvc.perform(mockHttpServletRequestBuilder)
				.andExpect(MockMvcResultMatchers.status().isOk());
		// New Mapper
		ObjectMapper objectMapper = new ObjectMapper();
		// Get the returned user
		List<User> oGettedUsers = objectMapper.readValue(resultActions.andReturn().getResponse().getContentAsString(),
				objectMapper.getTypeFactory().constructCollectionType(List.class, User.class));
		// Check equals
		Assert.assertEquals(0, oGettedUsers.size());
	}
	/**
	 * Test when I want to get a user by the nickname with correct data
	 */
	@Test
	public void givenCorrectDataWhenGetItByNicknameThenReturnIt() throws Exception {
		// When call to this method return the current user
		Mockito.when(this.serviceUser.getByNickname(ArgumentMatchers.any(String.class))).thenReturn(this.oUser);
		// Mock the request to this endpoint
		MockHttpServletRequestBuilder mockHttpServletRequestBuilder = MockMvcRequestBuilders.get("/user/action/getByNickname/Jorge")
				.contentType(MediaType.APPLICATION_JSON);
		// Check the Result
		ResultActions resultActions = this.mockMvc.perform(mockHttpServletRequestBuilder)
				.andExpect(MockMvcResultMatchers.status().isOk());
		// New Mapper
		ObjectMapper objectMapper = new ObjectMapper();
		// Get the returned user
		User oGettedUser = objectMapper.readValue(resultActions.andReturn().getResponse().getContentAsString(), User.class);
		// Check equals
		Assert.assertEquals(this.oUser.getId(), oGettedUser.getId());
		Assert.assertEquals(this.oUser.getNickname(), oGettedUser.getNickname());
	}
	/**
	 * Test when I want to get a user by the nickname with incorrect data
	 */
	@Test
	public void givenIncorrectDataWhenGetItByNicknameThenReturnIt() throws Exception {
		// When call to this method return the current user
		Mockito.when(this.serviceUser.getByNickname(ArgumentMatchers.any(String.class))).thenReturn(new User());
		// Mock the request to this endpoint
		MockHttpServletRequestBuilder mockHttpServletRequestBuilder = MockMvcRequestBuilders.get("/user/action/getByNickname/Jorge")
				.contentType(MediaType.APPLICATION_JSON);
		// Check the Result
		ResultActions resultActions = this.mockMvc.perform(mockHttpServletRequestBuilder)
				.andExpect(MockMvcResultMatchers.status().isOk());
		// New Mapper
		ObjectMapper objectMapper = new ObjectMapper();
		// Get the returned user
		User oGettedUser = objectMapper.readValue(resultActions.andReturn().getResponse().getContentAsString(), User.class);
		// Check equals
		Assert.assertEquals(null, oGettedUser.getId());
	}
	/**
	 * Test when I want to get all users
	 */
	@Test
	public void whenGetAllThenReturnThen() throws Exception {
		// Save the user in a final variable
		final User oUserGettedByFirstName = this.oUser;
		// Create a list
		List<User> listUser = new ArrayList<User>(){{
			add(oUserGettedByFirstName);
			add(oUserGettedByFirstName);
			add(oUserGettedByFirstName);
		}};
		// When call to this method return the current user
		Mockito.when(this.serviceUser.getAll()).thenReturn(listUser);
		// Mock the request to this endpoint
		MockHttpServletRequestBuilder mockHttpServletRequestBuilder = MockMvcRequestBuilders.get("/user/action/getAll")
				.contentType(MediaType.APPLICATION_JSON);
		// Check the Result
		ResultActions resultActions = this.mockMvc.perform(mockHttpServletRequestBuilder)
				.andExpect(MockMvcResultMatchers.status().isOk());
		// New Mapper
		ObjectMapper objectMapper = new ObjectMapper();
		// Get the returned users
		List<User> oGettedUsers = objectMapper.readValue(resultActions.andReturn().getResponse().getContentAsString(),
				objectMapper.getTypeFactory().constructCollectionType(List.class, User.class));
		// Check equals
		Assert.assertNotEquals(null, oGettedUsers);
		Assert.assertNotEquals(0, oGettedUsers.size());
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
