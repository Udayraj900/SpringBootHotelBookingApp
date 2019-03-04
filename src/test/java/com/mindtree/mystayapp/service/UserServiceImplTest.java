package com.mindtree.mystayapp.service;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.mindtree.mystayapp.dao.UserRepository;
import com.mindtree.mystayapp.dto.SignUpRequest;
import com.mindtree.mystayapp.exception.ApplicationException;
import com.mindtree.mystayapp.model.Role;
import com.mindtree.mystayapp.model.Room;
import com.mindtree.mystayapp.model.User;
import com.mindtree.mystayapp.service.UserService;
@SuppressWarnings("unused")
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceImplTest {
	
	@MockBean
	private UserRepository userRepository;
	
	@Autowired
	private UserService userServiceTest;
	
	private SignUpRequest userSignUpRequest;
	private User user;
	private Role role;
	@Before
	public void setUp(){
		role = new Role();
		role.setRoleId(1);
		role.setRoleType("USER");
		userSignUpRequest = new SignUpRequest();
		user = new User();
		user.setUserName("Raj");
		user.setAge(27);
		user.setEmail("raj.shaw@gmail.com");
		user.setPhoneNo("1231231231");
		user.setPassword("admin");
		user.setRole(role);
		userSignUpRequest.setUserName("Raj");
		userSignUpRequest.setAge(27);
		userSignUpRequest.setEmail("raj.shaw@gmail.com");
		userSignUpRequest.setPhoneNo("1231231231");
		userSignUpRequest.setPassword("admin");
		
	}
	
	@Test
	public void testRegisterUser() {
		Mockito.when(userRepository.save(Mockito.any(User.class))).thenReturn(user);
		assertEquals("Raj", userServiceTest.registerUser(userSignUpRequest).getUserName());
	}

	@Test
	public void testFindByUserName_Found() {
		Mockito.when(userRepository.findByUserName(Mockito.anyString())).thenReturn(user);
		assertEquals("Raj", userServiceTest.findByUserName("Raj").getUserName());
	}
	@Test
	public void testFindByUserName_NotFound() {
		try{
		Mockito.when(userRepository.findByUserName(Mockito.anyString())).thenThrow(ApplicationException.class);
		assertEquals("Raj", userServiceTest.findByUserName(null).getUserName());
		}catch(ApplicationException e){
			assert(true);
		}
		
	}

}
