package com.ugisoftware.hotelmanagement.dto.request;

import com.ugisoftware.hotelmanagement.entities.Rooms;
import com.ugisoftware.hotelmanagement.utils.RoomClean;
import com.ugisoftware.hotelmanagement.utils.RoomStatues;
import com.ugisoftware.hotelmanagement.utils.RoomTypes;

public class RoomCreateDTO {
	private Long id;
	private Long employeeId;
	private int beds;
	private int price;
	private RoomTypes type;
	private RoomStatues statue;
	private RoomClean clean;
    private int roomNumber;

	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}




	public Long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
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