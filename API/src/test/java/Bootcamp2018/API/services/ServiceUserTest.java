package Bootcamp2018.API.services;

import Bootcamp2018.API.entities.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import Bootcamp2018.API.daos.iDAOUser;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.powermock.api.mockito.PowerMockito.doReturn;
import static org.powermock.api.mockito.PowerMockito.verifyPrivate;

public class ServiceUserTest {

	@InjectMocks
	private ServiceUser serviceUser;
	/**
	 *  It creates a Mock for the DAOAuth which can
	 *  be used to bypass the call to the actual DAOAuth:
	 */
	@Mock
	private iDAOUser iDAOUser;
	// The user to test
	private User oUser;
	/**
	 * Initialize the Test
	 */
	@Before
	public void init() {
		// New User
		this.oUser = new User();
		// Set some data
		// Instance the User
		this.oUser = new User();
		// Set some example data
		this.oUser.setFirstName("Jorge");
		this.oUser.setLastName("Roberto");
		this.oUser.setEmail("dev@gmail.com");
		this.oUser.setNickname("jorgito");
		this.oUser.setPassword("1234");
		//  These instances would be created at the start of every test method of this test class.
		MockitoAnnotations.initMocks(this);
	}
	/**
	 * Test when we want to save a valid user
	 */
	@Test
	public void givenAnUserWhenISaveItThenReturnItWithItsID(){
		// Make a custom answer
		doAnswer(new Answer<User>() {
			@Override
			public User answer(InvocationOnMock invocationOnMock) throws Throwable {
				// Get the args of the method
				Object[] args = invocationOnMock.getArguments();
				// Get the saved user
				User oUserSaved = (User) args[0];
				// Set the new ID
				oUserSaved.setId(new Long(1234));
				// Return the user
				return oUserSaved;
			}
		}).when(this.iDAOUser).save(any(User.class));
		// Add the user
		User oUser = this.serviceUser.add(this.oUser);
		// Check if the value is correct
		Assert.assertEquals(new Long(1234), oUser.getId());
	}
	/**
	 * Test when we want to save an invalid user
	 */
	@Test
	public void givenAnInvalidUserWhenISaveItThenReturnException(){
		// Try to add a null user
		User oUser = this.serviceUser.add(null);
		// Check the result
		Assert.assertEquals(null, oUser);
	}
	/**
	 * When a I want to update an existed user
	 */
	@Test
	public void givenAValidUserWhenUpdateItReturnIt(){
		// Make a custom answer
		doAnswer(new Answer<User>() {
			@Override
			public User answer(InvocationOnMock invocationOnMock) throws Throwable {
				// Get the args of the method
				Object[] args = invocationOnMock.getArguments();
				// Get the saved user
				User oUserSaved = (User) args[0];
				// Set the new ID
				oUserSaved.setId(new Long(1234));
				// Return the user
				return oUserSaved;
			}
		}).when(this.iDAOUser).save(any(User.class));
		// When check if the user exists return true
		when(this.iDAOUser.existsById(new Long(1234))).thenReturn(true);
		// Set an ID to the User
		this.oUser.setId(new Long(1234));
		// Add the user
		User oUser = this.serviceUser.update(this.oUser);
		// Check if the value is correct
		Assert.assertEquals(new Long(1234), oUser.getId());
	}
	/**
	 * Test when we want to update an invalid user
	 */
	@Test(expected = Exception.class)
	public void givenAnInvalidUserWhenIUpdateItThenException(){
		// Make an exception when call this method
		doThrow(Exception.class).when(this.iDAOUser).save(null);
		// Try to update a null user
		User oUser = this.serviceUser.update(null);
	}
	/**
	 * When a I want to delete an existed user
	 */
	@Test
	public void givenAValidUserWhenDelete(){
		// Do nothing when call this method
		doNothing().when(this.iDAOUser).delete(any(User.class));
		// When we want find again the user
		when(this.iDAOUser.findById(new Long(1234))).thenReturn(Optional.of(new User()));
		// When check if the user exists return true
		when(this.iDAOUser.existsById(new Long(1234))).thenReturn(true);
		// Set an ID to the User
		this.oUser.setId(new Long(1234));
		// Remove the user
		this.serviceUser.remove(this.oUser);
		// Check if the value is correct
		Assert.assertEquals( null, this.serviceUser.get(new Long(1234)).get().getId());
	}
	/**
	 * Test when we want to delete an invalid user
	 */
	@Test(expected = Exception.class)
	public void givenAnInvalidUserWhenIDeleteItThenException(){
		// Make an exception when call this method
		doThrow(Exception.class).when(this.iDAOUser).delete(null);
		// Try to update a null user
		this.serviceUser.remove(null);
	}
	/**
	 * Check if we can find the user by the first name
	 */
	@Test
	public void whenValidFirstNameThenReturnTheUser(){
		// Create a final user to set to the ArrayList
		final User oUserToReturn = this.oUser;
		// When the method call to the Find Method of the DAO we return the test users
		when(this.iDAOUser.findByFirstName("Jorge")).thenReturn(new ArrayList<User>() {{
			add(oUserToReturn);
		}});
		// Found user
		List<User> oFound = this.serviceUser.getByFirstName("Jorge");
		// Check if is all OK
		Assert.assertEquals("dev@gmail.com", oFound.get(0).getEmail());
	}
	/**
	 * Check if we can handle when the put an invalid first name of the user
	 */
	@Test
	public void whenInvalidFirstNameThenReturnTheUser(){
		// When the method call to the Find Method of the DAO we return the test users
		when(this.iDAOUser.findByFirstName("Jorge4")).thenReturn(new ArrayList<User>());
		// Found user
		List<User> oFound = this.serviceUser.getByFirstName("Jorge4");
		// Check if is all OK
		Assert.assertEquals(0, oFound.size());
	}
	/**
	 * Check if we can find the user by the nickname
	 */
	@Test
	public void whenValidNicknameThenReturnTheUser(){
		// Create a final user to set to the ArrayList
		final User oUserToReturn = this.oUser;
		// When the method call to the Find Method of the DAO we return the test users
		when(this.iDAOUser.findByNickname("jorgito")).thenReturn(this.oUser);
		// Found user
		User oFound = this.serviceUser.getByNickname("jorgito");
		// Check if is all OK
		Assert.assertEquals("dev@gmail.com", oFound.getEmail());
	}
	/**
	 * Check if we can't find the user if has an incorrect nickname
	 */
	@Test
	public void whenInvalidNicknameThenReturnTheUser(){
		// Create a final user to set to the ArrayList
		final User oUserToReturn = this.oUser;
		// When the method call to the Find Method of the DAO we return the test users
		when(this.iDAOUser.findByNickname("jorgito4")).thenReturn(new User());
		// Found user
		User oFound = this.serviceUser.getByNickname("jorgito4");
		// Check if is all OK
		Assert.assertEquals(null, oFound.getId());
	}
	/**
	 * Check if we can find the user by a valid ID
	 */
	@Test
	public void whenValidIDThenReturnTheUser(){
		// Create a optional to get the User
		Optional<User> optUserToReturn = Optional.of(this.oUser);
		// When the method call to the Find Method of the DAO we return the test users
		when(this.iDAOUser.findById(new Long(123))).thenReturn(optUserToReturn);
		// Found user
		Optional<User> oFound = this.serviceUser.get(new Long(123));
		// Check if is all OK
		Assert.assertEquals("jorgito", oFound.get().getNickname());
	}
	/**
	 * Check if we can't avoid the problem when
	 * I want to find someone with an invalid ID
	 */
	@Test
	public void whenInvalidIDThenReturnNull(){
		// Create a optional to get the User
		Optional<User> optUserToReturn = Optional.of(new User());
		// When the method call to the Find Method of the DAO we return the test users
		when(this.iDAOUser.findById(new Long(123))).thenReturn(optUserToReturn);
		// Found user
		Optional<User> oFound = this.serviceUser.get(new Long(123));
		// Check if is all OK
		Assert.assertEquals(null, oFound.get().getId());
	}
	/**
	 * Get all users
	 */
	@Test
	public void whenGetAll(){
		// When the method call to the Find Method of the DAO we return the test users
		when(this.iDAOUser.findAll()).thenReturn(new ArrayList<User>());
		// Found user
		List<User> arrUsers = this.serviceUser.getAll();
		// Check if is all OK
		Assert.assertTrue(arrUsers.size() >= 0);
	}
}
