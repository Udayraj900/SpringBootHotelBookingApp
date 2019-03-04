package com.mindtree.mystayapp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.mindtree.mystayapp.dao.RoleRepository;
import com.mindtree.mystayapp.dao.UserRepository;
import com.mindtree.mystayapp.dto.SignUpRequest;
import com.mindtree.mystayapp.exception.ApplicationException;
import com.mindtree.mystayapp.model.User;
import com.mindtree.mystayapp.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	
	@Autowired
    private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;

    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

   
	@Override
	public User registerUser(SignUpRequest userSignUpRequest) {
		User user = new User();
		user.setUserName(userSignUpRequest.getUserName());
		user.setAge(userSignUpRequest.getAge());
		user.setEmail(userSignUpRequest.getEmail());
		user.setPhoneNo(userSignUpRequest.getPhoneNo());
		user.setPassword(getPasswordEncoder().encode(userSignUpRequest.getPassword()));
		user.setRole(roleRepository.findByRoleType("USER"));
		return userRepository.save(user);
	}

	@Override
	public User findByUserName(String userName) {
		User user = userRepository.findByUserName(userName);
		if(user!= null){
		user.setPassword("*******");
		return userRepository.findByUserName(userName);
		}else{
			throw new ApplicationException("User not found for the username: "+userName);
		}
	}
}
