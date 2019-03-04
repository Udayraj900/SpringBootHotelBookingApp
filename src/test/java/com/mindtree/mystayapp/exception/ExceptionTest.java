package com.mindtree.mystayapp.exception;

import static pl.pojo.tester.api.assertion.Assertions.assertPojoMethodsForAll;
import static pl.pojo.tester.api.assertion.Method.CONSTRUCTOR;
import static pl.pojo.tester.api.assertion.Method.GETTER;
import static pl.pojo.tester.api.assertion.Method.SETTER;

import org.junit.Test;

import com.mindtree.mystayapp.exception.ApplicationException;
import com.mindtree.mystayapp.exception.BookingException;
import com.mindtree.mystayapp.exception.DAOException;
import com.mindtree.mystayapp.exception.FetchException;
import com.mindtree.mystayapp.exception.PaymentException;
import com.mindtree.mystayapp.exception.PersistenceException;
import com.mindtree.mystayapp.exception.ResourceNotFoundException;
import com.mindtree.mystayapp.exception.RestAPIExceptionHandler;
import com.mindtree.mystayapp.exception.SearchHotelException;
import com.mindtree.mystayapp.exception.ServiceException;
import com.mindtree.mystayapp.exception.SignUpException;

public class ExceptionTest {

	@Test
	public void testExceptionClassess() {
		@SuppressWarnings("rawtypes")
		final Class[] classesUnderTest = { ApplicationException.class, BookingException.class,
				SearchHotelException.class, ResourceNotFoundException.class, RestAPIExceptionHandler.class,
				SignUpException.class, DAOException.class, FetchException.class, PersistenceException.class,
				ServiceException.class, PaymentException.class };

		assertPojoMethodsForAll(classesUnderTest).testing(GETTER).testing(SETTER).testing(CONSTRUCTOR)
				.areWellImplemented();
	}

	@Test
	public void testRestAPIExceptionClasses() {

	}
}
