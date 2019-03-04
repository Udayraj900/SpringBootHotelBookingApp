package com.mindtree.mystayapp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

@Entity
@Table(indexes = {@Index(name = "city_index",columnList = "city")
				,@Index(name = "distance_index" , columnList = "distanceFromAirport")
			})
public class Hotel{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "hotel_id")
	private Long hotelId;

	private String hotelName;

	private String hotelPhoneNo;

	private String city;

	private int distanceFromAirport;

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

	public String getHotelPhoneNo() {
		return hotelPhoneNo;
	}

	public void setHotelPhoneNo(String hotelPhoneNo) {
		this.hotelPhoneNo = hotelPhoneNo;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public int getDistanceFromAirport() {
		return distanceFromAirport;
	}

	public void setDistanceFromAirport(int distanceFromAirport) {
		this.distanceFromAirport = distanceFromAirport;
	}

	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}

	@Override
	public boolean equals(Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj);
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	
	public Hotel() {
		
	}

	public Hotel(String hotelName, String hotelPhoneNo, String city, int distanceFromAirport) {
		super();
		this.hotelName = hotelName;
		this.hotelPhoneNo = hotelPhoneNo;
		this.city = city;
		this.distanceFromAirport = distanceFromAirport;
	}

	public Hotel(Long hotelId, String hotelName, String hotelPhoneNo, String city, int distanceFromAirport) {
		super();
		this.hotelId = hotelId;
		this.hotelName = hotelName;
		this.hotelPhoneNo = hotelPhoneNo;
		this.city = city;
		this.distanceFromAirport = distanceFromAirport;
	}

}
