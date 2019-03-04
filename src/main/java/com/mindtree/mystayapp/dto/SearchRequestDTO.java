package com.mindtree.mystayapp.dto;

import java.sql.Date;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

public class SearchRequestDTO {

	@NotNull(message = "Please provide city name to search hotels.")
	private String city;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date fromDate;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date toDate;
	private String roomType;
	private Double price;
	private Integer distance;
	private Integer user;
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
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
	public String getRoomType() {
		return roomType;
	}
	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Integer getDistance() {
		return distance;
	}
	public void setDistance(Integer distance) {
		this.distance = distance;
	}
	public Integer getUser() {
		return user;
	}
	public void setUser(Integer user) {
		this.user = user;
	}
	@Override
	public String toString() {
		return "SearchRequest [city=" + city + ", fromDate=" + fromDate + ", toDate=" + toDate + ", roomType="
				+ roomType + ", price=" + price + ", distance=" + distance + ", user=" + user + "]";
	}
	
	
}
