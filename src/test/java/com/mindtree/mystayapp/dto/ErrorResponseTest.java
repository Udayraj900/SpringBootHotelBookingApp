package com.mindtree.mystayapp.dto;

import static pl.pojo.tester.api.assertion.Assertions.assertPojoMethodsFor;
import static pl.pojo.tester.api.assertion.Method.CONSTRUCTOR;
import static pl.pojo.tester.api.assertion.Method.GETTER;
import static pl.pojo.tester.api.assertion.Method.SETTER;
import org.junit.Test;

import com.mindtree.mystayapp.dto.ErrorResponse;

public class ErrorResponseTest {

	@Test
	public void testErrorResponse() {
		assertPojoMethodsFor(ErrorResponse.class)
		.testing(GETTER) 
		.testing(SETTER) 
        .testing(CONSTRUCTOR) 
        .areWellImplemented();
	
	}

}
