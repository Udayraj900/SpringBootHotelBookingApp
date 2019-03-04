package com.mindtree.mystayapp.dao;

import java.sql.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mindtree.mystayapp.dto.AvailableRoomsBySearchDTO;
import com.mindtree.mystayapp.model.Booking;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
	//dynamic instantiation of query
	@Query("Select new com.mindtree.mystayapp.dto.AvailableRoomsBySearchDTO "
			+ "(h.hotelId, h.hotelName, h.city, h.hotelPhoneNo, h.distanceFromAirport, r.roomId, r.roomType, r.roomPrice , r.totalRoomsCount , b.bookingStatus ,"
			+ "coalesce(r.totalRoomsCount - sum(b.noOfRooms),r.totalRoomsCount)) "
			+ "from Hotel h inner join Room r on r.hotel = h.hotelId and Lower(h.city) = LOWER(:city) and r.roomId = :roomId "
			+ "left join Booking b on r.roomId = b.bookedRoom and ((b.checkInDate <= (:toDate)) "
			+ "and (b.checkOutDate >= (:fromDate))) and LOWER(b.bookingStatus) = 'booked' "
			+ "group by h.hotelId, h.hotelName, h.city, h.hotelPhoneNo, h.distanceFromAirport, r.roomId, r.roomType, r.roomPrice , r.totalRoomsCount , b.bookingStatus "
			+ "having sum(coalesce(b.noOfRooms,0))<r.totalRoomsCount order by r.roomId,r.roomType,r.roomPrice ")
	public AvailableRoomsBySearchDTO getCurrentAvailableRooms(@Param("city") String city, @Param("roomId") Long roomId,
			@Param("fromDate") Date fromDate, @Param("toDate") Date toDate);

}
