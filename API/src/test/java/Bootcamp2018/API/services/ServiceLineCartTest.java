package Bootcamp2018.API.services;

import Bootcamp2018.API.daos.iDAOLineCart;
import Bootcamp2018.API.entities.LineCart;
import Bootcamp2018.API.entities.Product;
import Bootcamp2018.API.entities.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

/**
 * Represents the test to the LineCart's Service
 * @author Mozo Nicolas
 * @version 1.0
 * @since 1.0
 */
public class ServiceLineCartTest {
	// The Service
	@InjectMocks
	private ServiceLineCart serviceLineCart;
	/**
	 *  It creates a Mock for the DAOLineCart which can
	 *  be used to bypass the call to the actual DAOLineCart:
	 */
	@Mock
	private iDAOLineCart iDAOLineCart;
	// The LineCart to test
	private LineCart oLineCart;
	// Handle exception for
	@Rule
	public ExpectedException exception = ExpectedException.none();
	/**
	 * Initialize the Test
	 */
	@Before
	public void init() {
		// New LineCart
		this.oLineCart = new LineCart();
		// New LineCart
		Product oProduct= new Product();
		// Set some LineCart's Data
		oProduct.setICant(20);
		oProduct.setPrice(100);
		oProduct.setStrName("Nuevo Producto");
		// New User
		User oUser = new User();
		// Set some example data
		oUser.setFirstName("Jorge");
		oUser.setLastName("Roberto");
		oUser.setEmail("dev@gmail.com");
		oUser.setNickname("jorgito");
		oUser.setPassword("1234");
		// Set some data
		// Set some example data
		this.oLineCart.setICant(20);
		this.oLineCart.setISubtotal(200);
		this.oLineCart.setProduct(oProduct);
		this.oLineCart.setUser(oUser);
		//  These instances would be created at the start of every test method of this test class.
		MockitoAnnotations.initMocks(this);
	}
	/**
	 * Test when we want to save a valid LineCart
	 */
	@Test
	public void givenAnValidLineCartWhenISaveItThenReturnTheSameLineCart(){
		// When insert the line cart return the same
		when(this.iDAOLineCart.save(this.oLineCart)).thenReturn(this.oLineCart);
		// Try to add a null LineCart
		LineCart oLineCart = this.serviceLineCart.add(this.oLineCart);
		// Check the result
		Assert.assertEquals(20, oLineCart.getICant());
	}
	/**
	 * Test when we want to save an invalid LineCart
	 */
	@Test
	public void givenAnInvalidLineCartWhenISaveItThenReturnException(){
		// Try to add a null LineCart
		LineCart oLineCart = this.serviceLineCart.add(null);
		// Check the result
		Assert.assertEquals(null, oLineCart);
	}
	/**
	 * When a I want to update an existed LineCart
	 */
	@Test
	public void givenAValidLineCartWhenUpdateItReturnIt(){
		// Make a custom answer
		doAnswer(new Answer<LineCart>() {
			@Override
			public LineCart answer(InvocationOnMock invocationOnMock) throws Throwable {
				// Get the args of the method
				Object[] args = invocationOnMock.getArguments();
				// Get the saved LineCart
				LineCart oLineCartSaved = (LineCart) args[0];
				// Set the new ID
				oLineCartSaved.setId(new Long(1234));
				// Return the LineCart
				return oLineCartSaved;
			}
		}).when(this.iDAOLineCart).save(any(LineCart.class));
		// When check if the LineCart exists return true
		when(this.iDAOLineCart.existsById(new Long(1234))).thenReturn(true);
		// Set an ID to the LineCart
		this.oLineCart.setId(new Long(1234));
		// Add the LineCart
		LineCart oLineCart = this.serviceLineCart.update(this.oLineCart);
		// Check if the value is correct
		Assert.assertEquals(new Long(1234), oLineCart.getId());
	}
	/**
	 * Test when we want to update an invalid LineCart
	 */
	@Test(expected = IndexOutOfBoundsException.class)
	public void givenAnInvalidLineCartWhenIUpdateItThenException(){
		// When check if the LineCart exists return true
		when(this.iDAOLineCart.existsById(null)).thenReturn(true);
		// Try to update a null LineCart
		LineCart oLineCart = this.serviceLineCart.update(null);
	}

