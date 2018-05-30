package Bootcamp2018.API.controllers;

import Bootcamp2018.API.entities.Category;
import Bootcamp2018.API.entities.Product;
import Bootcamp2018.API.entities.Product;
import Bootcamp2018.API.services.ServiceCategory;
import Bootcamp2018.API.services.ServiceProduct;
import Bootcamp2018.API.services.ServiceProduct;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Represents the test to the Product's Controller
 * @author Mozo Nicolas
 * @version 1.0
 * @since 1.0
 */
@RunWith(SpringRunner.class)
// To test the Controllers, we can use @WebMvcTest.
// It will auto-configure the Spring MVC
// infrastructure for our unit tests.
@WebMvcTest(ControllerProduct.class)
public class ControllerProductTest {
	// MockMvc which offers a powerful way of
	// easy testing MVC controllers
	// without starting a full HTTP server.
	@Autowired
	private MockMvc mockMvc;
	// The Product's Service of the Controller
	@MockBean
	private ServiceProduct serviceProduct;
	// The Category's Service of the Controller
	@MockBean
	private ServiceCategory serviceCategory;
	// The Product that we are going to use
	private Product oProduct;
	// The Category that we are going to use
	private Category oCategory;
	// The Product converted to JSON
	private String strProductJSON;
	/**
	 * Before each test
	 */
	@Before
	public void init(){
		// New Product
		this.oProduct = new Product();
		// Set the main data
		this.oProduct.setId(new Long(1));
		this.oProduct.setStrName("Nuevo Producto");
		this.oProduct.setPrice(20);
		this.oProduct.setICant(100);
		this.oProduct.setCategory(new Category());

		List<Product> listProducts = new ArrayList<>();
		listProducts.add(this.oProduct);

		this.oCategory = new Category();
		this.oCategory.setId(new Long(1));
		this.oCategory.setName("Something");
		this.oCategory.setArrProducts(listProducts);
		// Get the Product converted to JSON
		this.strProductJSON = this.createProductJSON(this.oProduct);
	}
	/**
	 * Test when I want to create a new Product with correct data
	 */
	@Test
	public void givenCorrectDataWhenAddItThenReturnIt() throws Exception {
		// When call to this method return the current Product
		Mockito.when(this.serviceProduct.add(ArgumentMatchers.any(Product.class))).thenReturn(this.oProduct);
		// Mock the request to this endpoint
		MockHttpServletRequestBuilder mockHttpServletRequestBuilder = MockMvcRequestBuilders.post("/product/action/add")
				.contentType(MediaType.APPLICATION_JSON)
				.content(this.strProductJSON);
		// Check the Result
		ResultActions resultActions = this.mockMvc.perform(mockHttpServletRequestBuilder)
				.andExpect(MockMvcResultMatchers.status().isCreated());
	}
	/**
	 * Test when I want to create a new Product with incorrect data
	 */
	@Test
	public void givenIncorrectDataWhenAddItThenReturnIt() throws Exception {
		// When call to this method return the current Product
		Mockito.when(this.serviceProduct.add(ArgumentMatchers.any(Product.class))).thenReturn(null);
		// Mock the request to this endpoint
		MockHttpServletRequestBuilder mockHttpServletRequestBuilder = MockMvcRequestBuilders.post("/product/action/add")
				.contentType(MediaType.APPLICATION_JSON)
				.content(this.strProductJSON);
		// Check the Result
		ResultActions resultActions = this.mockMvc.perform(mockHttpServletRequestBuilder)
				.andExpect(MockMvcResultMatchers.status().isBadRequest());
	}
	/**
	 * Test when I want to update a Product with correct data
	 */
	@Test
	public void givenCorrectDataWhenUpdateItThenReturnIt() throws Exception {
		// Create an optional
		Optional<Product> optionalProduct = Optional.of(this.oProduct);
		// When call to this method return the current Product
		Mockito.when(this.serviceProduct.get(new Long(1))).thenReturn(optionalProduct);
		// When call the method to update the Product
		Mockito.when(this.serviceProduct.update(ArgumentMatchers.any(Product.class))).thenReturn(this.oProduct);
		// Mock the request to this endpoint
		MockHttpServletRequestBuilder mockHttpServletRequestBuilder = MockMvcRequestBuilders.put("/product/action/update/" + 1)
				.contentType(MediaType.APPLICATION_JSON)
				.content(this.strProductJSON);
		// Check the Result
		ResultActions resultActions = this.mockMvc.perform(mockHttpServletRequestBuilder)
				.andExpect(MockMvcResultMatchers.status().isCreated());
	}
	/**
	 * Test when I want to update a Product with incorrect data
	 */
	@Test
	public void givenIncorrectDataWhenUpdateItThenReturnIt() throws Exception {
		// When call to this method return the current Product
		Mockito.when(this.serviceProduct.get(new Long(1))).thenReturn(Optional.empty());
		// Mock the request to this endpoint
		MockHttpServletRequestBuilder mockHttpServletRequestBuilder = MockMvcRequestBuilders.put("/product/action/update/" + 1)
				.contentType(MediaType.APPLICATION_JSON)
				.content(this.strProductJSON);
		// Check the Result
		ResultActions resultActions = this.mockMvc.perform(mockHttpServletRequestBuilder)
				.andExpect(MockMvcResultMatchers.status().isNotFound());
	}
	/**
	 * Test when I want to delete a Product with correct data
	 */
	@Test
	public void givenCorrectDataWhenDeleteItThenReturnIt() throws Exception {
		// Create an optional
		Optional<Product> optionalProduct = Optional.of(this.oProduct);
		// When call to this method return the current Product
		Mockito.when(this.serviceProduct.get(new Long(1))).thenReturn(optionalProduct);
		// When call the method to update the Product
		Mockito.doNothing().when(this.serviceProduct).remove(ArgumentMatchers.any(Product.class));
		// Mock the request to this endpoint
		MockHttpServletRequestBuilder mockHttpServletRequestBuilder = MockMvcRequestBuilders.delete("/product/action/delete/" + 1)
				.contentType(MediaType.APPLICATION_JSON);
		// Check the Result
		ResultActions resultActions = this.mockMvc.perform(mockHttpServletRequestBuilder)
				.andExpect(MockMvcResultMatchers.status().isOk());
	}
	/**
	 * Test when I want to delete a Product with incorrect data
	 */
	@Test
	public void givenIncorrectDataWhenDeleteItThenReturnIt() throws Exception {
		// When call to this method return the current Product
		Mockito.when(this.serviceProduct.get(new Long(1))).thenReturn(Optional.empty());
		// Mock the request to this endpoint
		MockHttpServletRequestBuilder mockHttpServletRequestBuilder = MockMvcRequestBuilders.delete("/product/action/delete/" + 1)
				.contentType(MediaType.APPLICATION_JSON);
		// Check the Result
		ResultActions resultActions = this.mockMvc.perform(mockHttpServletRequestBuilder)
				.andExpect(MockMvcResultMatchers.status().isNotFound());
	}
	/**
	 * Test when I want to get a Product with correct data
	 */
	@Test
	public void givenCorrectDataWhenGetItThenReturnIt() throws Exception {
		// Create an optional
		Optional<Product> optionalProduct = Optional.of(this.oProduct);
		// When call to this method return the current Product
		Mockito.when(this.serviceProduct.get(new Long(1))).thenReturn(optionalProduct);
		// When call the method to update the Product
		Mockito.doNothing().when(this.serviceProduct).remove(ArgumentMatchers.any(Product.class));
		// Mock the request to this endpoint
		MockHttpServletRequestBuilder mockHttpServletRequestBuilder = MockMvcRequestBuilders.get("/product/action/get/" + 1)
				.contentType(MediaType.APPLICATION_JSON);
		// Check the Result
		ResultActions resultActions = this.mockMvc.perform(mockHttpServletRequestBuilder)
				.andExpect(MockMvcResultMatchers.status().isOk());
		// New Mapper
		ObjectMapper objectMapper = new ObjectMapper();
		// Get the returned Product
		Product oGettedProduct = objectMapper.readValue(resultActions.andReturn().getResponse().getContentAsString(), Product.class);
		// Check equals
		Assert.assertEquals(this.oProduct.getId(), oGettedProduct.getId());
		Assert.assertEquals(this.oProduct.getStrName(), oGettedProduct.getStrName());
	}
	/**
	 * Test when I want to get a Product with incorrect data
	 */
	@Test
	public void givenIncorrectDataWhenGetItThenReturnIt() throws Exception {
		// Create an optional
		Optional<Product> optionalProduct = Optional.empty();
		// When call to this method return the current Product
		Mockito.when(this.serviceProduct.get(new Long(1))).thenReturn(optionalProduct);
		// When call the method to update the Product
		Mockito.doNothing().when(this.serviceProduct).remove(ArgumentMatchers.any(Product.class));
		// Mock the request to this endpoint
		MockHttpServletRequestBuilder mockHttpServletRequestBuilder = MockMvcRequestBuilders.get("/product/action/get/" + 1)
				.contentType(MediaType.APPLICATION_JSON);
		// Check the Result
		ResultActions resultActions = this.mockMvc.perform(mockHttpServletRequestBuilder)
				.andExpect(MockMvcResultMatchers.status().isOk());
		// Check equals
		Assert.assertEquals("", resultActions.andReturn().getResponse().getContentAsString());
	}
	/**
	 * Test when I want to get a Product with correct data passing the category
	 */
	@Test
	public void givenCorrectDataWhenGetItByCategoryThenReturnIt() throws Exception {
		// When call to this method return the current Product
		Mockito.when(this.serviceCategory.getByName(this.oCategory.getName())).thenReturn(this.oCategory);
		// Mock the request to this endpoint
		MockHttpServletRequestBuilder mockHttpServletRequestBuilder = MockMvcRequestBuilders.get("/product/action/getByCategory/" + this.oCategory.getName())
				.contentType(MediaType.APPLICATION_JSON);
		// Check the Result
		ResultActions resultActions = this.mockMvc.perform(mockHttpServletRequestBuilder)
				.andExpect(MockMvcResultMatchers.status().isOk());
		// New Mapper
		ObjectMapper objectMapper = new ObjectMapper();
		// Get the returned Product
		List<Product> oGettedProducts = objectMapper.readValue(resultActions.andReturn().getResponse().getContentAsString(), List.class);
		// Check equals
		Assert.assertEquals(1, oGettedProducts.size());
	}
	/**
	 * Test when I want to get a Product with incorrect data
	 */
	@Test
	public void givenIncorrectDataWhenGetItByCategoryThenReturnIt() throws Exception {
		// When call to this method return the current Product
		Mockito.when(this.serviceCategory.getByName(this.oCategory.getName())).thenReturn(new Category());
		// Mock the request to this endpoint
		MockHttpServletRequestBuilder mockHttpServletRequestBuilder = MockMvcRequestBuilders.get("/product/action/getByCategory/" + this.oCategory.getName())
				.contentType(MediaType.APPLICATION_JSON);
		// Check the Result
		ResultActions resultActions = this.mockMvc.perform(mockHttpServletRequestBuilder)
				.andExpect(MockMvcResultMatchers.status().isOk());
		// New Mapper
		ObjectMapper objectMapper = new ObjectMapper();
		// Get the returned Product
		List<Product> oGettedProducts = objectMapper.readValue(resultActions.andReturn().getResponse().getContentAsString(), List.class);
		// Check equals
		Assert.assertEquals(0, oGettedProducts.size());
	}
	/**
	 * Test when I want to get all Products
	 */
	@Test
	public void whenGetAllThenReturnThen() throws Exception {
		// Save the Product in a final variable
		final Product oProductGettedByFirstName = this.oProduct;
		// Create a list
		List<Product> listProduct = new ArrayList<Product>(){{
			add(oProductGettedByFirstName);
			add(oProductGettedByFirstName);
			add(oProductGettedByFirstName);
		}};
		// When call to this method return the current Product
		Mockito.when(this.serviceProduct.getAll()).thenReturn(listProduct);
		// Mock the request to this endpoint
		MockHttpServletRequestBuilder mockHttpServletRequestBuilder = MockMvcRequestBuilders.get("/product/action/getAll")
				.contentType(MediaType.APPLICATION_JSON);
		// Check the Result
		ResultActions resultActions = this.mockMvc.perform(mockHttpServletRequestBuilder)
				.andExpect(MockMvcResultMatchers.status().isOk());
		// New Mapper
		ObjectMapper objectMapper = new ObjectMapper();
		// Get the returned Products
		List<Product> oGettedProducts = objectMapper.readValue(resultActions.andReturn().getResponse().getContentAsString(),
				objectMapper.getTypeFactory().constructCollectionType(List.class, Product.class));
		// Check equals
		Assert.assertNotEquals(null, oGettedProducts);
		Assert.assertNotEquals(0, oGettedProducts.size());
	}
	/**
	 * Create the JSON to pass to the request
	 * @param oProduct
	 * @return
	 */
	private String createProductJSON(Product oProduct){
		return "{ \"nickname\": \"" + oProduct.getId() + "\", " +
				"\"password\":\"" + oProduct.getStrName() + "\"," +
				"\"firstName\":\"" + oProduct.getPrice() + "\"," +
				"\"email\":\"" + oProduct.getICant() + "\"}";
	}
}
