package com.mindtree.mystayapp.dao;

import static org.junit.Assert.assertEquals;

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

import com.mindtree.mystayapp.dao.RoomRepository;
import com.mindtree.mystayapp.model.Hotel;
import com.mindtree.mystayapp.model.Room;

@RunWith(SpringRunner.class)
@DataJpaTest
public class RoomRepositoryTest {

	private static final String BANGALORE = "Bangalore";

	@Autowired
	private TestEntityManager entityManager;
	
	@Autowired
	private RoomRepository roomRepository;
	
	private Hotel mockHotel1;
	private Hotel mockHotel2;
	private Room mockRoom1;
	private Room mockRoom2;

	private List<Hotel> hotelList = new ArrayList<Hotel>();
	private List<Room> roomList = new ArrayList<Room>();

	@Before
	public void init() {

		mockHotel1 = new Hotel("By The Way", "9876543120", BANGALORE, 50);
		mockHotel2 = new Hotel("The Taj", "9876543120", BANGALORE, 10);

		mockRoom1 = new Room("Luxury", 1000.00, 2);
		mockRoom2 = new Room("Semi-Luxury", 800.00, 3);

		hotelList.add(mockHotel1);
		hotelList.add(mockHotel2);
		mockRoom1.setHotel(mockHotel1);
		mockRoom2.setHotel(mockHotel1);
		roomList.add(mockRoom1);
		roomList.add(mockRoom2);

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
	public void testAddRoom(){
		roomRepository.save(mockRoom1);
		Room savedRoom = entityManager.find(Room.class,mockRoom1.getRoomId());
		assertEquals(savedRoom.getRoomId(), mockRoom1.getRoomId());
		
		
	}
	
	@Test
	public void testUpdateRoom(){
		mockRoom1.setRoomPrice(1200.00);
		roomRepository.save(mockRoom1);
		Room updatedRoom = entityManager.find(Room.class,mockRoom1.getRoomId());
		
		assertEquals(mockRoom1.getRoomPrice(), updatedRoom.getRoomPrice());
	}
	
	@Test
	public void testDeleteRoom(){
		
		roomRepository.deleteById(mockRoom2.getRoomId());
		Room deletedRoom = entityManager.find(Room.class,mockRoom2.getRoomId()); 
		assertEquals(null,deletedRoom);
	}
}
