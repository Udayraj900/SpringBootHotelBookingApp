package com.mindtree.mystayapp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

@Entity
@Table(indexes = { @Index(name = "room_type_index", columnList = "roomType"),
		@Index(name = "room_price_index", columnList = "roomPrice") })
public class Room {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "room_id")
	private Long roomId;

	@ManyToOne()
	@JoinColumn(name = "hotel_id", nullable = false)
	private Hotel hotel;

	@NotNull
	private String roomType;
	@NotNull
	private Double roomPrice;
	@NotNull
	private Integer totalRoomsCount;

	public Long getRoomId() {
		return roomId;
	}

	public void setRoomId(Long roomId) {
		this.roomId = roomId;
	}

	public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}

	public Double getRoomPrice() {
		return roomPrice;
	}

	public void setRoomPrice(Double roomPrice) {
		this.roomPrice = roomPrice;
	}

	public String getRoomType() {
		return roomType;
	}

	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}

	public Integer getTotalRoomsCount() {
		return totalRoomsCount;
	}

	public void setTotalRoomsCount(Integer totalRoomsCount) {
		this.totalRoomsCount = totalRoomsCount;
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

	public Room() {
	}

	public Room(String roomType, Double roomPrice, Integer totalRoomsCount) {
		super();
		this.roomType = roomType;
		this.roomPrice = roomPrice;
		this.totalRoomsCount = totalRoomsCount;
	}

	public Room(Long roomId, String roomType, Double roomPrice, Integer totalRoomsCount) {
		super();
		this.roomId = roomId;
		this.roomType = roomType;
		this.roomPrice = roomPrice;
		this.totalRoomsCount = totalRoomsCount;
	}

}