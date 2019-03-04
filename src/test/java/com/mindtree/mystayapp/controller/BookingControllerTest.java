package com.mindtree.mystayapp.controller;

import static java.lang.System.currentTimeMillis;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.sql.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.mindtree.mystayapp.dto.BookingCriteria;
import com.mindtree.mystayapp.model.Booking;
import com.mindtree.mystayapp.model.Hotel;
import com.mindtree.mystayapp.model.Response;
import com.mindtree.mystayapp.model.User;
import com.mindtree.mystayapp.service.BookingService;
import com.mindtree.mystayapp.util.DateUtil;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookingControllerTest {

	@InjectMocks
	BookingController bookingController;

	@Mock
	BookingService bookingService;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testBookHotelsByAvailablity_WithoutBookingRequest() {
		BookingCriteria bookingRequest = null;
		ResponseEntity<Response> response = bookingController.bookHotelsByAvailablity(bookingRequest);
		assertNotNull(response.getStatusCode());

	}

	@Test
	public void testBookHotelsByAvailablity_WithBookingRequest() {
		BookingCriteria bookingRequest = new BookingCriteria();
		Date currentDate = new Date(currentTimeMillis());
        Date fromDate = new Date(DateUtil.addDays(currentDate, 1).getTime());
        Date toDate = new Date(DateUtil.addDays(currentDate, 2).getTime());
        bookingRequest.setFromDate(fromDate);
        bookingRequest.setToDate(toDate);
		Mockito.when(bookingService.saveBooking(Mockito.any(BookingCriteria.class))).thenReturn(buildBookingTestData());
		assertEquals(HttpStatus.CREATED.value(),bookingController.bookHotelsByAvailablity(bookingRequest).getStatusCodeValue());

	}
	
	@Test
	public void testBookHotelsByAvailablity_ForNullDates() {
		BookingCriteria booking = new BookingCriteria();
		booking.setNoOfBookedRooms(2);
		assertEquals(HttpStatus.UNPROCESSABLE_ENTITY.value(),bookingController.bookHotelsByAvailablity(booking).getStatusCodeValue());

	}
	
	@Test
	public void testBookHotelsByAvailablity_ForInvalidDates() {
		BookingCriteria bookingRequest = new BookingCriteria();
		
		Date currentDate = new Date(currentTimeMillis());
        Date fromDate = new Date(DateUtil.addDays(currentDate, -1).getTime());
        Date toDate = new Date(DateUtil.addDays(currentDate, -2).getTime());
		bookingRequest.setFromDate(fromDate);
		bookingRequest.setToDate(toDate);
		assertEquals(HttpStatus.UNPROCESSABLE_ENTITY.value(),bookingController.bookHotelsByAvailablity(bookingRequest).getStatusCodeValue());
	}

	@Test
	public void testBookHotelsByAvailablity_FromDateIsAfterToDate() {
		BookingCriteria bookingRequest = new BookingCriteria();
		
		Date currentDate = new Date(currentTimeMillis());
        Date fromDate = new Date(DateUtil.addDays(currentDate, 2).getTime());
        Date toDate = new Date(DateUtil.addDays(currentDate, 1).getTime());
		bookingRequest.setFromDate(fromDate);
		bookingRequest.setToDate(toDate);
		assertEquals(HttpStatus.UNPROCESSABLE_ENTITY.value(),bookingController.bookHotelsByAvailablity(bookingRequest).getStatusCodeValue());
	}
	public Booking buildBookingTestData() {
		Booking booking = new Booking();
		Date currentDate = new Date(currentTimeMillis());
        Date fromDate = new Date(DateUtil.addDays(currentDate, 1).getTime());
        Date toDate = new Date(DateUtil.addDays(currentDate, 2).getTime());
		booking.setBookingId(1L);
		booking.setBookingDate(currentDate);
		booking.setCheckInDate(fromDate);
		booking.setCheckOutDate(toDate);
		booking.setUser(new User());
		booking.setNoOfRooms(2);
		booking.setHotel(new Hotel());
		return booking;
	}

	
}
