package Bootcamp2018.API.controllers;

import Bootcamp2018.API.entities.Product;
import Bootcamp2018.API.entities.User;
import Bootcamp2018.API.services.ServiceUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.lang.invoke.MethodType;
import java.net.URI;
import java.util.List;
import java.util.Optional;

/**
 * Represents the controller to handle the User's CRUD
 * products and the shoppings cart
 * @author Mozo Nicolas
 * @version 1.0
 * @since 1.0
 */
@RestController
@RequestMapping("/user")
public class ControllerUser {
	// Our service
	@Autowired
	ServiceUser oServiceUser;
	/**
	 * Create a new user
	 * @param oUser
	 * @return
	 */
	@RequestMapping(path = "/action/add", method = RequestMethod.POST)
	public ResponseEntity<?> add(@RequestBody User oUser){
		// Get the saved user
		User oSavedUser = this.oServiceUser.add(oUser);
		// By default the response is a bad request
		ResponseEntity oResponse = ResponseEntity.badRequest().build();
		// Check if the user exist
		if(oSavedUser != null) {
			// Create the new location to redirect to it
			URI newLocation = ServletUriComponentsBuilder.fromCurrentServletMapping().path("/action/get/{id}")
					.buildAndExpand(oSavedUser.getId()).toUri();
			// Save the new response
			oResponse = ResponseEntity.created(newLocation).build();
		}
		// Build the response and return it
		return oResponse;
	}
	/**
	 * Update a user
	 * @param id
	 * @param oUser
	 * @return
	 */
	@RequestMapping(path = "/action/update/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody User oUser){
		// Get the saved user from the DB
		Optional optOldUser = this.oServiceUser.get(id);
		// New response by default
		ResponseEntity oResponse = ResponseEntity.notFound().build();
		// If the user exist
		if(optOldUser.isPresent()){
			// Set the id to the user to update
			oUser.setId(id);
			// Get the updated user
			User oSavedUser = this.oServiceUser.update(oUser);
			// Create the new location to redirect to it
			URI newLocation = ServletUriComponentsBuilder
					.fromCurrentServletMapping()
					.path("/action/get/{id}")
					.buildAndExpand(oSavedUser.getId())
					.toUri();
			// Build the response
			oResponse = ResponseEntity.created(newLocation).build();
		}
		// Return the response's object
		return oResponse;
	}
	/**
	 * Remove a user
	 * @param id
	 */
	@RequestMapping(path = "/action/delete/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> delete(@PathVariable("id") Long id){
		// Get the user to delete
		Optional<User> optUserToDelete = this.oServiceUser.get(id);
		// Response by default
		ResponseEntity oResponse = ResponseEntity.notFound().build();
		// If the user exists
		if(optUserToDelete.isPresent()){
			// Delete it
			this.oServiceUser.remove(optUserToDelete.get());
			// Become the response to OK
			oResponse = ResponseEntity.ok().build();
		}
		// Return an ok's message
		return oResponse;
	}
	/**
	 *  Get a user
	 * @param id
	 * @return
	 */
	@RequestMapping(path = "/action/get/{id}", method = RequestMethod.GET, produces = "application/json")
	public User get(@PathVariable("id") Long id){
		// Get the user to search
		Optional<User> optProduct = this.oServiceUser.get(id);
		// Response by default
		User oResponse = null;
		// If the user exists
		if(optProduct.isPresent()){
			// Become the response to the correct user
			oResponse = optProduct.get();
		}
		// Return an ok's message
		return oResponse;
	}
	/**
	 *  Get a users by the first name
	 * @param strFirstName
	 * @return
	 */
	@RequestMapping(path = "/action/getByFirstName/{firstName}", method = RequestMethod.GET, produces = "application/json")
	public List<User> getByFirstName(@PathVariable("firstName") String strFirstName){
		// Return all user that has that name
		return this.oServiceUser.getByFirstName(strFirstName);
	}
	/**
	 *  Get a users by the nickname
	 * @param strNickname
	 * @return
	 */
	@RequestMapping(path = "/action/getByNickname/{nickname}", method = RequestMethod.GET, produces = "application/json")
	public User getByNickname(@PathVariable("nickname") String strNickname){
		// Return the user that has that nickname
		return this.oServiceUser.getByNickname(strNickname);
	}
	/**
	 * Get all users
	 * @return
	 */
	@RequestMapping(path = "/action/getAll", method = RequestMethod.GET, produces = "application/json")
	public List<User> getAll(){
		return this.oServiceUser.getAll();
	}
}
