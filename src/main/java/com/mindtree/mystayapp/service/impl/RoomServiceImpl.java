package com.mindtree.mystayapp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindtree.mystayapp.dao.RoomRepository;
import com.mindtree.mystayapp.exception.ApplicationException;
import com.mindtree.mystayapp.model.Room;
import com.mindtree.mystayapp.service.RoomService;

@Service
public class RoomServiceImpl implements RoomService{

	@Autowired
	private RoomRepository roomRepository;

	@Override
	public Room addRoom(Room room) {
		return roomRepository.save(room);
	}

	@Override
	public Room updateRoom(Room room) {
		return roomRepository.save(room);
	}

	@Override
	public boolean deleteRoom(Long roomId) {
		
		try{
			roomRepository.deleteById(roomId);
			return true;
		}catch(ApplicationException e){
			return false;
		}
		
	}

	@Override
	public Room getRoomsById(long id) {
		return roomRepository.getOne(id);
		
	}
	
	
}
