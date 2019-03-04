package com.mindtree.mystayapp.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static pl.pojo.tester.api.assertion.Assertions.assertPojoMethodsForAll;
import static pl.pojo.tester.api.assertion.Method.CONSTRUCTOR;
import static pl.pojo.tester.api.assertion.Method.GETTER;
import static pl.pojo.tester.api.assertion.Method.SETTER;
import static pl.pojo.tester.api.assertion.Method.TO_STRING;

import org.junit.Before;
import org.junit.Test;

public class PojoEntityTest {

	private Role role;
	private Role anotherRole;

	@Before
	public void setUp() {
		role = new Role();
		anotherRole = new Role();
	}
	@Test
	  public void testPojoClassess() {
	@SuppressWarnings("rawtypes")
	final Class[] classesUnderTest = {Hotel.class,User.class,Role.class,Booking.class};
	assertPojoMethodsForAll(classesUnderTest)
	.testing(GETTER) 
	.testing(SETTER) 
    .testing(TO_STRING) 
    .testing(CONSTRUCTOR) 
    .areWellImplemented();
	}
	
	@Test
	public void equalsWhenEqual() {
		assertEquals(true, role.equals(anotherRole));
	}

	@Test
	public void equalsWhenNotEqual() {
		role.setRoleId(1);
		assertFalse(role.equals(anotherRole));
	}

	
}
