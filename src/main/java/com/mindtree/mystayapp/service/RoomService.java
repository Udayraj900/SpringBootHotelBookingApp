package com.mindtree.mystayapp.service;

import com.mindtree.mystayapp.model.Room;

public interface RoomService {

	public Room addRoom(Room room);
	
	public Room updateRoom(Room room);
	
	public boolean deleteRoom(Long roomId);

	public Room getRoomsById(long id);

}
