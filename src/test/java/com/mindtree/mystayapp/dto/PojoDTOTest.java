package com.mindtree.mystayapp.dto;

import static pl.pojo.tester.api.assertion.Assertions.assertPojoMethodsForAll;
import static pl.pojo.tester.api.assertion.Method.CONSTRUCTOR;
import static pl.pojo.tester.api.assertion.Method.GETTER;
import static pl.pojo.tester.api.assertion.Method.SETTER;
import static pl.pojo.tester.api.assertion.Method.TO_STRING;

import org.junit.Test;

import com.mindtree.mystayapp.model.Response;



public class PojoDTOTest {

	@SuppressWarnings("rawtypes")
	@Test
	public void testDTOClassess() {

		final Class[] classesUnderTest = { SignUpRequest.class, SearchRequestDTO.class,
				AvailableRoomsBySearchDTO.class,AvailableRoomsDTO.class,HotelsByRoomsAvailableDTO.class,BookingCriteria.class,Response.class };

		assertPojoMethodsForAll(classesUnderTest)
					.testing(GETTER)
					.testing(SETTER)
					.testing(CONSTRUCTOR).areWellImplemented();
		
		assertPojoMethodsForAll(BookingCriteria.class)
		.testing(TO_STRING).areWellImplemented();
	}

}
