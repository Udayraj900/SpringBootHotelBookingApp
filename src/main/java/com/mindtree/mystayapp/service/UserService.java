package com.mindtree.mystayapp.service;

import com.mindtree.mystayapp.dto.SignUpRequest;
import com.mindtree.mystayapp.model.User;

public interface UserService {

	

	public User registerUser(SignUpRequest userSignUpRequest);

	public User findByUserName(String userName);

	
}
