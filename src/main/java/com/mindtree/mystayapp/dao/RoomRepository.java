package com.mindtree.mystayapp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mindtree.mystayapp.model.Room;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long>{
	

}
