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

import com.mindtree.mystayapp.dao.HotelRepository;
import com.mindtree.mystayapp.dto.AvailableRoomsDTO;
import com.mindtree.mystayapp.model.Booking;
import com.mindtree.mystayapp.model.Hotel;
import com.mindtree.mystayapp.model.Room;
import com.mindtree.mystayapp.util.DateUtil;

@RunWith(SpringRunner.class)
@DataJpaTest
public class HotelRepositoryTest {

	private static final String LUXURY = "Luxury";

	private static final String DELHI = "Delhi";

	private static final String BANGALORE = "Bangalore";

	@Autowired
	private HotelRepository hotelRepository;

	@Autowired
	private TestEntityManager entityManager;

	private Hotel mockHotel1;
	private Hotel mockHotel2;
	private Room mockRoom1;
	private Room mockRoom2;

	private List<Hotel> hotelList = new ArrayList<Hotel>();
	private List<Room> roomList = new ArrayList<Room>();
	private Date currentDate = new Date(System.currentTimeMillis());
	private Date fromDate;
	private Date toDate;

	@Before
	public void init() {

		mockHotel1 = new Hotel("By The Way", "9876543120", BANGALORE, 50);
		mockHotel2 = new Hotel("The Taj", "9876543120", DELHI, 10);
		fromDate = new Date(System.currentTimeMillis());
		mockRoom1 = new Room(LUXURY, 1000.00, 2);
		mockRoom2 = new Room("Semi-Luxury", 800.00, 3);
		hotelList.add(mockHotel1);
		hotelList.add(mockHotel2);
		mockRoom1.setHotel(mockHotel1);
		mockRoom2.setHotel(mockHotel1);
		roomList.add(mockRoom1);
		roomList.add(mockRoom2);
		fromDate = new Date(DateUtil.addDays(currentDate, 1).getTime());
		toDate = new Date(DateUtil.addDays(currentDate, 2).getTime());
		new Booking(null, mockHotel1, new Date(DateUtil.addDays(fromDate, 1).getTime()),
				new Date(DateUtil.addDays(fromDate, 2).getTime()), new Date(System.currentTimeMillis()), mockRoom1, 1,
				"Booked");
	
		entityManager.persist(mockHotel1);
		entityManager.persist(mockHotel2);
		entityManager.persist(mockRoom1);
		entityManager.persist(mockRoom2);

	}

	@After
	public void done() {
		entityManager.flush();
	}

	@Test
	public void testFindByCityAndDates() {

		List<AvailableRoomsDTO> hotelsFound = hotelRepository.findByCityAndDates(BANGALORE,
				fromDate,toDate);
		assertEquals(2, hotelsFound.size());

		hotelsFound = hotelRepository.findByCityAndDates(DELHI, null, null);
		assertEquals(0, hotelsFound.size());

		hotelsFound = hotelRepository.findByCityAndDates("", fromDate, null);
		assertEquals(0, hotelsFound.size());

		hotelsFound = hotelRepository.findByCityAndDates(null, null, null);
		assertEquals(0, hotelsFound.size());
	}

	@Test
	public void testFindByCityAndRoomType() {

		List<AvailableRoomsDTO> hotelsFound = hotelRepository.findByCityAndRoomType(BANGALORE,
				fromDate,toDate,LUXURY);
		assertEquals(1, hotelsFound.size());

		hotelsFound = hotelRepository.findByCityAndRoomType(DELHI, null, null,LUXURY);
		assertEquals(0, hotelsFound.size());

		hotelsFound = hotelRepository.findByCityAndRoomType("", fromDate, null,LUXURY);
		assertEquals(0, hotelsFound.size());

		hotelsFound = hotelRepository.findByCityAndRoomType(null, null, null,null);
		assertEquals(0, hotelsFound.size());
	}

	@Test
	public void testFindByCityAndPrice() {

		List<AvailableRoomsDTO> hotelsFound = hotelRepository.findByCityAndPrice(BANGALORE,
				fromDate,toDate,1000.0);
		assertEquals(2, hotelsFound.size());

		hotelsFound = hotelRepository.findByCityAndPrice(DELHI, null, null,null);
		assertEquals(0, hotelsFound.size());

		hotelsFound = hotelRepository.findByCityAndPrice("", fromDate, null,1000.0);
		assertEquals(0, hotelsFound.size());

		hotelsFound = hotelRepository.findByCityAndPrice(null, null, null,1000.0);
		assertEquals(0, hotelsFound.size());
	}

	@Test
	public void tesFindByCityAndDistance() {

		List<AvailableRoomsDTO> hotelsFound = hotelRepository.findByCityAndDistance(BANGALORE,
				fromDate,toDate,60);
		assertEquals(2, hotelsFound.size());

		hotelsFound = hotelRepository.findByCityAndDistance(DELHI, null, null,100);
		assertEquals(0, hotelsFound.size());

		hotelsFound = hotelRepository.findByCityAndDistance("", fromDate, null,0);
		assertEquals(0, hotelsFound.size());

		hotelsFound = hotelRepository.findByCityAndDistance(null, null, null,0);
		assertEquals(0, hotelsFound.size());
	}

}
