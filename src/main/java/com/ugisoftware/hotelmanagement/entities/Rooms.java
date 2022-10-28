package com.ugisoftware.hotelmanagement.entities;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ugisoftware.hotelmanagement.utils.RoomClean;
import com.ugisoftware.hotelmanagement.utils.RoomStatues;
import com.ugisoftware.hotelmanagement.utils.RoomTypes;

import lombok.Data;
@Data
@Entity
@Table(name="rooms")
public class Rooms {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull(message = "Beds number can not be null")
	private int beds;
	
	@NotNull(message = "Price can not be null")
	private int price;
	
	@NotNull(message = "Room Number can not be null")
	private int roomNumber;
	
	@NotNull(message = "Type can not be null")
	private RoomTypes type;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "employee_id",nullable = false)
	@JsonIgnore
	private Employee employee;
	
	@NotNull(message = "Statue can not be empty")
	private RoomStatues statue;
	
	@NotNull(message = "Is Clean can not be empty")
	private RoomClean clean;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getBeds() {
		return beds;
	}

	public void setBeds(int beds) {
		this.beds = beds;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public RoomTypes getType() {
		return type;
	}

	public void setType(RoomTypes type) {
		this.type = type;
	}

	

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public RoomStatues getStatue() {
		return statue;
	}

	public void setStatue(RoomStatues statue) {
		this.statue = statue;
	}

	public RoomClean getClean() {
		return clean;
	}

	public void setClean(RoomClean clean) {
		this.clean = clean;
	}

	public int getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(int roomNumber) {
		this.roomNumber = roomNumber;
	}
	
	

	
}
