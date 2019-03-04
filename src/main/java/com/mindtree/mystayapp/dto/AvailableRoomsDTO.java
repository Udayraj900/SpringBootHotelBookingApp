package com.mindtree.mystayapp.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class AvailableRoomsDTO {

	@JsonIgnore
	private Long hotelId;
	@JsonIgnore
	private Long roomId;
	private String roomType;
	private Double roomPrice;
	private Integer totalRoomCount;
	private Long currentAvailableRoomCount;
	
	
	
	public AvailableRoomsDTO() {
	}
	
	
	/**
	 * @param hotelId
	 * @param roomId
	 * @param roomType
	 * @param roomPrice
	 * @param totalRoomCount
	 * @param currentAvailableRoomCount
	 */
	public AvailableRoomsDTO(Long hotelId, Long roomId, String roomType, Double roomPrice, Integer totalRoomCount,
			Long currentAvailableRoomCount) {
		this.hotelId = hotelId;
		this.roomId = roomId;
		this.roomType = roomType;
		this.roomPrice = roomPrice;
		this.totalRoomCount = totalRoomCount;
		this.currentAvailableRoomCount = currentAvailableRoomCount;
	}


	public Long getHotelId() {
		return hotelId;
	}
	public void setHotelId(Long hotelId) {
		this.hotelId = hotelId;
	}
	public Long getRoomId() {
		return roomId;
	}
	public void setRoomId(Long roomId) {
		this.roomId = roomId;
	}
	public String getRoomType() {
		return roomType;
	}
	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}
	public Double getRoomPrice() {
		return roomPrice;
	}
	public void setRoomPrice(Double roomPrice) {
		this.roomPrice = roomPrice;
	}
	public Integer getTotalRoomCount() {
		return totalRoomCount;
	}
	public void setTotalRoomCount(Integer totalRoomCount) {
		this.totalRoomCount = totalRoomCount;
	}
	public Long getCurrentAvailableRoomCount() {
		return currentAvailableRoomCount;
	}
	public void setCurrentAvailableRoomCount(Long currentAvailableRoomCount) {
		this.currentAvailableRoomCount = currentAvailableRoomCount;
	}
	

}
