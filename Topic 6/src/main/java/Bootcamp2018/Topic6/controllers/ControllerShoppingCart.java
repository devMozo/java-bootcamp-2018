package Bootcamp2018.Topic6.controllers;

import Bootcamp2018.Topic6.entities.LineCart;
import Bootcamp2018.Topic6.entities.Product;
import Bootcamp2018.Topic6.entities.User;
import Bootcamp2018.Topic6.services.ServiceLineCart;
import Bootcamp2018.Topic6.services.ServiceProduct;
import Bootcamp2018.Topic6.services.ServiceUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.sound.sampled.Line;
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
	// Product's Service
	@Autowired
	ServiceProduct serviceProduct;
	/**
	 * Add a new line-cart
	 * @param oLineCart
	 * @return
	 */
	@RequestMapping(path = "/action/add", method = RequestMethod.POST)
	public ResponseEntity<?> add(@RequestBody LineCart oLineCart,
								 @RequestParam("idProduct") Long idProduct,
								 @RequestParam("idUser") Long idUser){
		// By default the response is bad :(
		ResponseEntity oResponse = ResponseEntity.badRequest().build();
		// Get the selected product
		Optional<Product> optProduct = this.serviceProduct.get(idProduct);
		// Get also the user
		Optional<User> optUser = this.serviceUser.get(idUser);
		// If the user and the product exist
		if(optProduct.isPresent() && optUser.isPresent()){
			// Set the user
			oLineCart.setUser(optUser.get());
			// Set the product
			oLineCart.setProduct(optProduct.get());
			// Get the saved line-cart
			LineCart oSavedLineCart = this.serviceLineCart.add(oLineCart);
			// Create the new location to redirect to it
			URI newLocation = ServletUriComponentsBuilder
					.fromCurrentServletMapping()
					.path("/action/get/{id}")
					.buildAndExpand(oSavedLineCart.getId())
					.toUri();
			// Become the response to "made with success"
			oResponse =  ResponseEntity.created(newLocation).build();
		}
		// Build the response and return it
		return oResponse;
	}
	/**
	 * Update a line-cart by id
	 * @param id
	 * @param oLineCart
	 * @return
	 */
	@RequestMapping(path = "/action/update/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody LineCart oLineCart){
		// Get the saved line-cart from the DB
		Optional optOldLineCart = this.serviceLineCart.get(id);
		// New response by default
		ResponseEntity oResponse = ResponseEntity.notFound().build();
		// If the line-cart exist
		if(optOldLineCart.isPresent()){
			// Set the id to the line-cart to update
			oLineCart.setId(id);
			// Get the updated line-cart
			LineCart oSavedProduct = this.serviceLineCart.update(oLineCart);
			// Create the new location to redirect to it
			URI newLocation = ServletUriComponentsBuilder
					.fromCurrentServletMapping()
					.path("/action/get/{id}")
					.buildAndExpand(oSavedProduct.getId())
					.toUri();
			// Build the response
			oResponse = ResponseEntity.created(newLocation).build();
		}
		// Return the response's object
		return oResponse;
	}
	/**
	 * Delete a line-cart by id
	 * @param id
	 * @return
	 */
	@RequestMapping(path = "/action/delete/{id}", produces = "application/json", method = RequestMethod.DELETE)
	public ResponseEntity<?> delete(@PathVariable("id") Long id){
		// Get the line-cart to delete
		Optional<LineCart> optLineCartToDelete = this.serviceLineCart.get(id);
		// Response by default
		ResponseEntity oResponse = ResponseEntity.notFound().build();
		// If the line-cart exists
		if(optLineCartToDelete.isPresent()){
			// Delete it
			this.serviceLineCart.remove(optLineCartToDelete.get());
			// Become the response to OK
			oResponse = ResponseEntity.ok().build();
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
	public LineCart get(@PathVariable("id") Long id){
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
	public List<LineCart> getAll(){
		return this.serviceLineCart.getAll();
	}
}
