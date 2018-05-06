package Bootcamp2018.Topic6.controllers;

import Bootcamp2018.Topic6.entities.Product;
import Bootcamp2018.Topic6.services.ServiceProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.net.URL;
import java.util.List;

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
		// Create the new location to redirect to it
		URI newLocation = ServletUriComponentsBuilder
									.fromCurrentServletMapping()
									.path("/action/get/{id}")
									.buildAndExpand(oSavedProduct.getId())
									.toUri();
		// Build the response and return it
		return ResponseEntity.created(newLocation).build();
	}
	/**
	 * Update a product by id
	 * @param id
	 * @param oProduct
	 * @return
	 */
	@RequestMapping(path = "/action/update/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> update(@PathVariable("id") long id, @RequestBody Product oProduct){
		// Get the saved product from the DB
		Product oOldProduct = this.serviceProduct.get(id);
		// New response by default
		ResponseEntity oResponse = ResponseEntity.notFound().build();
		// If the product exist
		if(oOldProduct != null){
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
		Product oProductToDelete = this.serviceProduct.get(id);
		// Delete it
		this.serviceProduct.remove(oProductToDelete);
		// Return an ok's message
		return ResponseEntity.ok().build();
	}
	/**
	 * Get an specific product
	 * @param id
	 * @return
	 */
	@RequestMapping(path = "/action/get/{id}", produces = "application/json", method = RequestMethod.GET)
	public Product get(@PathVariable("id") Long id){
		return this.serviceProduct.get(id);
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
