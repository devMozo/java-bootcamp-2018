package bootcamp2018.ShoppingCart;

import io.swagger.annotations.*;

import java.util.ArrayList;

/**
 * Represents the shopping cart of our app
 * @author Mozo Nicolas
 * @version 1.0
 * @since 1.0
 */
@Api(value = "/cart", description = "An API to handle Products inside a Cart", produces = "application/json")
public class ShoppingCart {
	// The shopping cart
	ArrayList<Product> arrProducts = new ArrayList();
	/**
	 * Add a new product to the cart
	 * @param oProduct
	 */
	@ApiOperation(value = "Add a new Product to the cart.", httpMethod = "POST", consumes =  "Product", code = 200)
	@ApiResponses(value = {@ApiResponse(code = 200, message = "All was good!"),
			               @ApiResponse(code = 404, message = "You need to pass a not-null product and with its completed fields")})
	public void add(@ApiParam(value = "The product to add", name = "oProduct", required = true, type = "Product") Product oProduct) throws Exception {
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
	@ApiOperation(value = "Get the current cart", httpMethod = "GET", produces = "ArrayList<Product>", code = 200)
	public ArrayList<Product> getArrCart() {
		return this.arrProducts;
	}
	/**
	 * Remove a product by index
	 * @param i
	 */
	@ApiOperation(value = "Remove a product from the cart by the ID", httpMethod = "DELETE", consumes = "Integer", code = 200)
	@ApiResponses(value = {@ApiResponse(code = 200, message = "All was good!"),
			@ApiResponse(code = 404, message = "You've wanted to delete a product that doesn't exist")})
	public void remove(@ApiParam(value = "The ID of the Product", name = "i", required = true, type = "int") int i) throws IndexOutOfBoundsException {
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
	@ApiOperation(value = "Update a product passing its ID and the new Product", httpMethod = "PUT", consumes = "int, Product", code = 200)
	@ApiResponses(value = {@ApiResponse(code = 200, message = "All was good!"),
			@ApiResponse(code = 404, message = "You've wanted to delete a product that doesn't exist")})
	public void update(@ApiParam(value = "The ID of the Product", name = "i", required = true, type = "int") int i,
					   @ApiParam(value = "The product to update", name = "oProduct", required = true, type = "Product") Product oProduct) {
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
