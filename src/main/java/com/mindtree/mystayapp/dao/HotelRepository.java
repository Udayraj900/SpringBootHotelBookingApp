package com.mindtree.mystayapp.dao;


import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mindtree.mystayapp.dto.AvailableRoomsDTO;
import com.mindtree.mystayapp.model.Hotel;
import java.lang.Long;

@Repository
public interface HotelRepository extends PagingAndSortingRepository<Hotel, Long> {

	
	
	@Query("Select h from Hotel h where h.hotelId in (:hotelIds) ")
	Page<Hotel> findAllByHotelId(@Param("hotelIds") List<Long> hotelIds, Pageable pageable);
	
	@Query("Select new com.mindtree.mystayapp.dto.AvailableRoomsDTO "
			+ "(h.hotelId, r.roomId, r.roomType, r.roomPrice , r.totalRoomsCount ,"
			+ "coalesce(r.totalRoomsCount - sum(b.noOfRooms),r.totalRoomsCount)) "
			+ "from Hotel h inner join Room r on r.hotel = h.hotelId and Lower(h.city) = LOWER(:city) " 
			+ "left join Booking b on r.roomId = b.bookedRoom and ((b.checkInDate <= (:toDate)) "
			+ "and (b.checkOutDate >= (:fromDate))) and LOWER(b.bookingStatus) = 'booked' "
			+ "group by h.hotelId, r.roomId, r.roomType, r.roomPrice , r.totalRoomsCount  "
			+ "having sum(coalesce(b.noOfRooms,0))<r.totalRoomsCount order by r.roomId,r.roomType,r.roomPrice ")
	List<AvailableRoomsDTO> findByCityAndDates(@Param("city") String city, @Param("fromDate") Date fromDate,
			@Param("toDate") Date toDate);
	
	@Query("Select new com.mindtree.mystayapp.dto.AvailableRoomsDTO "
			+ "(h.hotelId, r.roomId, r.roomType, r.roomPrice , r.totalRoomsCount  ,"
			+ "coalesce(r.totalRoomsCount - sum(b.noOfRooms),r.totalRoomsCount)) "
			+ "from Hotel h inner join Room r on r.hotel = h.hotelId and Lower(h.city) = LOWER(:city) and LOWER(r.roomType) = LOWER(:roomType) "
			+ "left join Booking b on r.roomId = b.bookedRoom and ((b.checkInDate <= (:toDate)) "
			+ "and (b.checkOutDate >= (:fromDate))) and LOWER(b.bookingStatus) = 'booked' "
			+ "group by h.hotelId, r.roomId, r.roomType, r.roomPrice , r.totalRoomsCount  "
			+ "having sum(coalesce(b.noOfRooms,0))<r.totalRoomsCount order by r.roomId,r.roomType,r.roomPrice ")
	List<AvailableRoomsDTO> findByCityAndRoomType(@Param("city") String city, @Param("fromDate") Date fromDate,
			@Param("toDate") Date toDate , @Param("roomType") String roomType);
	
	@Query("Select new com.mindtree.mystayapp.dto.AvailableRoomsDTO "
			+ "(h.hotelId, r.roomId, r.roomType, r.roomPrice , r.totalRoomsCount  ,"
			+ "coalesce(r.totalRoomsCount - sum(b.noOfRooms),r.totalRoomsCount)) "
			+ "from Hotel h inner join Room r on r.hotel = h.hotelId and Lower(h.city) = LOWER(:city) and r.roomPrice <= :price "
			+ "left join Booking b on r.roomId = b.bookedRoom and ((b.checkInDate <= (:toDate)) "
			+ "and (b.checkOutDate >= (:fromDate))) and LOWER(b.bookingStatus) = 'booked' "
			+ "group by h.hotelId,  r.roomId, r.roomType, r.roomPrice , r.totalRoomsCount "
			+ "having sum(coalesce(b.noOfRooms,0))<r.totalRoomsCount order by r.roomId,r.roomType,r.roomPrice ")
	List<AvailableRoomsDTO> findByCityAndPrice(@Param("city") String city, @Param("fromDate") Date fromDate,
			@Param("toDate") Date toDate,  @Param("price") Double price);
	
	@Query("Select new com.mindtree.mystayapp.dto.AvailableRoomsDTO "
			+ "(h.hotelId,  r.roomId, r.roomType, r.roomPrice , r.totalRoomsCount  ,"
			+ "coalesce(r.totalRoomsCount - sum(b.noOfRooms),r.totalRoomsCount)) "
			+ "from Hotel h inner join Room r on r.hotel = h.hotelId and Lower(h.city) = LOWER(:city) and h.distanceFromAirport <= :distance "
			+ "left join Booking b on r.roomId = b.bookedRoom and ((b.checkInDate <= (:toDate)) "
			+ "and (b.checkOutDate >= (:fromDate))) and LOWER(b.bookingStatus) = 'booked' "
			+ "group by h.hotelId, r.roomId, r.roomType, r.roomPrice , r.totalRoomsCount  "
			+ "having sum(coalesce(b.noOfRooms,0))<r.totalRoomsCount order by r.roomId,r.roomType,r.roomPrice ")
	List<AvailableRoomsDTO> findByCityAndDistance(@Param("city") String city, @Param("fromDate") Date fromDate,
			@Param("toDate") Date toDate, @Param("distance") int distance);

}
