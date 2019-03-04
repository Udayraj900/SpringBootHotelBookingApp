package com.mindtree.mystayapp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mindtree.mystayapp.dto.SignUpRequest;
import com.mindtree.mystayapp.exception.ApplicationException;
import com.mindtree.mystayapp.exception.SignUpException;
import com.mindtree.mystayapp.model.Response;
import com.mindtree.mystayapp.model.User;
import com.mindtree.mystayapp.service.UserService;

/**
 * 
 * @author RShaw
 * 
 *         Controller for user activities like sign up and getting user details
 *
 */
@RestController
@RequestMapping(value = "/mystayapp")
public class UserController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/signup/user", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<Response> userSignUp(@RequestBody SignUpRequest userSignUpRequest) throws SignUpException {
		Response response = new Response();

		if (userSignUpRequest != null) {
			User user = userService.registerUser(userSignUpRequest);

			response.setStatus(HttpStatus.OK.value());
			response.setMessage("Registration is completed successfully with the user id: " + user.getUserId());
			return new ResponseEntity<>(response, HttpStatus.OK);
		} else {
			logger.error("User details are not proper, registration is not completed. ");
			response.setStatus(HttpStatus.BAD_REQUEST.value());
			response.setMessage("User details are not proper, registration is not completed.");
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}

	}

	@RequestMapping(value = "/user/{username}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<User> getUserDetailsByUsername(@PathVariable("username") String username)
			throws ApplicationException {
		Response response = new Response();
		User user = new User();
		try {
			user = userService.findByUserName(username);

			response.setStatus(HttpStatus.OK.value());
			response.setMessage("User details found for user: " + username);
			return new ResponseEntity<>(user, HttpStatus.OK);
		} catch (ApplicationException e) {
			throw new ApplicationException(HttpStatus.NO_CONTENT.value(), "",
					"User not found for the username: " + username);
		}
	}

}
