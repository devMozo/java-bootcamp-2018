package Bootcamp2018.Topic6.services;

import Bootcamp2018.Topic6.entities.LineCart;
import Bootcamp2018.Topic6.entities.Product;

import javax.transaction.Transactional;
import Bootcamp2018.Topic6.daos.iDAOLineCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Represents the shopping cart of our app
 * @author Mozo Nicolas
 * @version 1.0
 * @since 1.0
 */
@Service
public class ServiceLineCart {
	// The Line-cart's DAO
	@Autowired
	iDAOLineCart iDAOLineCart;
	/**
	 * Add a new line of the User's Cart
	 * @param oLineCart
	 */
	@Transactional
	public LineCart add(LineCart oLineCart) {
		// Default response
		LineCart oInnerLineCart = null;
		// If the line-cart null or has empty fields
		if(oLineCart == null){
			try {
				throw new Exception("You need to pass a not-null line-cart");
			} catch (Exception e) {
				e.printStackTrace();
			}
			// If not..
		} else {
			oInnerLineCart = this.iDAOLineCart.save(oLineCart);
		}
		// Return the saved line-cart
		return oInnerLineCart;
	}
	/**
	 * Update a Line-cart
	 * @param oLineCart
	 */
	@Transactional
	public LineCart update(LineCart oLineCart) {
		// Default response
		LineCart oInnerLineCart = null;
		// If the index position is bigger than the size of the array
		if(oLineCart == null || !this.iDAOLineCart.existsById(oLineCart.getId())){
			try {
				throw new IndexOutOfBoundsException("You've wanted to delete a line-cart that doesn't exist");
			} catch (Exception e) {
				e.printStackTrace();
			}
			// If not..
		} else {
			// Update the line-cart
			oInnerLineCart = this.iDAOLineCart.save(oLineCart);
		}
		// Return the updated line-cart
		return oInnerLineCart;
	}
	/**
	 * Remove a Line-cart
	 * @param oLineCart
	 */
	@Transactional
	public void remove(LineCart oLineCart) {
		// If the index position is bigger than the size of the array
		if(oLineCart == null || !this.iDAOLineCart.existsById(oLineCart.getId())){
			try {
				throw new IndexOutOfBoundsException("You've wanted to delete a line-cart that doesn't exist");
			} catch (Exception e) {
				e.printStackTrace();
			}
			// If not..
		} else {
			// Remove line-cart
			this.iDAOLineCart.delete(oLineCart);
		}
	}
	/**
	 * Get an specific Line-cart
	 * @return ArrayList
	 */
	@Transactional
	public Optional<LineCart> get(Long id) {
		// Get the first coincidence
		return this.iDAOLineCart.findById(id);
	}
	/**
	 * Get all Lines-cart
	 * @return ArrayList
	 */
	@Transactional
	public List<LineCart> getAll() {
		// Get all coincidences
		return this.iDAOLineCart.findAll();
	}
}