	@Test
	public void givenAnInvalidLineCartWhenIUpdateItThenExceptionUsingRule(){
		// Expect the followed exception
		this.exception.expect(IndexOutOfBoundsException.class);
		// When check if the LineCart exists return true
		when(this.iDAOLineCart.existsById(null)).thenReturn(true);
		// Try to update a null LineCart
		LineCart oLineCart = this.serviceLineCart.update(null);
	}
	/**
	 * When a I want to delete an existed LineCart
	 */
	@Test
	public void givenAValidLineCartWhenDelete(){
		// Do nothing when call this method
		doNothing().when(this.iDAOLineCart).delete(any(LineCart.class));
		// When we want find again the LineCart
		when(this.iDAOLineCart.findById(new Long(1234))).thenReturn(Optional.of(new LineCart()));
		// When check if the LineCart exists return true
		when(this.iDAOLineCart.existsById(new Long(1234))).thenReturn(true);
		// Set an ID to the LineCart
		this.oLineCart.setId(new Long(1234));
		// Remove the LineCart
		this.serviceLineCart.remove(this.oLineCart);
		// Check if the value is correct
		Assert.assertEquals( null, this.serviceLineCart.get(new Long(1234)).get().getId());
	}
	/**
	 * Test when we want to delete an invalid LineCart
	 */
	@Test(expected = IndexOutOfBoundsException.class)
	public void givenAnInvalidLineCartWhenIDeleteItThenException(){
		// When check if the LineCart exists return true
		when(this.iDAOLineCart.existsById(null)).thenReturn(true);
		// Try to update a null LineCart
		this.serviceLineCart.remove(null);
	}
	/**
	 * Test when we want to delete an invalid LineCart
	 */
	@Test
	public void givenAnInvalidLineCartWhenIDeleteItThenExceptionUsingRule(){
		// Expect the followed exception
		this.exception.expect(IndexOutOfBoundsException.class);
		// When check if the LineCart exists return true
		when(this.iDAOLineCart.existsById(null)).thenReturn(true);
		// Try to update a null LineCart
		this.serviceLineCart.remove(null);
	}
	/**
	 * Check if we can find the LineCart by a valid ID
	 */
	@Test
	public void whenValidIDThenReturnTheLineCart(){
		// Create a optional to get the LineCart
		Optional<LineCart> optLineCartToReturn = Optional.of(this.oLineCart);
		// When the method call to the Find Method of the DAO we return the test LineCarts
		when(this.iDAOLineCart.findById(new Long(123))).thenReturn(optLineCartToReturn);
		// Found LineCart
		Optional<LineCart> oFound = this.serviceLineCart.get(new Long(123));
		// Check if is all OK
		Assert.assertEquals("Nuevo Producto", oFound.get().getProduct().getStrName());
	}
	/**
	 * Check if we can't avoid the problem when
	 * I want to find someone with an invalid ID
	 */
	@Test
	public void whenInvalidIDThenReturnTheLineCart(){
		// Create a optional to get the LineCart
		Optional<LineCart> optLineCartToReturn = Optional.of(new LineCart());
		// When the method call to the Find Method of the DAO we return the test LineCarts
		when(this.iDAOLineCart.findById(new Long(123))).thenReturn(optLineCartToReturn);
		// Found LineCart
		Optional<LineCart> oFound = this.serviceLineCart.get(new Long(123));
		// Check if is all OK
		Assert.assertEquals(null, oFound.get().getId());
	}
	/**
	 * Get all LineCarts
	 */
	@Test
	public void whenGetAll(){
		// Make a final variable that contains the line-carts
		final LineCart oFinalLineCart = this.oLineCart;
		// Create a list of line-carts
		List<LineCart> optLineCartToReturn = new ArrayList<LineCart>(){{
			add(oFinalLineCart);
			add(oFinalLineCart);
			add(oFinalLineCart);
			add(oFinalLineCart);
		}};
		// When the method call to the Find Method of the DAO we return the test Products
		when(this.iDAOLineCart.findAll()).thenReturn(optLineCartToReturn);
		// Found Product
		List<LineCart> oFounds = this.serviceLineCart.getAll();
		// Check if is all OK
		Assert.assertEquals(4, oFounds.size());
	}
	/**
	 * Get all line-carts when the list is empty
	 */
	@Test
	public void whenGetAllAndIfEmpty(){
		// Create a list of line-carts
		List<LineCart> optLineCartToReturn = new ArrayList<LineCart>();
		// When the method call to the Find Method of the DAO we return the test Products
		when(this.iDAOLineCart.findAll()).thenReturn(optLineCartToReturn);
		// Found Product
		List<LineCart> oFounds = this.serviceLineCart.getAll();
		// Check if is all OK
		Assert.assertEquals(0, oFounds.size());
	}
}
