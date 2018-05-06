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

	@Autowired
	private ServiceProduct serviceProduct;

	@RequestMapping(path = "/action/add", produces = "application/json", method = RequestMethod.POST)
	public ResponseEntity<?> add(@RequestBody Product oProduct){

		Product oSavedProduct = this.serviceProduct.add(oProduct);

		URI newLocation = ServletUriComponentsBuilder
									.fromCurrentServletMapping()
									.path("/action/get/{id}")
									.buildAndExpand(oSavedProduct.getId())
									.toUri();

		return ResponseEntity.created(newLocation).build();
	}

	@RequestMapping(path = "/action/update/{id}", produces = "application/json", method = RequestMethod.PUT)
	public ResponseEntity<?> update(@PathVariable("id") long id, @RequestBody Product oProduct){

		return null;
	}

	@RequestMapping(path = "/action/delete/{id}", produces = "application/json", method = RequestMethod.DELETE)
	public ResponseEntity<?> delete(@PathVariable("id") Long id){

		return null;
	}

	@RequestMapping(path = "/action/get/{id}", produces = "application/json", method = RequestMethod.GET)
	public Product get(@PathVariable("id") Long id){
		return this.serviceProduct.get(id);
	}

	@RequestMapping(path = "/action/getAll", produces = "application/json", method = RequestMethod.GET)
	public List<Product> getAll(){
		return this.serviceProduct.getAll();
	}
}
