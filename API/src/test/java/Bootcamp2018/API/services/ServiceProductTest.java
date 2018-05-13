package Bootcamp2018.API.services;

import Bootcamp2018.API.daos.iDAOProduct;
import Bootcamp2018.API.entities.Product;
import Bootcamp2018.API.entities.Product;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
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
 * Represents the test to the Product's Service
 * @author Mozo Nicolas
 * @version 1.0
 * @since 1.0
 */
public class ServiceProductTest {
	// The Service
	@InjectMocks
	private ServiceProduct serviceProduct;
	/**
	 *  It creates a Mock for the DAOProduct which can
	 *  be used to bypass the call to the actual DAOProduct:
	 */
	@Mock
	private iDAOProduct iDAOProduct;
	// The Product to test
	private Product oProduct;
	/**
	 * Initialize the Test
	 */
	@Before
	public void init() {
		// New Product
		this.oProduct = new Product();
		// Set some data
		// Set some example data
		this.oProduct.setICant(20);
		this.oProduct.setPrice(100);
		this.oProduct.setStrName("Nuevo Producto");
		//  These instances would be created at the start of every test method of this test class.
		MockitoAnnotations.initMocks(this);
	}
	/**
	 * Test when we want to save a valid Product
	 */
	@Test
	public void givenAnProductWhenISaveItThenReturnItWithItsID(){
		// Make a custom answer
		doAnswer(new Answer<Product>() {
			@Override
			public Product answer(InvocationOnMock invocationOnMock) throws Throwable {
				// Get the args of the method
				Object[] args = invocationOnMock.getArguments();
				// Get the saved Product
				Product oProductSaved = (Product) args[0];
				// Set the new ID
				oProductSaved.setId(new Long(1234));
				// Return the Product
				return oProductSaved;
			}
		}).when(this.iDAOProduct).save(any(Product.class));
		// Add the Product
		Product oProduct = this.serviceProduct.add(this.oProduct);
		// Check if the value is correct
		Assert.assertEquals(new Long(1234), oProduct.getId());
	}
	/**
	 * Test when we want to save an invalid Product
	 */
	@Test
	public void givenAnInvalidProductWhenISaveItThenReturnException(){
		// Try to add a null Product
		Product oProduct = this.serviceProduct.add(null);
		// Check the result
		Assert.assertEquals(null, oProduct);
	}
	/**
	 * When a I want to update an existed Product
	 */
	@Test(expected = IndexOutOfBoundsException.class)
	public void givenAValidProductWhenUpdateItReturnIt(){
		// Make a custom answer
		doAnswer(new Answer<Product>() {
			@Override
			public Product answer(InvocationOnMock invocationOnMock) throws Throwable {
				// Get the args of the method
				Object[] args = invocationOnMock.getArguments();
				// Get the saved Product
				Product oProductSaved = (Product) args[0];
				// Set the new ID
				oProductSaved.setId(new Long(1234));
				// Return the Product
				return oProductSaved;
			}
		}).when(this.iDAOProduct).save(any(Product.class));
		// When check if the Product exists return true
		when(this.iDAOProduct.existsById(new Long(1234))).thenReturn(true);
		// Set an ID to the Product
		this.oProduct.setId(new Long(1234));
		// Add the Product
		Product oProduct = this.serviceProduct.update(this.oProduct);
		// Check if the value is correct
		Assert.assertEquals(new Long(1234), oProduct.getId());
	}
	/**
	 * Test when we want to update an invalid Product
	 */
	@Test(expected = IndexOutOfBoundsException.class)
	public void givenAnInvalidProductWhenIUpdateItThenException(){
		// When check if the Product exists return true
		when(this.iDAOProduct.existsById(null)).thenReturn(true);
		// Make an exception when call this method
		doThrow(IndexOutOfBoundsException.class).when(this.iDAOProduct).save(any(Product.class));
		// Try to update a null Product
		Product oProduct = this.serviceProduct.update(new Product());
	}
	/**
	 * When a I want to delete an existed Product
	 */
	@Test
	public void givenAValidProductWhenDelete(){
		// Do nothing when call this method
		doNothing().when(this.iDAOProduct).delete(any(Product.class));
		// When we want find again the Product
		when(this.iDAOProduct.findById(new Long(1234))).thenReturn(Optional.of(new Product()));
		// When check if the Product exists return true
		when(this.iDAOProduct.existsById(new Long(1234))).thenReturn(true);
		// Set an ID to the Product
		this.oProduct.setId(new Long(1234));
		// Remove the Product
		this.serviceProduct.remove(this.oProduct);
		// Check if the value is correct
		Assert.assertEquals( null, this.serviceProduct.get(new Long(1234)).get().getId());
	}
	/**
	 * Test when we want to delete an invalid Product
	 */
	@Test(expected = IndexOutOfBoundsException.class)
	public void givenAnInvalidProductWhenIDeleteItThenException(){
		// When check if the Product exists return true
		when(this.iDAOProduct.existsById(null)).thenReturn(true);
		// Make an exception when call this method
		doThrow(IndexOutOfBoundsException.class).when(this.iDAOProduct).delete(any(Product.class));
		// Try to update a null Product
		this.serviceProduct.remove(new Product());
	}
	/**
	 * Check if we can find the Product by a valid ID
	 */
	@Test
	public void whenValidIDThenReturnTheProduct(){
		// Create a optional to get the Product
		Optional<Product> optProductToReturn = Optional.of(this.oProduct);
		// When the method call to the Find Method of the DAO we return the test Products
		when(this.iDAOProduct.findById(new Long(123))).thenReturn(optProductToReturn);
		// Found Product
		Optional<Product> oFound = this.serviceProduct.get(new Long(123));
		// Check if is all OK
		Assert.assertEquals("Nuevo Producto", oFound.get().getStrName());
	}
	/**
	 * Check if we can't avoid the problem when
	 * I want to find someone with an invalid ID
	 */
	@Test
	public void whenInvalidIDThenReturnTheProduct(){
		// Create a optional to get the Product
		Optional<Product> optProductToReturn = Optional.of(new Product());
		// When the method call to the Find Method of the DAO we return the test Products
		when(this.iDAOProduct.findById(new Long(123))).thenReturn(optProductToReturn);
		// Found Product
		Optional<Product> oFound = this.serviceProduct.get(new Long(123));
		// Check if is all OK
		Assert.assertEquals(null, oFound.get().getId());
	}
	/**
	 * Get all products
	 */
	@Test
	public void whenGetAll(){
		// Make a final variable that contains the product
		final Product oFinalProduct = this.oProduct;
		// Create a list of products
		List<Product> optProductToReturn = new ArrayList<Product>(){{
			add(oFinalProduct);
			add(oFinalProduct);
			add(oFinalProduct);
			add(oFinalProduct);
		}};
		// When the method call to the Find Method of the DAO we return the test Products
		when(this.iDAOProduct.findAll()).thenReturn(optProductToReturn);
		// Found Product
		List<Product> oFounds = this.serviceProduct.getAll();
		// Check if is all OK
		Assert.assertEquals(4, oFounds.size());
	}
	/**
	 * Get all products when the list is empty
	 */
	@Test
	public void whenGetAllAndIfEmpty(){
		// Create a list of products
		List<Product> optProductToReturn = new ArrayList<Product>();
		// When the method call to the Find Method of the DAO we return the test Products
		when(this.iDAOProduct.findAll()).thenReturn(optProductToReturn);
		// Found Product
		List<Product> oFounds = this.serviceProduct.getAll();
		// Check if is all OK
		Assert.assertEquals(0, oFounds.size());
	}
}
