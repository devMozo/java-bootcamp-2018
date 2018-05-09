package bootcamp2018.ShoppingCart;

import org.junit.Test;

/**
 * Represents the shopping cart of our app
 * @author Mozo Nicolas
 * @version 1.0
 * @since 1.0
 */
public class ProductTest {
	/**
	 * When the user try to set a null name to a Product
	 */
	@Test(expected = Exception.class)
	public void givenNameWhenIsNullThenThrowException() throws Exception{
		// New Product
		Product oProduct = new Product();
		// Set a null string
		oProduct.setStrName(null);
	}
	/**
	 * When the user try to set an empty name to a Product
	 */
	@Test(expected = Exception.class)
	public void givenNameWhenIsEmptyThenThrowException() throws Exception{
		// New Product
		Product oProduct = new Product();
		// Set an empty name
		oProduct.setStrName("");
	}
	/**
	 * When the user try to set zero to the Product's Quantity
	 */
	@Test(expected = Exception.class)
	public void givenCantWhenIsZeroThenThrowException() throws Exception{
		// New Product
		Product oProduct = new Product();
		// Set a 0 cant
		oProduct.setiCant(0);
	}
}
