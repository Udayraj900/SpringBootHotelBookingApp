package com.mindtree.mystayapp.dto;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class BookingCriteria {
	
	private Long room;
	private Long hotel;
	private String bookingStatus;
	
	
	private Integer noOfBookedRooms;
	
	private String userName;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date fromDate;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date toDate;

	public BookingCriteria() {
		super();
	}

	public BookingCriteria(Long room, Long hotel, String bookingStatus, Integer noOfBookedRooms, String user,
			Date fromDate, Date toDate) {
		super();
		this.room = room;
		this.hotel = hotel;
		this.bookingStatus = bookingStatus;
		this.noOfBookedRooms = noOfBookedRooms;
		this.userName = user;
		this.fromDate = fromDate;
		this.toDate = toDate;
	}

	public Long getRoom() {
		return room;
	}

	public void setRoom(Long room) {
		this.room = room;
	}

	public Long getHotel() {
		return hotel;
	}

	public void setHotel(Long hotel) {
		this.hotel = hotel;
	}

	public String getBookingStatus() {
		return bookingStatus;
	}

	public void setBookingStatus(String bookingStatus) {
		this.bookingStatus = bookingStatus;
	}

	public Integer getNoOfBookedRooms() {
		return noOfBookedRooms;
	}

	public void setNoOfBookedRooms(Integer noOfBookedRooms) {
		this.noOfBookedRooms = noOfBookedRooms;
	}

	

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	@Override
	public String toString() {
		return "BookingCriteria [room=" + room + ", hotel=" + hotel + ", bookingStatus=" + bookingStatus
				+ ", noOfBookedRooms=" + noOfBookedRooms + ", userName=" + userName + ", fromDate=" + fromDate
				+ ", toDate=" + toDate + "]";
	}

	
	
}
