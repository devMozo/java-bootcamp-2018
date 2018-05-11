package Bootcamp2018.API.services;

import Bootcamp2018.API.entities.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import Bootcamp2018.API.daos.iDAOProduct;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

/**
 * Represents the service to handle products
 * products and the shoppings cart
 * @author Mozo Nicolas
 * @version 1.0
 * @since 1.0
 */
@Service
public class ServiceProduct {
	@Autowired
	private iDAOProduct iDAOProduct;
	/**
	 * Add a new product
	 * @param oProduct
	 */
	@Transactional
	public Product add(Product oProduct) {
		// Default response
		Product oInnerProduct = null;
		// If the product null or has empty fields
		if(oProduct != null){
			oInnerProduct = this.iDAOProduct.save(oProduct);
		}
		// Return the saved product
		return oInnerProduct;
	}
	/**
	 * Update a product
	 * @param oProduct
	 */
	@Transactional
	public Product update(Product oProduct) {
		// Default response
		Product oInnerProduct = null;
		// If the index position is bigger than the size of the array
		if(oProduct != null && this.iDAOProduct.existsById(oProduct.getId())){
			throw new IndexOutOfBoundsException("You've wanted to delete a product that doesn't exist");
			// If not..
		} else {
			// Update the product
			oInnerProduct = this.iDAOProduct.save(oProduct);
		}
		// Return the updated product
		return oInnerProduct;
	}
	/**
	 * Remove a product
	 * @param oProduct
	 */
	@Transactional
	public void remove(Product oProduct) {
		// If the index position is bigger than the size of the array
		if(oProduct == null || !this.iDAOProduct.existsById(oProduct.getId())){
			throw new IndexOutOfBoundsException("You've wanted to delete a product that doesn't exist");
			// If not..
		} else {
			// Remove product
			this.iDAOProduct.delete(oProduct);
		}
	}
	/**
	 * Get an specific product
	 * @return ArrayList
	 */
	@Transactional
	public Optional<Product> get(Long id) {
		// Get the first coincidence
		return this.iDAOProduct.findById(id);
	}
	/**
	 * Get all products
	 * @return ArrayList
	 */
	@Transactional
	public List<Product> getAll() {
		// Get all coincidences
		return this.iDAOProduct.findAll();
	}
}
