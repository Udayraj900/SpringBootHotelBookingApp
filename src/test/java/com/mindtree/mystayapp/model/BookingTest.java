package com.mindtree.mystayapp.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static pl.pojo.tester.api.assertion.Assertions.assertPojoMethodsForAll;
import static pl.pojo.tester.api.assertion.Method.CONSTRUCTOR;
import static pl.pojo.tester.api.assertion.Method.TO_STRING;

import java.sql.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class BookingTest {

	private Booking booking;
	private Booking anotherbooking;
	private Room room;
	private Hotel hotel;
	private User user;

	@Before
	public void setUp() {
		booking = new Booking();
		anotherbooking = new Booking();
		room = new Room();
		user = new User();
		hotel = new Hotel();
		new Date(System.currentTimeMillis());
	}

	
	@Test
	public void testBookedRoom() {
		room.setRoomId(1l);
		booking.setBookedRoom(room);
		assertEquals(room, booking.getBookedRoom());
	}

	@Test
	public void testUser() {
		user.setUserId(1l);
		booking.setUser(user);
		assertEquals(user, booking.getUser());
	}

	@Test
	public void testHotel() {
		hotel.setHotelId(1l);
		booking.setHotel(hotel);
		assertEquals(hotel, booking.getHotel());
	}

	@Test
	public void equalsWhenEqual() {
		assertEquals(true, booking.equals(anotherbooking));
	}

	@Test
	public void equalsWhenNotEqual() {
		booking.setBookingId(1L);
		assertFalse(booking.equals(anotherbooking));
	}

	@Test
	public void hashCodeSameWhenEqual() {
		assertEquals(booking.hashCode(), anotherbooking.hashCode());
	}

	@Test
	public void hashCodeSameWhenNotEqual() {
		booking.setBookingId(1L);
		assertNotEquals(booking.hashCode(), anotherbooking.hashCode());
	}

	@Test
	  public void testToStringAndConstructors() {
	@SuppressWarnings("rawtypes")
	final Class classesUnderTest = Booking.class;
	assertPojoMethodsForAll(classesUnderTest)
	.testing(TO_STRING) 
	.testing(CONSTRUCTOR) 
	.areWellImplemented();
	}

}
