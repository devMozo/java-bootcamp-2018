package Bootcamp2018.API.controllers;

import Bootcamp2018.API.entities.User;
import Bootcamp2018.API.services.ServiceAuth;
import Bootcamp2018.API.services.ServiceUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

/**
 * Represents the controller to handle the Users' Session
 * @author Mozo Nicolas
 * @version 1.0
 * @since 1.0
 */
@RestController
@RequestMapping("/")
public class ControllerAuth {
	// The Auth's Service
	@Autowired
	ServiceAuth serviceAuth;
	// The User's Service
	@Autowired
	ServiceUser serviceUser;
	/**
	 * Try login..
	 * @param oUser
	 * @return
	 */
	@RequestMapping(path = "/login", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<?> login(@RequestBody User oUser, HttpServletResponse httpResponse){
		// Return the first coincidence inside redis
		User userToLogin = this.serviceUser.getByNickname(oUser.getNickname());
		// Generate the response
		ResponseEntity oResponse = ResponseEntity.badRequest().build();
		// If the password are OK
		if(userToLogin.getPassword().equals(oUser.getPassword())){
			// Save the user's session
			String strEncodedID = this.serviceAuth.save(userToLogin);
			// If all is ok
			if(strEncodedID != null){
				// Update the response
				oResponse = ResponseEntity.ok(strEncodedID);
				// Add a new cookie
				httpResponse.addCookie(new Cookie("strEncodedID", strEncodedID));
			}
		}
		// Return the response
		return oResponse;
	}
	/**
	 * Try to register..
	 * @param oUser
	 * @return
	 */
	@RequestMapping(path = "/register", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<?> register(@RequestBody User oUser){
		// Return the first coincidence inside redis
		User userToLogin = this.serviceUser.add(oUser);
		// Generate the response
		ResponseEntity oResponse = ResponseEntity.badRequest().build();
		// If the password are OK
		if(userToLogin != null){
			// Update the response
			oResponse = ResponseEntity.ok().build();
		}
		// Return the response
		return oResponse;
	}
}
