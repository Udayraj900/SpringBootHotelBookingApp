package com.mindtree.mystayapp.dto;

import java.util.List;

public class HotelsByRoomsAvailableDTO {

	
	private Long hotelId;
	private String hotelName;
	private String city;
	private String hotelPhoneNo;
	private Integer distanceFromAirport;
	private List<AvailableRoomsDTO> availableRooms;
	/**
	 * 
	 */
	
	
	public Long getHotelId() {
		return hotelId;
	}
	public void setHotelId(Long hotelId) {
		this.hotelId = hotelId;
	}
	public String getHotelName() {
		return hotelName;
	}
	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getHotelPhoneNo() {
		return hotelPhoneNo;
	}
	public void setHotelPhoneNo(String hotelPhoneNo) {
		this.hotelPhoneNo = hotelPhoneNo;
	}
	public Integer getDistanceFromAirport() {
		return distanceFromAirport;
	}
	public void setDistanceFromAirport(Integer distanceFromAirport) {
		this.distanceFromAirport = distanceFromAirport;
	
	}
	public List<AvailableRoomsDTO> getAvailableRooms() {
		return availableRooms;
	}
	public void setAvailableRooms(List<AvailableRoomsDTO> availableRooms) {
		this.availableRooms = availableRooms;
	}
			 
}
