package com.mindtree.mystayapp.dto;

public class AvailableRoomsBySearchDTO {

	
	private Long hotelId;
	private String hotelName;
	private String city;
	private String hotelPhoneNo;
	private Integer distanceFromAirport;
	private Long roomId;
	private String roomType;
	private Double roomPrice ;
	private Integer totalRoomsCount ;
	private String bookingStatus;
	private Long currentAvailableRoomCount;
	/**
	 * 
	 */
	public AvailableRoomsBySearchDTO() {
	}
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
	public Integer getTotalRoomsCount() {
		return totalRoomsCount;
	}
	public void setTotalRoomsCount(Integer totalRoomsCount) {
		this.totalRoomsCount = totalRoomsCount;
	}
	public String getBookingStatus() {
		return bookingStatus;
	}
	public void setBookingStatus(String bookingStatus) {
		this.bookingStatus = bookingStatus;
	}
	public Long getCurrentAvailableRoomCount() {
		return currentAvailableRoomCount;
	}
	public void setCurrentAvailableRoomCount(Long currentAvailableRoomCount) {
		this.currentAvailableRoomCount = currentAvailableRoomCount;
	}
	/**
	 * @param hotelId
	 * @param hotelName
	 * @param city
	 * @param hotelPhoneNo
	 * @param distanceFromAirport
	 * @param roomId
	 * @param roomType
	 * @param roomPrice
	 * @param totalRoomsCount
	 * @param bookingStatus
	 * @param currentAvailableRoomCount
	 */
	public AvailableRoomsBySearchDTO(Long hotelId, String hotelName, String city, String hotelPhoneNo,
			Integer distanceFromAirport, Long roomId, String roomType, Double roomPrice, Integer totalRoomsCount,
			String bookingStatus, Long currentAvailableRoomCount) {
		this.hotelId = hotelId;
		this.hotelName = hotelName;
		this.city = city;
		this.hotelPhoneNo = hotelPhoneNo;
		this.distanceFromAirport = distanceFromAirport;
		this.roomId = roomId;
		this.roomType = roomType;
		this.roomPrice = roomPrice;
		this.totalRoomsCount = totalRoomsCount;
		this.bookingStatus = bookingStatus;
		this.currentAvailableRoomCount = currentAvailableRoomCount;
	}
	public AvailableRoomsBySearchDTO(Long hotelId, Long roomId, String roomType, Double roomPrice, Integer totalRoomsCount,
			String bookingStatus, Long currentAvailableRoomCount) {
		this.hotelId = hotelId;
		this.roomId = roomId;
		this.roomType = roomType;
		this.roomPrice = roomPrice;
		this.totalRoomsCount = totalRoomsCount;
		this.bookingStatus = bookingStatus;
		this.currentAvailableRoomCount = currentAvailableRoomCount;
	}
			 
}
