package com.mindtree.mystayapp.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.mindtree.mystayapp.exception.ApplicationException;
import com.mindtree.mystayapp.model.Response;
import com.mindtree.mystayapp.model.Room;
import com.mindtree.mystayapp.service.RoomService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RoomControllerTest {

	@InjectMocks
	RoomController roomController;

	@Mock
	RoomService roomService;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test(expected = ApplicationException.class)
	public void testGetroomById_IdNotMatched() {
		Mockito.when(roomService.getRoomsById(Mockito.anyLong())).thenReturn(null);		
		roomController.getRoomsById(1L);		
	}

	@Test
	public void testGetroomById_IdMatched() {
		Mockito.when(roomService.getRoomsById(Mockito.anyLong())).thenReturn(buildRoomTestData());		
		ResponseEntity<Room> roomResponse = roomController.getRoomsById(1L);
		assertEquals(200, roomResponse.getStatusCodeValue());
	}
	
	@Test
	public void testAddRoom_NotSuccess() {
		Mockito.when(roomService.addRoom(Mockito.any(Room.class))).thenThrow(ApplicationException.class);		
		ResponseEntity<Room> roomResponse = roomController.addRoom(new Room());
		assertEquals(501, roomResponse.getStatusCodeValue());
	}

	@Test
	public void testAddRoom_Success() {
		Mockito.when(roomService.addRoom(Mockito.any(Room.class))).thenReturn(buildRoomTestData());		
		ResponseEntity<Room> roomResponse = roomController.addRoom(buildRoomTestData());
		assertEquals(201, roomResponse.getStatusCodeValue());
	}
	@Test
	public void testUpdateRoom_NotSuccess() {
		Mockito.when(roomService.updateRoom(Mockito.any(Room.class))).thenThrow(ApplicationException.class);		
		ResponseEntity<Room> roomResponse = roomController.updateRoom(buildRoomTestData());
		assertEquals(304, roomResponse.getStatusCodeValue());
	}

	@Test
	public void testUpdateRoom_Success() {
		when(roomService.updateRoom(Mockito.any(Room.class))).thenReturn(buildRoomTestData());		
		ResponseEntity<Room> roomResponse = roomController.updateRoom(buildRoomTestData());
		assertEquals(200, roomResponse.getStatusCodeValue());
	}

	@Test
	public void testDeleteRoom_NotSuccess() {
		when(roomService.deleteRoom(Mockito.anyLong())).thenThrow(ApplicationException.class);		
		ResponseEntity<Response> deleteResponse  = roomController.deleteRoom(1L);
		assertEquals(404, deleteResponse.getStatusCodeValue());
	}

	@Test
	public void testDeleteRoom_Success() {
		when(roomService.deleteRoom(Mockito.anyLong())).thenReturn(true);		
		ResponseEntity<Response> deleteResponse= roomController.deleteRoom(1L);
		assertEquals(200, deleteResponse.getStatusCodeValue());
	}
	public Room buildRoomTestData() {
		Room room = new Room();
		room.setRoomId(1L);
		room.setRoomType("Luxury");
		return room;
	}

}
