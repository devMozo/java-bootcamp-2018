package bootcamp2018.ShoppingCart;

import java.util.ArrayList;

/**
 * Represents the shopping cart of our app
 * @author Mozo Nicolas
 * @version 1.0
 * @since 1.0
 */
public class ShoppingCart {
	// The shopping cart
	ArrayList<Product> arrProducts = new ArrayList();
	/**
	 * Add a new product to the cart
	 * @param oProduct
	 */
	public void add(Product oProduct) throws Exception {
		// If the product null or has empty fields
		if(oProduct == null || oProduct.getiCant() == 0 || oProduct.getStrName().equals("")){
			throw new Exception("You need to pass a not-null product and with its completed fields");
		// If not..
		} else {
			this.arrProducts.add(oProduct);
		}
	}
	/**
	 * Get all the shopping cart
	 * @return ArrayList
	 */
	public ArrayList<Product> getArrCart() {
		return this.arrProducts;
	}
	/**
	 * Remove a product by index
	 * @param i
	 */
	public void remove(int i) throws IndexOutOfBoundsException {
		// If the index position is bigger than the size of the array
		if(i > this.arrProducts.size() - 1){
			throw new IndexOutOfBoundsException("You've wanted to delete a product that doesn't exist");
		// If not..
		} else {
			this.arrProducts.remove(i);
		}
	}
	/**
	 * Update a product by index
	 * @param i
	 */
	public void update(int i, Product oProduct) {
		// If the index position is bigger than the size of the array
		if(i > this.arrProducts.size() - 1){
			throw new IndexOutOfBoundsException("You've wanted to delete a product that doesn't exist");
			// If not..
		} else {
			// Remove the element
			this.remove(i);
			// Add this in the position of the last deleted product
			this.arrProducts.add(i, oProduct);
		}
	}
}
