package com.mindtree.mystayapp.controller;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.mindtree.mystayapp.dto.SignUpRequest;
import com.mindtree.mystayapp.exception.ApplicationException;
import com.mindtree.mystayapp.exception.SignUpException;
import com.mindtree.mystayapp.model.Response;
import com.mindtree.mystayapp.model.User;
import com.mindtree.mystayapp.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserControllerTest {

	@InjectMocks
	UserController userController;

	@Mock
	UserService userService;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testUserSignUpFail_WhenSignUpRequestIsNotAvailable() {
		SignUpRequest userRequest = null;
		Mockito.when(userService.registerUser(Mockito.any(SignUpRequest.class))).thenThrow(SignUpException.class);
		ResponseEntity<Response> userResponse = userController.userSignUp(userRequest);
		assertEquals(400, userResponse.getStatusCodeValue());

	}

	@Test
	public void testUserSignUpSuccess_WhenSignUpRequestIsAvailable() {
		SignUpRequest userRequest = new SignUpRequest();
		Mockito.when(userService.registerUser(Mockito.any(SignUpRequest.class))).thenReturn(buildUserTestData());
		ResponseEntity<Response> userResponse = userController.userSignUp(userRequest);
		assertEquals(200, userResponse.getStatusCodeValue());

	}

	@Test(expected = ApplicationException.class)
	public void testGetUserDetailsByUsername_NotSuccess() {
		String user = "1";
		Mockito.when(userService.findByUserName(Mockito.anyString())).thenThrow(ApplicationException.class);
		ResponseEntity<User> userResponse = userController.getUserDetailsByUsername(user);
		assertEquals(204, userResponse.getStatusCodeValue());

	}

	@Test
	public void testGetUserDetailsByUsername_Success() {
		Mockito.when(userService.findByUserName(Mockito.anyString())).thenReturn(buildUserTestData());
		ResponseEntity<User> userResponse = userController.getUserDetailsByUsername("Raj");
		assertEquals(200, userResponse.getStatusCodeValue());

	}

	public User buildUserTestData() {
		User user = new User();
		user.setUserId(1L);
		user.setUserName("Raj");
		return user;
	}

}
