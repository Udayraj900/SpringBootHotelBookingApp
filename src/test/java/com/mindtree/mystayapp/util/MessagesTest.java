package com.mindtree.mystayapp.util;

import static pl.pojo.tester.api.assertion.Assertions.assertPojoMethodsForAll;
import static pl.pojo.tester.api.assertion.Method.CONSTRUCTOR;

import org.junit.Test;

public class MessagesTest {

	@Test
	public void testMessage() {
		final Class<Messages> classesUnderTest = Messages.class;
		assertPojoMethodsForAll(classesUnderTest)
		.testing(CONSTRUCTOR) 
		.areWellImplemented();
	}

	
}
