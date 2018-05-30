package Bootcamp2018.API.services;

import Bootcamp2018.API.daos.iDAOCategory;
import Bootcamp2018.API.entities.Category;
import Bootcamp2018.API.entities.Product;
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
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

/**
 * Represents the test to the Category's Service
 * @author Mozo Nicolas
 * @version 1.0
 * @since 1.0
 */
public class ServiceCategoryTest {
	// The Service
	@InjectMocks
	private ServiceCategory serviceCategory;
	/**
	 *  It creates a Mock for the DAOCategory which can
	 *  be used to bypass the call to the actual DAOCategory:
	 */
	@Mock
	private Bootcamp2018.API.daos.iDAOCategory iDAOCategory;
	// The Category to test
	private Category oCategory;
	// Handle exception for
	@Rule
	public ExpectedException exception = ExpectedException.none();
	/**
	 * Initialize the Test
	 */
	@Before
	public void init() {
		// New Category
		this.oCategory = new Category();

		// New Product
		Product oProduct = new Product();
		// Set the main data
		oProduct.setId(new Long(1));
		oProduct.setStrName("Nuevo Producto");
		oProduct.setPrice(20);
		oProduct.setICant(100);
		oProduct.setCategory(new Category());

		List<Product> listProducts = new ArrayList<>();
		listProducts.add(oProduct);
		// Set some data
		// Set some example data
		this.oCategory.setName("Something");
		this.oCategory.setId(new Long(1234));
		this.oCategory.setArrProducts(listProducts);
		//  These instances would be created at the start of every test method of this test class.
		MockitoAnnotations.initMocks(this);
	}
	/**
	 * Test when we want to save a valid Category
	 */
	@Test
	public void givenAnCategoryWhenISaveItThenReturnItWithItsID(){
		// Make a custom answer
		doAnswer(new Answer<Category>() {
			@Override
			public Category answer(InvocationOnMock invocationOnMock) throws Throwable {
				// Get the args of the method
				Object[] args = invocationOnMock.getArguments();
				// Get the saved Category
				Category oCategorySaved = (Category) args[0];
				// Set the new ID
				oCategorySaved.setId(new Long(1234));
				// Return the Category
				return oCategorySaved;
			}
		}).when(this.iDAOCategory).save(any(Category.class));
		// Add the Category
		Category oCategory = this.serviceCategory.add(this.oCategory);
		// Check if the value is correct
		Assert.assertEquals(new Long(1234), oCategory.getId());
	}
	/**
	 * Test when we want to save an invalid Category
	 */
	@Test
	public void givenAnInvalidCategoryWhenISaveItThenReturnException(){
		// Try to add a null Category
		Category oCategory = this.serviceCategory.add(null);
		// Check the result
		Assert.assertEquals(null, oCategory);
	}
	/**
	 * When a I want to update an existed Category
	 */
	@Test
	public void givenAValidCategoryWhenUpdateItReturnIt(){
		// Make a custom answer
		doAnswer(new Answer<Category>() {
			@Override
			public Category answer(InvocationOnMock invocationOnMock) throws Throwable {
				// Get the args of the method
				Object[] args = invocationOnMock.getArguments();
				// Get the saved Category
				Category oCategorySaved = (Category) args[0];
				// Set the new ID
				oCategorySaved.setId(new Long(1234));
				// Return the Category
				return oCategorySaved;
			}
		}).when(this.iDAOCategory).save(any(Category.class));
		// When check if the Category exists return true
		when(this.iDAOCategory.existsById(new Long(1234))).thenReturn(true);
		// Set an ID to the Category
		this.oCategory.setId(new Long(1234));
		// Add the Category
		Category oCategory = this.serviceCategory.update(this.oCategory);
		// Check if the value is correct
		Assert.assertEquals(new Long(1234), oCategory.getId());
	}
	/**
	 * Test when we want to update an invalid Category
	 */
	@Test(expected = IndexOutOfBoundsException.class)
	public void givenAnInvalidCategoryWhenIUpdateItThenException(){
		// When check if the Category exists return true
		when(this.iDAOCategory.existsById(null)).thenReturn(true);
		// Try to update a null Category
		Category oCategory = this.serviceCategory.update(null);
	}
	/**
	 * Test when we want to update an invalid Category usign Rule
	 */
	@Test
	public void givenAnInvalidCategoryWhenIUpdateItThenExceptionUsingRule(){
		// Expect the following exception
		this.exception.expect(IndexOutOfBoundsException.class);
		// When check if the Category exists return true
		when(this.iDAOCategory.existsById(null)).thenReturn(true);
		// Try to update a null Category
		Category oCategory = this.serviceCategory.update(null);
	}
	/**
	 * When a I want to delete an existed Category
	 */
	@Test
	public void givenAValidCategoryWhenDelete(){
		// Do nothing when call this method
		doNothing().when(this.iDAOCategory).delete(any(Category.class));
		// When we want find again the Category
		when(this.iDAOCategory.findById(new Long(1234))).thenReturn(Optional.of(new Category()));
		// When check if the Category exists return true
		when(this.iDAOCategory.existsById(new Long(1234))).thenReturn(true);
		// Set an ID to the Category
		this.oCategory.setId(new Long(1234));
		// Remove the Category
		this.serviceCategory.remove(this.oCategory);
		// Check if the value is correct
		Assert.assertEquals( null, this.serviceCategory.get(new Long(1234)).get().getId());
	}
	/**
	 * Test when we want to delete an invalid Category
	 */
	@Test(expected = IndexOutOfBoundsException.class)
	public void givenAnInvalidCategoryWhenIDeleteItThenException(){
		// When check if the Category exists return true
		when(this.iDAOCategory.existsById(null)).thenReturn(true);
		// Try to update a null Category
		this.serviceCategory.remove(null);
	}
	/**
	 * Test when we want to delete an invalid Category
	 */
	@Test
	public void givenAnInvalidCategoryWhenIDeleteItThenExceptionUsingRule(){

		this.exception.expect(IndexOutOfBoundsException.class);
		// When check if the Category exists return true
		when(this.iDAOCategory.existsById(null)).thenReturn(true);
		// Try to update a null Category
		this.serviceCategory.remove(null);
	}
	/**
	 * Check if we can find the Category by a valid ID
	 */
	@Test
	public void whenValidIDThenReturnTheCategory(){
		// Create a optional to get the Category
		Optional<Category> optCategoryToReturn = Optional.of(this.oCategory);
		// When the method call to the Find Method of the DAO we return the test Categorys
		when(this.iDAOCategory.findById(new Long(123))).thenReturn(optCategoryToReturn);
		// Found Category
		Optional<Category> oFound = this.serviceCategory.get(new Long(123));
		// Check if is all OK
		Assert.assertEquals(this.oCategory.getName(), oFound.get().getName());
	}
	/**
	 * Check if we can't avoid the problem when
	 * I want to find someone with an invalid ID
	 */
	@Test
	public void whenInvalidIDThenReturnTheCategory(){
		// Create a optional to get the Category
		Optional<Category> optCategoryToReturn = Optional.of(new Category());
		// When the method call to the Find Method of the DAO we return the test Categorys
		when(this.iDAOCategory.findById(new Long(123))).thenReturn(optCategoryToReturn);
		// Found Category
		Optional<Category> oFound = this.serviceCategory.get(new Long(123));
		// Check if is all OK
		Assert.assertEquals(null, oFound.get().getId());
	}
	/**
	 * Get all Categorys
	 */
	@Test
	public void whenGetAll(){
		// Make a final variable that contains the Category
		final Category oFinalCategory = this.oCategory;
		// Create a list of Categorys
		List<Category> optCategoryToReturn = new ArrayList<Category>(){{
			add(oFinalCategory);
			add(oFinalCategory);
			add(oFinalCategory);
			add(oFinalCategory);
		}};
		// When the method call to the Find Method of the DAO we return the test Categorys
		when(this.iDAOCategory.findAll()).thenReturn(optCategoryToReturn);
		// Found Category
		List<Category> oFounds = this.serviceCategory.getAll();
		// Check if is all OK
		Assert.assertEquals(4, oFounds.size());
	}
	/**
	 * Get all Categorys when the list is empty
	 */
	@Test
	public void whenGetAllAndIfEmpty(){
		// Create a list of Categorys
		List<Category> optCategoryToReturn = new ArrayList<Category>();
		// When the method call to the Find Method of the DAO we return the test Categorys
		when(this.iDAOCategory.findAll()).thenReturn(optCategoryToReturn);
		// Found Category
		List<Category> oFounds = this.serviceCategory.getAll();
		// Check if is all OK
		Assert.assertEquals(0, oFounds.size());
	}
}
