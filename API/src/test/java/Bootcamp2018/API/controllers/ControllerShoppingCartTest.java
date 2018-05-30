package Bootcamp2018.API.controllers;

import Bootcamp2018.API.entities.LineCart;
import Bootcamp2018.API.entities.Product;
import Bootcamp2018.API.entities.User;
import Bootcamp2018.API.services.ServiceAuth;
import Bootcamp2018.API.services.ServiceLineCart;
import Bootcamp2018.API.services.ServiceProduct;
import Bootcamp2018.API.services.ServiceUser;
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

import javax.servlet.http.Cookie;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Represents the test to the Shopping Cart's Controller
 * @author Mozo Nicolas
 * @version 1.0
 * @since 1.0
 */
@RunWith(SpringRunner.class)
// To test the Controllers, we can use @WebMvcTest.
// It will auto-configure the Spring MVC
// infrastructure for our unit tests.
@WebMvcTest(ControllerShoppingCart.class)
public class ControllerShoppingCartTest {
	// MockMvc which offers a powerful way of
	// easy testing MVC controllers
	// without starting a full HTTP server.
	@Autowired
	private MockMvc mockMvc;
	// The Product's Service of the Controller
	@MockBean
	private ServiceProduct serviceProduct;
	// Shopping Cart's Service
	@MockBean
	private ServiceLineCart serviceLineCart;
	// User's Service
	@MockBean
	private ServiceUser serviceUser;
	// Auth's Service
	@MockBean
	private ServiceAuth serviceAuth;
	// The user that we are going to use
	private User oUser;
	// The Product that we are going to use
	private Product oProduct;
	// The LineCart that we are going to use
	private LineCart oLineCart;
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
		// New Product
		this.oProduct = new Product();
		// Set the main data
		this.oProduct.setId(new Long(20));
		this.oProduct.setStrName("Nuevo Producto");
		this.oProduct.setPrice(20);
		this.oProduct.setICant(100);
		// Set a new LineCart
		this.oLineCart = new LineCart();
		// Set the main data
		this.oLineCart.setId(new Long(1));
		this.oLineCart.setICant(200);
		this.oLineCart.setISubtotal(1000);;
		this.oLineCart.setUser(this.oUser);
		this.oLineCart.setProduct(this.oProduct);
	}
	/**
	 * Test when I want to create a new LineCart with correct data
	 */
	@Test
	public void givenCorrectDataWhenAddItThenReturnIt() throws Exception {
		// Create an optional
		Optional<Product> optionalProduct = Optional.of(this.oProduct);
		// When call to this method return the current LineCart
		Mockito.when(this.serviceAuth.find("1")).thenReturn(this.oUser);
		Mockito.when(this.serviceProduct.get(ArgumentMatchers.any(Long.class))).thenReturn(optionalProduct);
		Mockito.when(this.serviceLineCart.add(ArgumentMatchers.any(LineCart.class))).thenReturn(this.oLineCart);
		// Mock the request to this endpoint
		MockHttpServletRequestBuilder mockHttpServletRequestBuilder = MockMvcRequestBuilders.post("/cart/action/add")
				.contentType(MediaType.APPLICATION_JSON)
				.param("cant", "20")
				.param("idProduct", "20")
				.cookie(new Cookie("strEncodedID", "1"));
		// Check the Result
		ResultActions resultActions = this.mockMvc.perform(mockHttpServletRequestBuilder)
				.andExpect(MockMvcResultMatchers.status().isCreated());
	}
	/**
	 * Test when I want to create a new LineCart with incorrect data
	 */
	@Test
	public void givenIncorrectDataWhenAddItThenReturnIt() throws Exception {
		// Create an optional
		Optional<Product> optionalProduct = Optional.of(this.oProduct);
		// When call to this method return the current LineCart
		Mockito.when(this.serviceAuth.find("1")).thenReturn(null);
		// Mock the request to this endpoint
		MockHttpServletRequestBuilder mockHttpServletRequestBuilder = MockMvcRequestBuilders.post("/cart/action/add")
				.contentType(MediaType.APPLICATION_JSON)
				.param("cant", "20")
				.param("idProduct", "20")
				.cookie(new Cookie("strEncodedID", "1"));
		// Check the Result
		ResultActions resultActions = this.mockMvc.perform(mockHttpServletRequestBuilder)
				.andExpect(MockMvcResultMatchers.status().isBadRequest());
	}
	/**
	 * Test when I want to update a LineCart with correct data
	 */
	@Test
	public void givenCorrectDataWhenUpdateItThenReturnIt() throws Exception {
		// Create an optional
		Optional<LineCart> optionalLineCart = Optional.of(this.oLineCart);
		// When call to this method return the current LineCart
		Mockito.when(this.serviceAuth.find("1")).thenReturn(this.oUser);
		Mockito.when(this.serviceLineCart.get(ArgumentMatchers.any(Long.class))).thenReturn(optionalLineCart);
		Mockito.when(this.serviceLineCart.update(ArgumentMatchers.any(LineCart.class))).thenReturn(this.oLineCart);
		// Mock the request to this endpoint
		MockHttpServletRequestBuilder mockHttpServletRequestBuilder = MockMvcRequestBuilders.put("/cart/action/update/" + 1)
				.contentType(MediaType.APPLICATION_JSON)
				.param("cant", "20")
				.cookie(new Cookie("strEncodedID", "1"));
		// Check the Result
		ResultActions resultActions = this.mockMvc.perform(mockHttpServletRequestBuilder)
				.andExpect(MockMvcResultMatchers.status().isCreated());
	}
	/**
	 * Test when I want to update a LineCart with incorrect data
	 */
	@Test
	public void givenIncorrectDataWhenUpdateItThenReturnIt() throws Exception {
		// When call to this method return the current LineCart
		Mockito.when(this.serviceAuth.find("1")).thenReturn(null);
		// Mock the request to this endpoint
		MockHttpServletRequestBuilder mockHttpServletRequestBuilder = MockMvcRequestBuilders.put("/cart/action/update/" + 1)
				.contentType(MediaType.APPLICATION_JSON)
				.param("cant", "20")
				.cookie(new Cookie("strEncodedID", "1"));
		// Check the Result
		ResultActions resultActions = this.mockMvc.perform(mockHttpServletRequestBuilder)
				.andExpect(MockMvcResultMatchers.status().isNotFound());
	}
	/**
	 * Test when I want to delete a LineCart with correct data
	 */
	@Test
	public void givenCorrectDataWhenDeleteItThenReturnIt() throws Exception {
		// Create an optional
		Optional<LineCart> optionalLineCart = Optional.of(this.oLineCart);
		// When call to this method return the current LineCart
		Mockito.when(this.serviceAuth.find("1")).thenReturn(this.oUser);
		Mockito.when(this.serviceLineCart.get(ArgumentMatchers.any(Long.class))).thenReturn(optionalLineCart);
		Mockito.doNothing().when(this.serviceLineCart).remove(ArgumentMatchers.any(LineCart.class));
		// Mock the request to this endpoint
		MockHttpServletRequestBuilder mockHttpServletRequestBuilder = MockMvcRequestBuilders.delete("/cart/action/delete/" + 1)
				.contentType(MediaType.APPLICATION_JSON)
				.cookie(new Cookie("strEncodedID", "1"));
		// Check the Result
		ResultActions resultActions = this.mockMvc.perform(mockHttpServletRequestBuilder)
				.andExpect(MockMvcResultMatchers.status().isOk());
	}
	/**
	 * Test when I want to delete a LineCart with incorrect data
	 */
	@Test
	public void givenIncorrectDataWhenDeleteItThenReturnIt() throws Exception {
		// When call to this method return the current LineCart
		Mockito.when(this.serviceAuth.find("1")).thenReturn(null);
		// Mock the request to this endpoint
		MockHttpServletRequestBuilder mockHttpServletRequestBuilder = MockMvcRequestBuilders.delete("/cart/action/delete/" + 1)
				.contentType(MediaType.APPLICATION_JSON)
				.cookie(new Cookie("strEncodedID", "1"));
		// Check the Result
		ResultActions resultActions = this.mockMvc.perform(mockHttpServletRequestBuilder)
				.andExpect(MockMvcResultMatchers.status().isNotFound());
	}
	/**
	 * Test when I want to get a LineCart with correct data
	 */
	@Test
	public void givenCorrectDataWhenGetItThenReturnIt() throws Exception {
		// Create an optional
		Optional<LineCart> optionalLineCart = Optional.of(this.oLineCart);
		// When call to this method return the current LineCart
		Mockito.when(this.serviceLineCart.get(new Long(1))).thenReturn(optionalLineCart);
		// Mock the request to this endpoint
		MockHttpServletRequestBuilder mockHttpServletRequestBuilder = MockMvcRequestBuilders.get("/cart/action/get/" + 1)
				.contentType(MediaType.APPLICATION_JSON)
				.cookie(new Cookie("strEncodedID", "1"));
		// Check the Result
		ResultActions resultActions = this.mockMvc.perform(mockHttpServletRequestBuilder)
				.andExpect(MockMvcResultMatchers.status().isOk());
		// New Mapper
		ObjectMapper objectMapper = new ObjectMapper();
		// Get the returned LineCart
		LineCart oGettedLineCart = objectMapper.readValue(resultActions.andReturn().getResponse().getContentAsString(), LineCart.class);
		// Check equals
		Assert.assertEquals(this.oLineCart.getId(), oGettedLineCart.getId());
	}
	/**
	 * Test when I want to get a LineCart with incorrect data
	 */
	@Test
	public void givenIncorrectDataWhenGetItThenReturnIt() throws Exception {
		// Create an optional
		Optional<LineCart> optionalLineCart = Optional.empty();
		// When call to this method return the current LineCart
		Mockito.when(this.serviceLineCart.get(new Long(1))).thenReturn(optionalLineCart);
		// Mock the request to this endpoint
		MockHttpServletRequestBuilder mockHttpServletRequestBuilder = MockMvcRequestBuilders.get("/cart/action/get/" + 1)
				.contentType(MediaType.APPLICATION_JSON)
				.cookie(new Cookie("strEncodedID", "1"));
		// Check the Result
		ResultActions resultActions = this.mockMvc.perform(mockHttpServletRequestBuilder)
				.andExpect(MockMvcResultMatchers.status().isOk());
		// Check equals
		Assert.assertEquals("", resultActions.andReturn().getResponse().getContentAsString());
	}
	/**
	 * Test when I want to get all LineCarts
	 */
	@Test
	public void whenGetAllThenReturnThen() throws Exception {
		// Save the LineCart in a final variable
		final LineCart oLineCartGettedByFirstName = this.oLineCart;
		// Create a list
		List<LineCart> listLineCart = new ArrayList<LineCart>(){{
			add(oLineCartGettedByFirstName);
			add(oLineCartGettedByFirstName);
			add(oLineCartGettedByFirstName);
		}};
		// When call to this method return the current LineCart
		Mockito.when(this.serviceLineCart.getAll()).thenReturn(listLineCart);
		// Mock the request to this endpoint
		MockHttpServletRequestBuilder mockHttpServletRequestBuilder = MockMvcRequestBuilders.get("/cart/action/getAll")
				.contentType(MediaType.APPLICATION_JSON)
				.cookie(new Cookie("strEncodedID", "1"));
		// Check the Result
		ResultActions resultActions = this.mockMvc.perform(mockHttpServletRequestBuilder)
				.andExpect(MockMvcResultMatchers.status().isOk());
		// New Mapper
		ObjectMapper objectMapper = new ObjectMapper();
		// Get the returned LineCarts
		List<LineCart> oGettedLineCarts = objectMapper.readValue(resultActions.andReturn().getResponse().getContentAsString(),
				objectMapper.getTypeFactory().constructCollectionType(List.class, LineCart.class));
		// Check equals
		Assert.assertNotEquals(null, oGettedLineCarts);
		Assert.assertNotEquals(0, oGettedLineCarts.size());
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
