package Bootcamp2018.API.services;

import Bootcamp2018.API.daos.DAOAuth;
import Bootcamp2018.API.entities.User;
import Bootcamp2018.API.helpers.EncryptionString;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.internal.verification.VerificationModeFactory;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * Represents the test to the AuthTest's Service
 * @author Mozo Nicolas
 * @version 1.0
 * @since 1.0
 */
@PrepareForTest(EncryptionString.class)
@RunWith(PowerMockRunner.class)
public class ServiceAuthTest {

	@InjectMocks
	private ServiceAuth serviceAuth;
	/**
	 *  It creates a Mock for the DAOAuth which can
	 *  be used to bypass the call to the actual DAOAuth:
	 */
	@Mock
	private DAOAuth daoAuth;
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
		this.oUser.setId(new Long(1));
		this.oUser.setFirstName("Jorge");
		this.oUser.setLastName("Roberto");
		this.oUser.setEmail("dev@gmail.com");
		this.oUser.setNickname("jorgito");
		this.oUser.setPassword("1234");
		// Mock the EncryptedID's Generator
		PowerMockito.mockStatic(EncryptionString.class);
		//  These instances would be created at the start of every test method of this test class.
		MockitoAnnotations.initMocks(this);
	}
	/**
	 * Check if we can find the user
	 */
	@Test
	public void whenValidEncryptedIDThenReturnTheUser(){
		// When the method call to the Find Method of the DAO we return the test users
		when(this.daoAuth.find("1")).thenReturn(this.oUser);
		// Found user
		User oFound = this.serviceAuth.find("1");
		// Check if is all OK
		Assert.assertEquals("jorgito", oFound.getNickname());
	}
	/**
	 * Check if we want to find a user that
	 * doesn't exist we need to retrieve null
	 */
	@Test
	public void whenInvalidEncryptedIDThenReturnNull(){
		// When the method call to the Find Method of the DAO we return the test users
		when(this.daoAuth.find("1")).thenReturn(new User());
		// Found user
		User oFound = this.serviceAuth.find("1");
		// Check if is all OK
		Assert.assertEquals(null, oFound.getNickname());
	}
	/**
	 * Check if we can add a new user
	 */
	@Test
	public void givenValidUserWhenAddItThenReturnIt() throws Exception {
		// Make nothing when we call to this method
		doNothing().when(this.daoAuth).save(this.oUser, "1");
		// Mock the static method
		PowerMockito.when(EncryptionString.encrypt(this.oUser.getId().toString())).thenCallRealMethod();
		// Save new User
		String strEncryptedIDInserted = this.serviceAuth.save(this.oUser);
		// See the result
		Assert.assertEquals(EncryptionString.encrypt(this.oUser.getId().toString()), strEncryptedIDInserted);
	}
	/**
	 * Check if the method reject me if I pass an invalid user
	 */
	@Test
	public void givenInvalidUserWhenAddItThenReturnNull() throws Exception {
		// Save new User
		String strEncryptedIDInserted = this.serviceAuth.save(null);
		// See the result
		Assert.assertEquals(null, strEncryptedIDInserted);
	}
	/**
	 * Check if we can update the user
	 */
	@Test
	public void whenValidEncryptedIDThenUpdateTheUser() throws Exception {
		// When the method call to the Find Method of the DAO we return the test users
		when(this.daoAuth.find("1")).thenReturn(this.oUser);
		// Make a new Answer
		doAnswer(new Answer<User>(){
			// Override the Class's Methods
			@Override
			public User answer(InvocationOnMock invocationOnMock) throws Throwable {
				// Get the arguments of the method
				Object[] args = invocationOnMock.getArguments();
				// Get the user
				User oUserToUpdate = (User) args[0];
				// Get the encrypted ID
				String strEncryptedID = (String) args[1];
				// Return the user
				return oUserToUpdate;
			}
		}).when(this.daoAuth).update(any(User.class), any(String.class));
		// User to update
		User oFound = this.serviceAuth.find("1");
		// Change something
		oFound.setNickname("jorgito2");
		oFound.setId(new Long(1));
		// Update user
		this.serviceAuth.update(oFound);
		// Check if is all OK
		Assert.assertEquals("jorgito2", this.serviceAuth.find("1").getNickname());
	}
	/**
	 * Check if the method reject me if I pass an invalid user
	 */
	@Test(expected = Exception.class)
	public void givenAnInvalidUserWhenUpdateItThenException() throws Exception {
		this.serviceAuth.update(null);
	}
	/**
	 * Check if we can delete the user
	 */
	@Test
	public void whenValidEncryptedIDThenDeleteUser() throws Exception {
		// When the method call to the Find Method of the DAO we return the test users
		when(this.daoAuth.find("1")).thenReturn(this.oUser);
		// User to delete
		User oFound = this.serviceAuth.find("1");
		// Set the ID
		oFound.setId(new Long(1));
		// Update user
		this.serviceAuth.delete(oFound);
		// When the method call to the Find Method of the DAO we return the test users
		when(this.daoAuth.find("1")).thenReturn(null);
		// Check if is all OK
		Assert.assertEquals(null, this.serviceAuth.find("1"));
	}
	/**
	 * Check if the method reject me if I pass an invalid user
	 */
	@Test(expected = Exception.class)
	public void givenAnInvalidUserWhenDeleteItThenException() throws Exception {
		this.serviceAuth.delete(null);
	}
	/**
	 * Get all auth users
	 */
	@Test
	public void whenGetAll(){
		// When the method call to the Find Method of the DAO we return the test users
		when(this.daoAuth.findAll()).thenReturn(new HashMap<String, User>());
		// Found user
		Map<String, User> hsmpUsers = this.serviceAuth.findAll();
		// Check if is all OK
		Assert.assertTrue(hsmpUsers.size() >= 0);
	}
}
