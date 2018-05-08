package Bootcamp2018.API.controllers;

import Bootcamp2018.API.entities.LineCart;
import Bootcamp2018.API.entities.Product;
import Bootcamp2018.API.entities.User;
import Bootcamp2018.API.services.ServiceAuth;
import Bootcamp2018.API.services.ServiceLineCart;
import Bootcamp2018.API.services.ServiceProduct;
import Bootcamp2018.API.services.ServiceUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

/**
 * Represents the controller to handle the Shopping Cart's CRUD
 * products and the shoppings cart
 * @author Mozo Nicolas
 * @version 1.0
 * @since 1.0
 */
@RestController
@RequestMapping("/cart")
public class ControllerShoppingCart {
	// Shopping Cart's Service
	@Autowired
	ServiceLineCart serviceLineCart;
	// User's Service
	@Autowired
	ServiceUser serviceUser;
	// Auth's Service
	@Autowired
	ServiceAuth serviceAuth;
	// Product's Service
	@Autowired
	ServiceProduct serviceProduct;
	/**
	 * Add a new line-cart
	 * @param iCant
	 * @param idProduct
	 * @param strEncodedID
	 * @return
	 */
	@RequestMapping(path = "/action/add", method = RequestMethod.POST)
	public ResponseEntity<?> add(@RequestParam("cant") int iCant,
								 @RequestParam("idProduct") Long idProduct,
	 							 @CookieValue(value = "strEncodedID") String strEncodedID){
		// By default the response is bad :(
		ResponseEntity oResponse = ResponseEntity.badRequest().build();
		// Get the user from Redis if exists
		User oRedisUser = this.serviceAuth.find(strEncodedID);
		// Has the user logged and the quantity is bigger than 0?
		if(oRedisUser != null && iCant > 0) {
			// Get the selected product
			Optional<Product> optProduct = this.serviceProduct.get(idProduct);
			// If the user and the product exist
			if (optProduct.isPresent()) {
				// Create a new line-cart
				LineCart oLineCart = new LineCart();
				// Set the quantity
				oLineCart.setICant(iCant);
				// Update the subtotal
				oLineCart.setISubtotal(optProduct.get().getPrice() * iCant);
				// Set the user
				oLineCart.setUser(oRedisUser);
				// Set the product
				oLineCart.setProduct(optProduct.get());
				// Get the saved line-cart
				LineCart oSavedLineCart = this.serviceLineCart.add(oLineCart);
				// Create the new location to redirect to it
				URI newLocation = ServletUriComponentsBuilder.fromCurrentServletMapping().path("/action/get/{id}")
						.buildAndExpand(oSavedLineCart.getId()).toUri();
				// Become the response to "made with success"
				oResponse = ResponseEntity.created(newLocation).build();
			}
		}
		// Build the response and return it
		return oResponse;
	}
	/**
	 * Update a line-cart by id
	 * @param iCant
	 * @param idLineCart
	 * @param strEncodedID
	 * @return
	 */
	@RequestMapping(path = "/action/update/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> update(@RequestParam("cant") int iCant,
									@PathVariable("id") Long idLineCart,
	 							 	@CookieValue(value = "strEncodedID") String strEncodedID){
		// New response by default
		ResponseEntity oResponse = ResponseEntity.notFound().build();
		// Get the user from Redis if exists
		User oRedisUser = this.serviceAuth.find(strEncodedID);
		// Has the user logged and the quantity is bigger than 0?
		if(oRedisUser != null && iCant > 0) {
			// Get the saved line-cart from the DB
			Optional<LineCart> optOldLineCart = this.serviceLineCart.get(idLineCart);
			// Create a new line-cart
			LineCart oLineCart = optOldLineCart.get();
			// If the line-cart exists and the line-cart that I'm updating now is mine
			if (optOldLineCart.isPresent() && oLineCart.getUser().getId() == oRedisUser.getId()) {
				// Update the line-cart
				oLineCart.setICant(iCant);
				oLineCart.setISubtotal(oLineCart.getProduct().getPrice() * iCant);
				// Get the updated line-cart
				LineCart oSavedProduct = this.serviceLineCart.update(oLineCart);
				// Create the new location to redirect to it
				URI newLocation = ServletUriComponentsBuilder.fromCurrentServletMapping().path("/action/get/{id}")
						.buildAndExpand(oSavedProduct.getId()).toUri();
				// Build the response
				oResponse = ResponseEntity.created(newLocation).build();
			}
		}
		// Return the response's object
		return oResponse;
	}
	/**
	 * Delete a line-cart by id
	 * @param idLineCart
	 * @param strEncodedID
	 * @return
	 */
	@RequestMapping(path = "/action/delete/{id}", produces = "application/json", method = RequestMethod.DELETE)
	public ResponseEntity<?> delete(@PathVariable("id") Long idLineCart,
									@CookieValue(value = "strEncodedID") String strEncodedID){
		// Response by default
		ResponseEntity oResponse = ResponseEntity.notFound().build();
		// Get the user from Redis if exists
		User oRedisUser = this.serviceAuth.find(strEncodedID);
		// Has the user logged?
		if(oRedisUser != null) {
			// Get the line-cart to delete
			Optional<LineCart> optLineCartToDelete = this.serviceLineCart.get(idLineCart);
			// Create a new line-cart
			LineCart oLineCart = optLineCartToDelete.get();
			// If the line-cart exists and the line-cart that I'm updating now is mine
			if (optLineCartToDelete.isPresent() && oLineCart.getUser().getId() == oRedisUser.getId()) {
				// Delete it
				this.serviceLineCart.remove(oLineCart);
				// Become the response to OK
				oResponse = ResponseEntity.ok().build();
			}
		}
		// Return an ok's message
		return oResponse;
	}
	/**
	 * Get an specific line-cart
	 * @param id
	 * @return
	 */
	@RequestMapping(path = "/action/get/{id}", produces = "application/json", method = RequestMethod.GET)
	public LineCart get(@PathVariable("id") Long id,
						@CookieValue(value = "strEncodedID") String strEncodedID){
		// Get the line-cart to delete
		Optional<LineCart> optLineCart = this.serviceLineCart.get(id);
		// Response by default
		LineCart oResponse = null;
		// If the line-cart exists
		if(optLineCart.isPresent()){
			// Become the response to the correct product
			oResponse = optLineCart.get();
		}
		// Return an ok's message
		return oResponse;
	}
	/**
	 * Get all lines-cart
	 * @return
	 */
	@RequestMapping(path = "/action/getAll", produces = "application/json", method = RequestMethod.GET)
	public List<LineCart> getAll(@CookieValue(value = "strEncodedID") String strEncodedID){
		return this.serviceLineCart.getAll();
	}
}
