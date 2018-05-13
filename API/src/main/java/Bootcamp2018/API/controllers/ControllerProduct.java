package Bootcamp2018.API.controllers;

import Bootcamp2018.API.entities.Product;
import Bootcamp2018.API.services.ServiceProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.net.URL;
import java.util.List;
import java.util.Optional;

/**
 * Represents the controller to handle the Product's CRUD
 * products and the shoppings cart
 * @author Mozo Nicolas
 * @version 1.0
 * @since 1.0
 */
@RestController
@RequestMapping("/product")
public class ControllerProduct {
	// Product's Services
	@Autowired
	private ServiceProduct serviceProduct;
	/**
	 * Add a new product
	 * @param oProduct
	 * @return
	 */
	@RequestMapping(path = "/action/add", method = RequestMethod.POST)
	public ResponseEntity<?> add(@RequestBody Product oProduct){
		// Get the saved product
		Product oSavedProduct = this.serviceProduct.add(oProduct);
		// By default the response is a bad request
		ResponseEntity oResponse = ResponseEntity.badRequest().build();
		// Check if the user exist
		if(oSavedProduct != null) {
			// Create the new location to redirect to it
			URI newLocation = ServletUriComponentsBuilder
										.fromCurrentServletMapping()
										.path("/action/get/{id}")
										.buildAndExpand(oSavedProduct.getId())
										.toUri();
			// Save the new response
			oResponse = ResponseEntity.created(newLocation).build();
		}
		// Build the response and return it
		return oResponse;
	}
	/**
	 * Update a product by id
	 * @param id
	 * @param oProduct
	 * @return
	 */
	@RequestMapping(path = "/action/update/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody Product oProduct){
		// Get the saved product from the DB
		Optional optOldProduct = this.serviceProduct.get(id);
		// New response by default
		ResponseEntity oResponse = ResponseEntity.notFound().build();
		// If the product exist
		if(optOldProduct.isPresent()){
			// Set the id to the product to update
			oProduct.setId(id);
			// Get the updated product
			Product oSavedProduct = this.serviceProduct.update(oProduct);
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
	 * Delete a product by id
	 * @param id
	 * @return
	 */
	@RequestMapping(path = "/action/delete/{id}", produces = "application/json", method = RequestMethod.DELETE)
	public ResponseEntity<?> delete(@PathVariable("id") Long id){
		// Get the product to delete
		Optional<Product> optProductToDelete = this.serviceProduct.get(id);
		// Response by default
		ResponseEntity oResponse = ResponseEntity.notFound().build();
		// If the product exists
		if(optProductToDelete.isPresent()){
			// Delete it
			this.serviceProduct.remove(optProductToDelete.get());
			// Become the response to OK
			oResponse = ResponseEntity.ok().build();
		}
		// Return an ok's message
		return oResponse;
	}
	/**
	 * Get an specific product
	 * @param id
	 * @return
	 */
	@RequestMapping(path = "/action/get/{id}", produces = "application/json", method = RequestMethod.GET)
	public Product get(@PathVariable("id") Long id){
		// Get the product to delete
		Optional<Product> optProduct = this.serviceProduct.get(id);
		// Response by default
		Product oResponse = null;
		// If the product exists
		if(optProduct.isPresent()){
			// Become the response to the correct product
			oResponse = optProduct.get();
		}
		// Return an ok's message
		return oResponse;
	}
	/**
	 * Get all products
	 * @return
	 */
	@RequestMapping(path = "/action/getAll", produces = "application/json", method = RequestMethod.GET)
	public List<Product> getAll(){
		return this.serviceProduct.getAll();
	}
}
