package bootcamp2018.ShoppingCart;

import org.junit.Assert;
import org.junit.Test;
/**
 * Represents the test of the shopping cart
 * @author Mozo Nicolas
 * @version 1.0
 * @since 1.0
 */
public class ShoppingCartTest {
	/**
	 * If we want to add an empty or null product
	 * to our cart we'll receive an exception
	 * @throws Exception
	 */
	@Test(expected = Exception.class)
	public void givenProductWhenIsNullOrEmptyThenThrowException() throws Exception{
		// New Shopping Cart
		ShoppingCart oShoppingCart = new ShoppingCart();
		// New null product
		Product oProduct = null;
		// Try to add it
		oShoppingCart.add(oProduct);
		// New empty product
		oProduct = new Product();
		// Try to add it
		oShoppingCart.add(oProduct);
	}
	/**
	 * Get the list of the shopping cart
	 */
	@Test
	public void whenGetShoppingCart(){
		// New Shopping Cart
		ShoppingCart oShoppingCart = new ShoppingCart();
		// If the cart isn't null
		Assert.assertNotEquals(null, oShoppingCart.getArrCart());
		// And the type of the object is an ArrayList
		Assert.assertEquals("ArrayList", oShoppingCart.getArrCart().getClass().getSimpleName());
	}
	/**
	 * When we want to delete a product from the cart
	 * and this index will out of the range
	 * we'll receive an exepction
	 * @throws IndexOutOfBoundsException
	 */
	@Test(expected = IndexOutOfBoundsException.class)
	public void whenDeleteAnElementFromTheCart() throws IndexOutOfBoundsException{
		// New Shopping Cart
		ShoppingCart oShoppingCart = new ShoppingCart();
		// New Product
		Product oProduct = new Product();
		// Initialize Product
		oProduct.setStrName("Chevrolet");
		// Initialize the Product's Quantity
		oProduct.setiCant(20);
		// Add several times the same products to the cart
		try {
			oShoppingCart.add(oProduct);
			oShoppingCart.add(oProduct);
			oShoppingCart.add(oProduct);
			oShoppingCart.add(oProduct);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// Remove the second
		oShoppingCart.remove(2);
		// Check that was removed
		Assert.assertEquals(3, oShoppingCart.getArrCart().size());
		// Try to remove with a non-existent index
		oShoppingCart.remove(20);
	}
	/**
	 * When we want to update a product from the cart
	 * and this index will out of the range
	 * we'll receive an exepction
	 * @throws IndexOutOfBoundsException
	 */
	@Test(expected = IndexOutOfBoundsException.class)
	public void whenUpdateAnElementFromTheCart() {
		// Shopping Cart
		ShoppingCart oShoppingCart = new ShoppingCart();
		// New Product
		Product oProduct = new Product();
		// Initialize the name of the product
		oProduct.setStrName("Chevrolet");
		// Initialize the quantity of the product
		oProduct.setiCant(20);
		// Add several times the product to the chart
		try {
			oShoppingCart.add(oProduct);
			oShoppingCart.add(oProduct);
			oShoppingCart.add(oProduct);
			oShoppingCart.add(oProduct);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// Update the information of the product
		oProduct.setStrName("Ford");
		oProduct.setiCant(40);
		// And try to update some product that we've inserted before
		oShoppingCart.update(2, oProduct);
		// Check if the size is the same
		Assert.assertEquals(4, oShoppingCart.getArrCart().size());
		// Check if the Product's Name that we've updated was converted with success
		Assert.assertEquals("Ford", oShoppingCart.getArrCart().get(2).getStrName());
		// Check if the Product's Quantity that we've updated was converted with success
		Assert.assertEquals(40, oShoppingCart.getArrCart().get(2).getiCant());
		// Try to update with a non-existent index
		oShoppingCart.update(20, oProduct);
	}
}
