package Bootcamp2018.API.services;

import Bootcamp2018.API.daos.iDAOCategory;
import Bootcamp2018.API.entities.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ServiceCategory {
	@Autowired
	private iDAOCategory iDAOCategory;
	/**
	 * Add a new Category
	 * @param oCategory
	 */
	@Transactional
	public Category add(Category oCategory) {
		// Default response
		Category oInnerCategory = null;
		// If the Category null or has empty fields
		if(oCategory != null){
			oInnerCategory = this.iDAOCategory.save(oCategory);
		}
		// Return the saved Category
		return oInnerCategory;
	}
	/**
	 * Update a Category
	 * @param oCategory
	 */
	@Transactional
	public Category update(Category oCategory) {
		// Default response
		Category oInnerCategory = null;
		// If the index position is bigger than the size of the array
		if(oCategory == null || !this.iDAOCategory.existsById(oCategory.getId())){
			throw new IndexOutOfBoundsException("You've wanted to delete a Category that doesn't exist");
			// If not..
		} else {
			// Update the Category
			oInnerCategory = this.iDAOCategory.save(oCategory);
		}
		// Return the updated Category
		return oInnerCategory;
	}
	/**
	 * Remove a Category
	 * @param oCategory
	 */
	@Transactional
	public void remove(Category oCategory) {
		// If the index position is bigger than the size of the array
		if(oCategory == null || !this.iDAOCategory.existsById(oCategory.getId())){
			throw new IndexOutOfBoundsException("You've wanted to delete a Category that doesn't exist");
			// If not..
		} else {
			// Remove Category
			this.iDAOCategory.delete(oCategory);
		}
	}
	/**
	 * Get an specific Category
	 * @return ArrayList
	 */
	@Transactional
	public Optional<Category> get(Long id) {
		// Get the first coincidence
		return this.iDAOCategory.findById(id);
	}
	/**
	 * Get an specific Category by name
	 * @return ArrayList
	 */
	@Transactional
	public Category getByName(String strName) {
		// Get the first coincidence
		return this.iDAOCategory.findByName(strName);
	}
	/**
	 * Get all Categorys
	 * @return ArrayList
	 */
	@Transactional
	public List<Category> getAll() {
		// Get all coincidences
		return this.iDAOCategory.findAll();
	}
}
