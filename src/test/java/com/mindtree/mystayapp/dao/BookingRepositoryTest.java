package com.mindtree.mystayapp.dao;

import static org.junit.Assert.assertEquals;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.mindtree.mystayapp.dao.BookingRepository;
import com.mindtree.mystayapp.model.Booking;
import com.mindtree.mystayapp.model.Hotel;
import com.mindtree.mystayapp.model.Role;
import com.mindtree.mystayapp.model.Room;
import com.mindtree.mystayapp.model.User;

@RunWith(SpringRunner.class)
@DataJpaTest
public class BookingRepositoryTest {

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private BookingRepository bookingRepository;

	
	private Hotel mockHotel1;
	private Hotel mockHotel2;
	private Room mockRoom1;
	private Room mockRoom2;
	private Booking booking = new Booking();
	private User user = new User();
	private Role role = new Role();

	private List<Hotel> hotelList = new ArrayList<Hotel>();
	private List<Room> roomList = new ArrayList<Room>();

	@Before
	public void init() {

		mockHotel1 = new Hotel("By The Way", "9876543120", "Bangalore", 50);
		mockHotel2 = new Hotel("The Taj", "9876543120", "Delhi", 10);

		mockRoom1 = new Room("Luxury", 1000.00, 2);
		mockRoom2 = new Room("Semi-Luxury", 800.00, 3);
		role.setRoleId(1);
		role.setRoleType("ADMIN");

		user.setRole(role);
		user.setUserName("Raj");
		mockRoom1.setHotel(mockHotel1);

		booking.setBookedRoom(mockRoom1);
		booking.setBookingDate(new Date(0));
		booking.setBookingStatus("Booked");
		booking.setCheckInDate(new Date(0));
		booking.setCheckOutDate(new Date(0));
		booking.setUser(user);
		booking.setNoOfRooms(2);
		booking.setHotel(mockRoom1.getHotel());

		hotelList.add(mockHotel1);
		hotelList.add(mockHotel2);

		roomList.add(mockRoom1);
		roomList.add(mockRoom2);

		entityManager.persist(role);
		entityManager.persist(user);
		entityManager.persist(mockHotel1);
		entityManager.persist(mockHotel2);
		entityManager.persist(mockRoom1);

		entityManager.persist(booking);

	}

	@After
	public void done() {
		entityManager.flush();
	}

	@Test
	public void testSaveBooking() {
		bookingRepository.save(booking);
		Room bookedRoom = (Room) entityManager.find(Room.class, mockRoom1.getRoomId());
		assertEquals(bookedRoom.getRoomId(), mockRoom1.getRoomId());
	}

}
