package com.mindtree.mystayapp.service;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.mindtree.mystayapp.dao.RoomRepository;
import com.mindtree.mystayapp.model.Hotel;
import com.mindtree.mystayapp.model.Room;
import com.mindtree.mystayapp.service.RoomService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RoomServiceTest {

	@Autowired
	private RoomService roomServiceMock;

	@MockBean
	private RoomRepository roomRepositoryTest;

	private Hotel mockHotel1;
	private Hotel mockHotel2;
	private Room mockRoom1;
	private Room mockRoom2;

	private List<Hotel> hotelList = new ArrayList<Hotel>();
	private List<Room> roomList = new ArrayList<Room>();

	@Before
	public void init() {

		mockHotel1 = new Hotel(1L, "By The Way", "9876543120", "Bangalore", 50);
		mockHotel2 = new Hotel(2L, "The Taj", "9876543120", "Bangalore", 10);

		mockRoom1 = new Room(101L, "Luxury", 1000.00, 2);
		mockRoom2 = new Room(102L, "Semi-Luxury", 800.00, 3);

		hotelList.add(mockHotel1);
		hotelList.add(mockHotel2);
		mockRoom1.setHotel(mockHotel1);
		mockRoom2.setHotel(mockHotel1);
		roomList.add(mockRoom1);
		roomList.add(mockRoom2);

	}

	@Test
	public void testAddRoom() {
		Mockito.when(roomRepositoryTest.save(Mockito.any(Room.class))).thenReturn(mockRoom1);
		assertEquals(mockRoom1, roomServiceMock.addRoom(mockRoom1));
	}

	@Test
	public void testUpdateRoom() {
		mockRoom1.setRoomPrice(1200.00);
		Mockito.when(roomRepositoryTest.save(Mockito.any(Room.class))).thenReturn(mockRoom1);
		Room updatedroom = roomServiceMock.updateRoom(mockRoom1);
		assertEquals(updatedroom.getRoomPrice(), mockRoom1.getRoomPrice());
	}

	@Test
	public void testDeleteRoom() {

		Mockito.doNothing().when(roomRepositoryTest).deleteById(Mockito.anyLong());
		roomServiceMock.deleteRoom(Mockito.anyLong());
		Mockito.verify(roomRepositoryTest, Mockito.times(1)).deleteById(Mockito.anyLong());

	}

	@Test
	public void testGetRoomsById() {

		Mockito.when(roomRepositoryTest.getOne(Mockito.anyLong())).thenReturn(mockRoom1);
		Room room = roomServiceMock.getRoomsById(Mockito.anyLong());
		assertEquals(mockRoom1.getRoomId(), room.getRoomId());

	}

}
